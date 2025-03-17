package com.luizlacerda.mobiauto_backend_202502.service;

import org.springframework.stereotype.Service;

@Service
public interface CnpjValidatorService {

    public Boolean isCnpjValido(String cnpj);
}
