package com.luizlacerda.mobiauto_backend_202502.service;

import com.luizlacerda.mobiauto_backend_202502.Enum.Status;
import com.luizlacerda.mobiauto_backend_202502.entity.Oportunidade;
import com.luizlacerda.mobiauto_backend_202502.entity.UsuarioOportunidade;
import com.luizlacerda.mobiauto_backend_202502.repository.UsuarioOportunidadeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioOportunidadeServiceImp implements UsuarioOportunidadeService{

    private final UsuarioOportunidadeRepository usuarioOportunidadeRepository;


    @Override
    public void createUpdateUsuarioOportunidade(Oportunidade oportunidade) {
        Optional<UsuarioOportunidade> byAssistenteId = this.usuarioOportunidadeRepository.findByAssistente_Id(oportunidade.getUsuarioAssociado().getId());
        UsuarioOportunidade usuarioOportunidade;
        if(byAssistenteId.isPresent()){
            usuarioOportunidade = byAssistenteId.get();

            if (oportunidade.getStatus().equals(Status.EM_ATENDIMENTO)) {
                usuarioOportunidade.setOportunidadesEmAndamento(usuarioOportunidade.getOportunidadesEmAndamento() + 1);
            } else {
                usuarioOportunidade.setOportunidadesEmAndamento(usuarioOportunidade.getOportunidadesEmAndamento() - 1);
                usuarioOportunidade.setOportunidadesConcluidas(usuarioOportunidade.getOportunidadesConcluidas() + 1);
            }
        }else{
            usuarioOportunidade =
                    new UsuarioOportunidade(
                            oportunidade.getStatus().equals(Status.EM_ATENDIMENTO)  ? 1 : 0,
                            oportunidade.getStatus().equals(Status.CONCLUIDO) ? 1 : 0,
                            oportunidade.getUsuarioAssociado());
        }
        usuarioOportunidade.setRevenda(oportunidade.getRevenda());
        usuarioOportunidade.setUltimaOportunidade(LocalDateTime.now());
        this.usuarioOportunidadeRepository.save(usuarioOportunidade);
    }
}
