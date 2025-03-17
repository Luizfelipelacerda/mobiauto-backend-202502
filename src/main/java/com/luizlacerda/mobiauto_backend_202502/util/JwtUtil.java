package com.luizlacerda.mobiauto_backend_202502.util;

import com.luizlacerda.mobiauto_backend_202502.Enum.Cargo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class JwtUtil {

    public Cargo getUserCargoFromToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getPrincipal() instanceof Jwt jwt) {
            return Cargo.valueOf(jwt.getClaim("role"));
        }
        throw new IllegalStateException("Token JWT inválido");
    }

    public UUID getUserIdFromToken(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getPrincipal() instanceof Jwt jwt) {
            return UUID.fromString(jwt.getSubject());
        }
        throw new IllegalStateException("Token JWT inválido");
    }

    public Boolean isAdmin(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof Jwt jwt) {
            return jwt.getClaim("role").equals(Cargo.ADMIN.name());
        }
        throw new IllegalStateException("Token JWT inválido");
    }
}
