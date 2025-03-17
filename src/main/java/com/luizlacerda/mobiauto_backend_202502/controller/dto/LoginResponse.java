package com.luizlacerda.mobiauto_backend_202502.controller.dto;

public record LoginResponse(String accessToken, Long expiredIn) {
}
