package com.luizlacerda.mobiauto_backend_202502.controller;

import com.luizlacerda.mobiauto_backend_202502.controller.dto.CreateRevendaDTO;
import com.luizlacerda.mobiauto_backend_202502.controller.dto.RevendaDTO;
import com.luizlacerda.mobiauto_backend_202502.exception.NotAdminUserException;
import com.luizlacerda.mobiauto_backend_202502.service.RevendaService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Api/v1/revenda")
public class RevendaController {

    private final RevendaService revendaService;

    @PostMapping
//    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Create", description = "Criar uma nova revenda", tags = "Revenda")
    public ResponseEntity<Void> createNewRevenda(@RequestBody CreateRevendaDTO createRevendaDTO){
        this.revendaService.createNewRevenda(createRevendaDTO);

        return ResponseEntity.ok().build();
    }

    // Apenas para ADMIN
    @GetMapping("/getAllRevendas")
    @Operation(summary = "Get", description = "retorna todas as revenda", tags = "Revenda")
    public ResponseEntity<List<RevendaDTO>> getAllRevendas() throws NotAdminUserException {
        List<RevendaDTO> revendaDTO = this.revendaService.getRevendas();
        return ResponseEntity.ok(revendaDTO);
    }

    @GetMapping("/getMyRevenda")
    @Operation(summary = "Get", description = "retorna sua revenda", tags = "Revenda")
    public ResponseEntity<RevendaDTO> getRevenda(){
        RevendaDTO revendaDTO = this.revendaService.getRevenda();
        return ResponseEntity.ok(revendaDTO);
    }

    @PostMapping("/getRevendaById/{revendaId}")
    @Operation(summary = "Post", description = "retorna revenda por id", tags = "Revenda")
    public ResponseEntity<RevendaDTO> getRevendaById(@RequestParam UUID revendaId){
        RevendaDTO revenda = revendaService.getRevendaById(revendaId);
        return ResponseEntity.ok(revenda);
    }


}
