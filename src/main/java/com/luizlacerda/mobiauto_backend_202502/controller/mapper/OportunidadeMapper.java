package com.luizlacerda.mobiauto_backend_202502.controller.mapper;

import com.luizlacerda.mobiauto_backend_202502.controller.dto.OportunidadeDTO;
import com.luizlacerda.mobiauto_backend_202502.controller.dto.OportunidadeInsertDTO;
import com.luizlacerda.mobiauto_backend_202502.controller.dto.OportunidadeUpdateDTO;
import com.luizlacerda.mobiauto_backend_202502.entity.Cliente;
import com.luizlacerda.mobiauto_backend_202502.entity.Oportunidade;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OportunidadeMapper {

//    public Oportunidade toEntity(OportunidadeInsertDTO oportunidadeDTO){
//        return new Oportunidade(
//                oportunidadeDTO.oportunidadeDTO().status()
//                ,oportunidadeDTO.oportunidadeDTO().revenda()
//                ,oportunidadeDTO.oportunidadeDTO().usuarioAssociado()
//                ,oportunidadeDTO.cliente()
//                ,oportunidadeDTO.veiculo()
//        );
//    }


    public OportunidadeDTO toDTO(Oportunidade oportunidade){
        return new OportunidadeDTO(
                oportunidade.getStatus(),
                oportunidade.getRevenda().getId(),
                oportunidade.getRevenda().getCnpj(),
                oportunidade.getUsuarioAssociado().getId(),
                oportunidade.getUsuarioAssociado().getEmail(),
                oportunidade.getDataInsersao(),
                oportunidade.getDataAlteracao());
    }

    public List<OportunidadeDTO> toDTO(List<Oportunidade> oportunidades){
        return oportunidades.stream().map(this::toDTO).toList();
    }
}
