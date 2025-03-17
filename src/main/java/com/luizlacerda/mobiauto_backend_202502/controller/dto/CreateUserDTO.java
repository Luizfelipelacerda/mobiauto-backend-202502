package com.luizlacerda.mobiauto_backend_202502.controller.dto;

import com.luizlacerda.mobiauto_backend_202502.Enum.Cargo;

import java.util.UUID;

public record CreateUserDTO(String nome, String email, String senha, UUID revenda, Cargo cargo) {
}
