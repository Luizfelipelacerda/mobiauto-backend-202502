package com.luizlacerda.mobiauto_backend_202502.controller.dto;

import com.luizlacerda.mobiauto_backend_202502.Enum.Status;
import jakarta.persistence.Column;

import java.time.LocalDateTime;
import java.util.UUID;

public record OportunidadeDTO(Status status, UUID revenda,String cnpj, UUID usuarioAssociado, String email, LocalDateTime dataInsersao, LocalDateTime dataAlteracao) {
}