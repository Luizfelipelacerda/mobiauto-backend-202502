package com.luizlacerda.mobiauto_backend_202502.service;

import com.luizlacerda.mobiauto_backend_202502.controller.dto.OportunidadeDTO;
import com.luizlacerda.mobiauto_backend_202502.controller.dto.OportunidadeInsertDTO;
import com.luizlacerda.mobiauto_backend_202502.controller.dto.OportunidadeTransferDTO;
import com.luizlacerda.mobiauto_backend_202502.controller.dto.OportunidadeUpdateDTO;
import com.luizlacerda.mobiauto_backend_202502.entity.Oportunidade;
import com.luizlacerda.mobiauto_backend_202502.exception.NotAdminUserException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OportunidadeService {
    List<OportunidadeDTO> getAllOportunidades() throws NotAdminUserException;

    void atendeOportunidade(OportunidadeUpdateDTO oportunidadeUpdateDTO);

    void transferirOportunidade(OportunidadeTransferDTO oportunidadeTransferDTO);

    List<Oportunidade> inserirOportunidades(List<OportunidadeInsertDTO> oportunidadeInsertDTOS);

    Oportunidade inserirOportunidade(OportunidadeInsertDTO oportunidadeInsertDTO);
}
