package com.luizlacerda.mobiauto_backend_202502.controller;

import com.luizlacerda.mobiauto_backend_202502.controller.dto.OportunidadeDTO;
import com.luizlacerda.mobiauto_backend_202502.controller.dto.OportunidadeInsertDTO;
import com.luizlacerda.mobiauto_backend_202502.controller.dto.OportunidadeTransferDTO;
import com.luizlacerda.mobiauto_backend_202502.controller.dto.OportunidadeUpdateDTO;
import com.luizlacerda.mobiauto_backend_202502.entity.Oportunidade;
import com.luizlacerda.mobiauto_backend_202502.exception.NotAdminUserException;
import com.luizlacerda.mobiauto_backend_202502.service.OportunidadeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/Api/v1/oportunidade")
public class OportunidadeController {

    private final OportunidadeService oportunidadeService;

    @GetMapping()
    @Operation(summary = "Get", description = "Get Oportunidades", tags = "Oportunidade")
    public ResponseEntity<List<OportunidadeDTO>> getOportunidades() throws NotAdminUserException {
        List<OportunidadeDTO> allOportunidades = this.oportunidadeService.getAllOportunidades();
        return ResponseEntity.ok(allOportunidades);
    }

    @PostMapping("/inserirOportunidade")
    @Operation(summary = "Insert", description = "Inseri Oportunidade", tags = "Oportunidade")
    public ResponseEntity<Oportunidade> insertOportunidade(@RequestBody OportunidadeInsertDTO oportunidadeInsertDTO){
        Oportunidade oportunidade = this.oportunidadeService.inserirOportunidade(oportunidadeInsertDTO);
        return ResponseEntity.status(201).body(oportunidade);
    }

    @PostMapping("/inserirOportunidades")
    @Operation(summary = "Insert", description = "Inseri Oportunidades", tags = "Oportunidade")
    public ResponseEntity<List<Oportunidade>> insertOportunidades(@RequestBody List<OportunidadeInsertDTO> oportunidadeInsertDTOS){
        List<Oportunidade> oportunidades = this.oportunidadeService.inserirOportunidades(oportunidadeInsertDTOS);
        return ResponseEntity.status(201).body(oportunidades);
    }

    @PutMapping("/transferirOportunidade")
    @Operation(summary = "Update", description = "Transfere Oportunidade para outro Usuario", tags = "Oportunidade")
    public ResponseEntity<Void> transferirOportunidade(@RequestBody OportunidadeTransferDTO oportunidadeTransferDTO){
        this.oportunidadeService.transferirOportunidade(oportunidadeTransferDTO);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/AtendimentoDeOportunidade")
    @Operation(summary = "Update", description = "Assistente Atende Oportunidade", tags = "Oportunidade")
    public ResponseEntity<Void> AtendimentoDeOportunidade(@RequestBody OportunidadeUpdateDTO oportunidadeUpdateDTO){
        this.oportunidadeService.atendeOportunidade(oportunidadeUpdateDTO);
        return ResponseEntity.ok().build();
    }
//
//    @PutMapping("")
//    @Operation(summary = "Update", description = "Edita Oportunidade", tags = "Oportunidade")
//    public ResponseEntity<Void> editarOportunidade(OportunidadeUpdateDTO oportunidadeUpdateDTO){
//
//    }
}
