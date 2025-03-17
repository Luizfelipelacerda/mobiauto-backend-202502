package com.luizlacerda.mobiauto_backend_202502.controller.dto;

import com.luizlacerda.mobiauto_backend_202502.entity.User;

import java.util.List;
import java.util.UUID;

public record RevendaDTO(UUID id, String cnpj, String nomeSocial) {
}
