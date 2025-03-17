package com.luizlacerda.mobiauto_backend_202502.service;

import com.luizlacerda.mobiauto_backend_202502.entity.Oportunidade;
import org.springframework.stereotype.Service;

@Service
public interface UsuarioOportunidadeService {

    void createUpdateUsuarioOportunidade(Oportunidade oportunidade);
}
