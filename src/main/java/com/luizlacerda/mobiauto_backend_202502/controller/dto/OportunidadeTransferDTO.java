package com.luizlacerda.mobiauto_backend_202502.controller.dto;

import java.util.UUID;

public record OportunidadeTransferDTO(UUID usuarioAssociado, UUID oportunidadeSelecionada) {
}
