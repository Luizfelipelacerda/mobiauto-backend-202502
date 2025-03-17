package com.luizlacerda.mobiauto_backend_202502.repository;

import com.luizlacerda.mobiauto_backend_202502.entity.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, UUID> {
}
