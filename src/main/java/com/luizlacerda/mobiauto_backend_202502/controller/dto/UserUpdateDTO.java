package com.luizlacerda.mobiauto_backend_202502.controller.dto;

import com.luizlacerda.mobiauto_backend_202502.Enum.Cargo;

import java.util.UUID;

public record UserUpdateDTO(UUID userId, String userName, String email, String password, Cargo cargo, UUID revenda) {
}
