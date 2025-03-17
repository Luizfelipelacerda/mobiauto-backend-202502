package com.luizlacerda.mobiauto_backend_202502.service;

import com.luizlacerda.mobiauto_backend_202502.controller.dto.CreateRevendaDTO;
import com.luizlacerda.mobiauto_backend_202502.controller.dto.RevendaDTO;
import com.luizlacerda.mobiauto_backend_202502.controller.mapper.RevendaMapper;
import com.luizlacerda.mobiauto_backend_202502.entity.Revenda;
import com.luizlacerda.mobiauto_backend_202502.entity.User;
import com.luizlacerda.mobiauto_backend_202502.exception.CnpjNotValidException;
import com.luizlacerda.mobiauto_backend_202502.exception.NotAdminUserException;
import com.luizlacerda.mobiauto_backend_202502.repository.RevendaRepository;
import com.luizlacerda.mobiauto_backend_202502.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RevendaServiceImpl implements RevendaService{

    private final CnpjValidatorServiceImp cnpjValidatorServiceImp;
    private final RevendaRepository revendaRepository;
    private final UserService userService;
    private final JwtUtil jwtUtil;
    public final RevendaMapper revendaMapper;
    @Override
    public void createNewRevenda(CreateRevendaDTO createRevendaDTO) {

        if(!this.cnpjValidatorServiceImp.isCnpjValido(createRevendaDTO.cnpj())){
            throw new CnpjNotValidException("Cnpj não é valido");
        }
        Revenda newRevenda = new Revenda();
        newRevenda.setCnpj(createRevendaDTO.cnpj());
        newRevenda.setNomeSocial(createRevendaDTO.nomeSocial());
        this.revendaRepository.save(newRevenda);

    }

    @Override
    public List<RevendaDTO> getRevendas() throws NotAdminUserException {
        if(!this.jwtUtil.isAdmin()){
            throw new NotAdminUserException("Apenas Usuario Admin pode fazer essa ação.");
        }
        List<Revenda> allRevendas = this.revendaRepository.findAll();
        if(allRevendas.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhuma revenda Encontrada.");
        }
        return revendaMapper.toDTO(allRevendas);
    }

    @Override
    public RevendaDTO getRevenda() {
        UUID userIdFromToken = this.jwtUtil.getUserIdFromToken();
        User userById = this.userService.getUserById(userIdFromToken);
        System.out.println("my revenda: "+userById.getRevenda());
        return this.getRevendaById(userById.getRevenda().getId());
    }


    @Override
    public RevendaDTO getRevendaById(UUID revendaId) {
        Optional<Revenda> revendaById = this.revendaRepository.findById(revendaId);
        if(revendaById.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "revenda não Encontrada.");
        }
        return this.revendaMapper.toDTO(revendaById.get());
    }

}
