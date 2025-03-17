package com.luizlacerda.mobiauto_backend_202502.service;

import com.luizlacerda.mobiauto_backend_202502.controller.dto.CreateRevendaDTO;
import com.luizlacerda.mobiauto_backend_202502.controller.dto.RevendaDTO;
import com.luizlacerda.mobiauto_backend_202502.entity.Revenda;
import com.luizlacerda.mobiauto_backend_202502.exception.NotAdminUserException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface RevendaService {

    public void createNewRevenda(CreateRevendaDTO createRevendaDTO);


    List<RevendaDTO> getRevendas() throws NotAdminUserException;

    RevendaDTO getRevenda();

    RevendaDTO getRevendaById(UUID revendaId);
}
