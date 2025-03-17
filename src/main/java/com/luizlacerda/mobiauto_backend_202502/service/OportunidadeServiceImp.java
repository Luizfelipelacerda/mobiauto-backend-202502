package com.luizlacerda.mobiauto_backend_202502.service;

import com.luizlacerda.mobiauto_backend_202502.Enum.Cargo;
import com.luizlacerda.mobiauto_backend_202502.Enum.Status;
import com.luizlacerda.mobiauto_backend_202502.controller.dto.*;
import com.luizlacerda.mobiauto_backend_202502.controller.mapper.OportunidadeMapper;
import com.luizlacerda.mobiauto_backend_202502.entity.*;
import com.luizlacerda.mobiauto_backend_202502.exception.*;
import com.luizlacerda.mobiauto_backend_202502.repository.*;
import com.luizlacerda.mobiauto_backend_202502.util.JwtUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class OportunidadeServiceImp implements OportunidadeService {

    private final OportunidadeRepository oportunidadeRepository;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final OportunidadeMapper oportunidadeMapper;
    private final VeiculoRepository veiculoRepository;
    private final ClienteRepository clienteRepository;
    private final RevendaRepository revendaRepository;
    private final UsuarioOportunidadeRepository usuarioOportunidadeRepository;
    private final EscolhaDeUsuarioInteligente usuarioInteligenteService;
    private final UsuarioOportunidadeService usuarioOportunidadeService;


    @Override
    public List<OportunidadeDTO> getAllOportunidades() throws NotAdminUserException {

        Optional<User> userOptional = this.userRepository.findById(this.jwtUtil.getUserIdFromToken());

        if(userOptional.isEmpty()) {
            throw new UserDoesntExistException("Usuario não encontrado");
        }
        List<Oportunidade> all;
        User user = userOptional.get();

        switch (user.getCargo()){
            case ADMIN -> all = this.oportunidadeRepository.findAll();
            case ASSISTENTE -> all = this.oportunidadeRepository.findAllByUsuarioAssociadoId(user.getId());
            case GERENTE,PROPRIETARIO -> all = this.oportunidadeRepository.findAllByRevendaId(user.getRevenda().getId());
            default -> throw new NoCargoFoundException("Usuario não possui cargo.");
        }
        return this.oportunidadeMapper.toDTO(all);
    }

    public void atendeOportunidade(OportunidadeUpdateDTO oportunidadeUpdateDTO){
        Optional<User> byId = this.userRepository.findById(this.jwtUtil.getUserIdFromToken());
        if(byId.isEmpty()){
            throw new UserOrOportunidadeDoesntExistException("Usuario não encontrado.");
        }
        User usuario = byId.get();
        Optional<Oportunidade> oportunidadeById = this.oportunidadeRepository.findByOportunidadeIdAndStatusNot(oportunidadeUpdateDTO.oportunidadeId(),Status.CONCLUIDO);
        if(oportunidadeById.isEmpty()){
            //throw exception
            throw new UserOrOportunidadeDoesntExistException("Oportunidade não encontrada");
        }
        Oportunidade oportunidade = oportunidadeById.get();
        if(!usuario.getId().equals(oportunidadeUpdateDTO.usuarioAssociado())){
            // throw exception
            throw new UserNOtAuthorizedToAttendOportunity("Usuario não autorizado a atender Oportunidade.");
        }
        oportunidade.setStatus(oportunidadeUpdateDTO.status());
        oportunidade.setMotivoConclusao(oportunidadeUpdateDTO.status() == Status.CONCLUIDO ? oportunidadeUpdateDTO.motivoConclusao() : null);
        oportunidade.setDataAlteracao(LocalDateTime.now());

        this.oportunidadeRepository.save(oportunidade);
        this.usuarioOportunidadeService.createUpdateUsuarioOportunidade(oportunidade);

    }

    @Override
    public void transferirOportunidade(OportunidadeTransferDTO oportunidadeTransferDTO) {
        //verifica proprietario
        Cargo cargoAtual = this.jwtUtil.getUserCargoFromToken();
        if(cargoAtual.equals(Cargo.ASSISTENTE)){
            throw new CannotCreateUpdateUserException("Assistentes não podem fazer essa ação.");
        }
        //usuario e propriedade existem
        Optional<User> usuarioAssociado = this.userRepository.findById(oportunidadeTransferDTO.usuarioAssociado());
        Optional<Oportunidade> oportunidadeSelecionada = this.oportunidadeRepository.findByOportunidadeIdAndStatusNot(oportunidadeTransferDTO.oportunidadeSelecionada(),Status.CONCLUIDO);
        if(usuarioAssociado.isEmpty() || oportunidadeSelecionada.isEmpty()){
            //throw
            throw new UserOrOportunidadeDoesntExistException("Usuario Associado ou Oportunidade não existem");
        }
        //altera oportunidade

        Oportunidade oportunidade = oportunidadeSelecionada.get();
        oportunidade.setUsuarioAssociado(usuarioAssociado.get());

        this.createUsuarioOportunidade(usuarioAssociado.get());

        this.oportunidadeRepository.save(oportunidade);
    }

    public void createUsuarioOportunidade(User usuarioAssociadoId){
        Optional<UsuarioOportunidade> byAssistente = this.usuarioOportunidadeRepository.findByAssistente_Id(usuarioAssociadoId.getId());
        UsuarioOportunidade usuarioOportunidade;
        if(byAssistente.isEmpty()){
            usuarioOportunidade = new UsuarioOportunidade(0,0, null);
        }else{
            usuarioOportunidade = byAssistente.get();
        }

        usuarioOportunidade.setOportunidadesEmAndamento(usuarioOportunidade.getOportunidadesEmAndamento()+1);
        usuarioOportunidade.setOportunidadesConcluidas(usuarioOportunidade.getOportunidadesConcluidas());
        usuarioOportunidade.setUltimaOportunidade(LocalDateTime.now());
        usuarioOportunidade.setAssistente(usuarioAssociadoId);

        this.usuarioOportunidadeRepository.save(usuarioOportunidade);
    }

    @Override
    public List<Oportunidade> inserirOportunidades(List<OportunidadeInsertDTO> oportunidadeInsertDTOS)  {
        // map to entity
        List<Oportunidade> oportunidades = oportunidadeInsertDTOS.stream().map(this::inserirOportunidade).toList();

        return oportunidades;

    }

    @Override
    @Transactional
    public Oportunidade inserirOportunidade(OportunidadeInsertDTO oportunidadeInsertDTO) {
        // map to entity
        VeiculoDTO veiculoDto = oportunidadeInsertDTO.veiculo();
        Veiculo veiculo = new Veiculo(veiculoDto.marca(),veiculoDto.modelo(),veiculoDto.versao(),veiculoDto.ano());

        ClienteDTO clienteDto = oportunidadeInsertDTO.cliente();
        Cliente cliente = new Cliente(clienteDto.nome(),clienteDto.email(),clienteDto.telefone());

        Optional<Revenda> revendaById = this.revendaRepository.findByCnpj(oportunidadeInsertDTO.cnpj());
        if(revendaById.isEmpty()){
            throw new RevendaNotFoundException("Revenda não Encontrada");
        }
        Revenda revenda = revendaById.get();
        Veiculo veiculoSaved = this.veiculoRepository.save(veiculo);
        Cliente clienteSaved = this.clienteRepository.save(cliente);
        Oportunidade oportunidade = Oportunidade.builder()
                .status(Status.NOVO)
                .revenda(revenda)
                .cliente(clienteSaved)
                .veiculo(veiculoSaved)
                .dataInsersao(LocalDateTime.now())
                .dataAlteracao(LocalDateTime.now())
                .build();
        Optional emailop = Optional.ofNullable(oportunidadeInsertDTO.emailAssociado());
        emailop.ifPresent(op ->
            this.userRepository.findByEmail(op.toString()).ifPresent(usuario -> oportunidade.setUsuarioAssociado(usuario))
        );
        if(emailop.isEmpty()){
            oportunidade.setUsuarioAssociado(this.usuarioInteligenteService.escolhaDeUsuario(oportunidade.getRevenda().getId()));
        }

        Oportunidade oportunidadSaved = this.oportunidadeRepository.save(oportunidade);

        //insert oportunidade
        return this.oportunidadeRepository.save(oportunidadSaved);
    }
}
