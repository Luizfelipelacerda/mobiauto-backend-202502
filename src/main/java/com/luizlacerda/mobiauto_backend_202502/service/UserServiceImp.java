package com.luizlacerda.mobiauto_backend_202502.service;

import com.luizlacerda.mobiauto_backend_202502.Enum.Cargo;
import com.luizlacerda.mobiauto_backend_202502.controller.dto.*;
import com.luizlacerda.mobiauto_backend_202502.controller.mapper.UserMapper;
import com.luizlacerda.mobiauto_backend_202502.entity.Revenda;
import com.luizlacerda.mobiauto_backend_202502.entity.User;
import com.luizlacerda.mobiauto_backend_202502.exception.CannotCreateUpdateUserException;
import com.luizlacerda.mobiauto_backend_202502.exception.NotAdminUserException;
import com.luizlacerda.mobiauto_backend_202502.exception.RevendaNotFoundException;
import com.luizlacerda.mobiauto_backend_202502.exception.UserDoesntExistException;
import com.luizlacerda.mobiauto_backend_202502.repository.RevendaRepository;
import com.luizlacerda.mobiauto_backend_202502.repository.UserRepository;
import com.luizlacerda.mobiauto_backend_202502.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.*;

@RequiredArgsConstructor
@Service
public class UserServiceImp implements UserService {

    private final JwtEncoder jwtEncoder;

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final RevendaRepository revendaRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    public LoginResponse login(LoginRequest loginRequest) {
        Optional<User> user = userRepository.findByEmail(loginRequest.login());
        if(user.isEmpty() || !user.get().isLoginCorrect(loginRequest, passwordEncoder)){
            throw new BadCredentialsException("login ou senha invalidos");
        }

        var now = Instant.now();
        var expiresIn = 1200L;
        var claims = JwtClaimsSet.builder()
                .issuer("mobiauto-backend")
                .subject(user.get().getId().toString())
                .claim("role",user.get().getCargo().name())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .build();

        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();


        return new LoginResponse(jwtValue, expiresIn);

    }

    @Override
    public void createNewUser(CreateUserDTO createUserDTO) {
        Optional<User> userOptional = this.userRepository.findByUserName(createUserDTO.nome());
        if(userOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        Cargo UserCargoFromToken = this.jwtUtil.getUserCargoFromToken();
        if(!this.canCreateUser(UserCargoFromToken, createUserDTO.cargo())){
            throw new CannotCreateUpdateUserException("Usuario com cargo "+UserCargoFromToken+" não pode criar/alterar um usuario de cargo "+createUserDTO.cargo());
        }

        Optional<User> usuario = this.userRepository.findById(this.jwtUtil.getUserIdFromToken());
        if(usuario.isEmpty()){
            throw new UserDoesntExistException("Usuario não encontrado, faço login novamente.");
        }

        User user = new User();
        user.setUserName(createUserDTO.nome());
        user.setEmail(createUserDTO.email());
        user.setCargo(createUserDTO.cargo());

        if(usuario.get().getCargo().equals(Cargo.ADMIN)){
            Optional<Revenda> revendaById = this.revendaRepository.findById(createUserDTO.revenda());
            if(revendaById.isEmpty()){
                throw new RevendaNotFoundException("revenda não encotrada");
            }
            user.setRevenda(revendaById.get());

        }else{
            user.setRevenda(user.getRevenda());
        }
        user.setPassword(this.passwordEncoder.encode(createUserDTO.senha()));

        this.userRepository.save(user);
    }

    @Override
    public void updateUser(UserUpdateDTO userUpdateDTO) {
        Optional<User> userOptional = this.userRepository.findById(userUpdateDTO.userId());
        if(userOptional.isEmpty()){
            throw new UserDoesntExistException("Usuario não encontrado ou não existe.");
        }
        Cargo UserCargoFromToken = this.jwtUtil.getUserCargoFromToken();
        if(!this.canUpdateUser(UserCargoFromToken, userUpdateDTO.cargo())){
            throw new CannotCreateUpdateUserException("Usuario com cargo "+UserCargoFromToken+" não pode criar/alterar um usuario de cargo "+userUpdateDTO.cargo());
        }

        User userUpdate = userOptional.get();
        userUpdate.setUserName(userUpdateDTO.userName());
        userUpdate.setEmail(userUpdateDTO.email());
        userUpdate.setPassword(this.passwordEncoder.encode(userUpdateDTO.password()));
        userUpdate.setCargo(userUpdateDTO.cargo());

        Optional<Revenda> revendaById = this.revendaRepository.findById(userUpdateDTO.revenda());
        if(revendaById.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "revenda Encontrada.");
        }
        userUpdate.setRevenda(revendaById.get());

        this.userRepository.save(userUpdate);

    }

    public User getUserById(UUID userId){
        Optional<User> byId = this.userRepository.findById(userId);
        if(byId.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario not found.");
        }
        return byId.get();

    }


    public Boolean canCreateUser(Cargo authenticatedUserCargo, Cargo toBeUpdatedUserCargo){
        if(authenticatedUserCargo.name().equals(Cargo.ADMIN.name())){
            return true;
        }
        if(authenticatedUserCargo.name().equals(Cargo.PROPRIETARIO) &&
                (toBeUpdatedUserCargo.name().equals(Cargo.GERENTE) || toBeUpdatedUserCargo.name().equals(Cargo.ASSISTENTE))){
            return true;
        }
        if(authenticatedUserCargo.name().equals(Cargo.GERENTE) &&
                toBeUpdatedUserCargo.name().equals(Cargo.ASSISTENTE)){
            return true;
        }
        return false;
    }

    public Boolean canUpdateUser(Cargo authenticatedUserCargo, Cargo toBeUpdatedUserCargo){
        if(authenticatedUserCargo.name().equals(Cargo.ADMIN.name())){
            return true;
        }
        if(authenticatedUserCargo.name().equals(Cargo.PROPRIETARIO) &&
                (toBeUpdatedUserCargo.name().equals(Cargo.GERENTE) || toBeUpdatedUserCargo.name().equals(Cargo.ASSISTENTE))){
            return true;
        }
        return false;
    }

    @Override
    public List<UserReturnDTO> getAllUsers() throws NotAdminUserException {
        Cargo UserCargoFromToken = this.jwtUtil.getUserCargoFromToken();
        if(!UserCargoFromToken.equals(Cargo.ADMIN)){
            throw new NotAdminUserException("Apenas Admins podem acessar essas informações.");
        }
        List<User> allUsersList = this.userRepository.findAll();
        return this.userMapper.toDTO(allUsersList);
    }

}
