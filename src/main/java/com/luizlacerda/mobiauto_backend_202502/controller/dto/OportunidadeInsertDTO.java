package com.luizlacerda.mobiauto_backend_202502.controller.dto;

import com.luizlacerda.mobiauto_backend_202502.Enum.Status;

import java.util.UUID;

public record OportunidadeInsertDTO(UUID revenda, String emailAssociado, String cnpj, ClienteDTO cliente, VeiculoDTO veiculo) {
}
