package com.luizlacerda.mobiauto_backend_202502.service;

import com.luizlacerda.mobiauto_backend_202502.controller.dto.RetornoCNPJApi;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class CnpjValidatorServiceImp implements CnpjValidatorService {

    @Value("${ApiUrl}")
    private String ApiUrl;
    @Value("${ApiCnpj.token}")
    private String token;
    private final WebClient webClient;

    public Boolean isCnpjValido(String cnpj) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(ApiUrl)
                        .queryParam("token",token)
                        .queryParam("value",cnpj)
                        .queryParam("type","cnpj")
                        .build())
                .retrieve()
                .bodyToMono(RetornoCNPJApi.class)
                .block().valid();
    }
}
