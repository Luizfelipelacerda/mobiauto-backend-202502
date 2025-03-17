package com.luizlacerda.mobiauto_backend_202502.repository;

import com.luizlacerda.mobiauto_backend_202502.entity.Revenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RevendaRepository extends JpaRepository<Revenda, UUID> {

    Optional<Revenda> findByCnpj(String cnpj);

}
