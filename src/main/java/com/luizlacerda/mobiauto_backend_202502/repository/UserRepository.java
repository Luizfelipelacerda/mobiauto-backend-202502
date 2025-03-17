package com.luizlacerda.mobiauto_backend_202502.repository;

import com.luizlacerda.mobiauto_backend_202502.Enum.Cargo;
import com.luizlacerda.mobiauto_backend_202502.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByUserName(String username);

        Optional<User> findByEmail(String email);

    Optional<List<User>> findAllByRevenda_IdAndCargo(UUID revendaId, Cargo cargo);
}
