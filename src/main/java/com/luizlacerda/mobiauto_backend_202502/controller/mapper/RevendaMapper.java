package com.luizlacerda.mobiauto_backend_202502.controller.mapper;

import com.luizlacerda.mobiauto_backend_202502.controller.dto.RevendaDTO;
import com.luizlacerda.mobiauto_backend_202502.entity.Revenda;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RevendaMapper {

    public Revenda toEntity(RevendaDTO revendaDTO){
        return new Revenda(revendaDTO.id(),revendaDTO.cnpj(),revendaDTO.nomeSocial());
    }

    public RevendaDTO toDTO(Revenda revenda){
        return new RevendaDTO(revenda.getId(), revenda.getCnpj(),revenda.getNomeSocial());
    }

    public List<Revenda> toEntity(List<RevendaDTO> listaRevendaDTO){
        return listaRevendaDTO.stream().map(this::toEntity).toList();
    }
    public List<RevendaDTO> toDTO(List<Revenda> listaRevenda){
        return listaRevenda.stream().map(this::toDTO).toList();
    }
}
