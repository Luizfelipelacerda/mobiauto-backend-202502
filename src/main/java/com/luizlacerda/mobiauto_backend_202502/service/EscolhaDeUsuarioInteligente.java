package com.luizlacerda.mobiauto_backend_202502.service;

import com.luizlacerda.mobiauto_backend_202502.entity.User;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface EscolhaDeUsuarioInteligente {

    User escolhaDeUsuario(UUID revendaId);


}
