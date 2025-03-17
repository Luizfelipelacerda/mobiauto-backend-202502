package com.luizlacerda.mobiauto_backend_202502.controller;

import com.luizlacerda.mobiauto_backend_202502.controller.dto.CreateUserDTO;
import com.luizlacerda.mobiauto_backend_202502.controller.dto.UserReturnDTO;
import com.luizlacerda.mobiauto_backend_202502.controller.dto.UserUpdateDTO;
import com.luizlacerda.mobiauto_backend_202502.exception.NotAdminUserException;
import com.luizlacerda.mobiauto_backend_202502.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Api/v1/User")
public class UserController {

    private final UserService userService;

    @GetMapping("/AllUsers")
    @Operation(summary = "Get", description = "Retorna todos os usuarios", tags = "Usuario")
    public ResponseEntity<List<UserReturnDTO>> getAllUser() throws NotAdminUserException {
        List<UserReturnDTO> allUsers = this.userService.getAllUsers();
        return ResponseEntity.ok(allUsers);
    }

    @PostMapping("/createUser")
    @Operation(summary = "Create", description = "Criar um novo usuario", tags = "Usuario")
    public ResponseEntity<Void> newUser(@RequestBody CreateUserDTO createUserDTO){
        this.userService.createNewUser(createUserDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping()
    @Operation(summary = "update", description = "edita um usuario", tags = "Usuario")
    public ResponseEntity<Void> updateUser(@RequestBody UserUpdateDTO userUpdateDTO){

        this.userService.updateUser(userUpdateDTO);

        return ResponseEntity.ok().build();
    }
}
