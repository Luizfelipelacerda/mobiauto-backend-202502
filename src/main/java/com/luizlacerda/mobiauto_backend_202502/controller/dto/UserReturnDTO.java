package com.luizlacerda.mobiauto_backend_202502.controller.dto;

import com.luizlacerda.mobiauto_backend_202502.Enum.Cargo;
import com.luizlacerda.mobiauto_backend_202502.entity.Oportunidade;
import com.luizlacerda.mobiauto_backend_202502.entity.Revenda;

import java.util.List;
import java.util.UUID;

public record UserReturnDTO(String userName, String email, Cargo cargo, UUID revenda) {
}
