package com.luizlacerda.mobiauto_backend_202502.repository;

import com.luizlacerda.mobiauto_backend_202502.Enum.Status;
import com.luizlacerda.mobiauto_backend_202502.entity.Oportunidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OportunidadeRepository extends JpaRepository<Oportunidade, UUID> {


    Optional<Oportunidade> findByOportunidadeIdAndStatusNot(UUID oportunidadeId, Status status);

    List<Oportunidade> findAllByUsuarioAssociadoId(UUID id);

    List<Oportunidade> findAllByRevendaId(UUID id);
}
