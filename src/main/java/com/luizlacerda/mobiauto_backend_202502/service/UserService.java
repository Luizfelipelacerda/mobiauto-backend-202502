package com.luizlacerda.mobiauto_backend_202502.service;

import com.luizlacerda.mobiauto_backend_202502.Enum.Cargo;
import com.luizlacerda.mobiauto_backend_202502.controller.dto.*;
import com.luizlacerda.mobiauto_backend_202502.entity.User;
import com.luizlacerda.mobiauto_backend_202502.exception.NotAdminUserException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface UserService {
    LoginResponse login(LoginRequest loginRequest);

    void createNewUser(CreateUserDTO createUserDTO);

    void updateUser(UserUpdateDTO userUpdateDTO);

    User getUserById(UUID userId);

    Boolean canCreateUser(Cargo authenticatedUserCargo, Cargo toBeUpdatedUserCargo);

    Boolean canUpdateUser(Cargo authenticatedUserCargo, Cargo toBeUpdatedUserCargo);

    List<UserReturnDTO> getAllUsers() throws NotAdminUserException;
}
