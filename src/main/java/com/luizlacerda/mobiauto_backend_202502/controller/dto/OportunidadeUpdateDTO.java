package com.luizlacerda.mobiauto_backend_202502.controller.dto;

import com.luizlacerda.mobiauto_backend_202502.Enum.Status;

import java.util.UUID;

public record OportunidadeUpdateDTO(UUID oportunidadeId, Status status, String motivoConclusao, UUID usuarioAssociado) {
}
