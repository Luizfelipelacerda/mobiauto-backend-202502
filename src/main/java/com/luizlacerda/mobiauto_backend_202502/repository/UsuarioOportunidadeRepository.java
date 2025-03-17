package com.luizlacerda.mobiauto_backend_202502.repository;

import com.luizlacerda.mobiauto_backend_202502.entity.UsuarioOportunidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioOportunidadeRepository extends JpaRepository<UsuarioOportunidade, UUID> {

    Optional<UsuarioOportunidade> findByAssistente_Id(UUID ass);

    Optional<List<UsuarioOportunidade>> findAllByRevenda_Id(UUID revendaId);
}
