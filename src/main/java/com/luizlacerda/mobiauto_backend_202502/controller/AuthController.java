package com.luizlacerda.mobiauto_backend_202502.controller;

import com.luizlacerda.mobiauto_backend_202502.controller.dto.CreateUserDTO;
import com.luizlacerda.mobiauto_backend_202502.controller.dto.LoginRequest;
import com.luizlacerda.mobiauto_backend_202502.controller.dto.LoginResponse;
import com.luizlacerda.mobiauto_backend_202502.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/Api/v1/Auth")
public class AuthController {

    private final UserService userService;


    //Sign up
    @PostMapping("/signup")
    @Operation(summary = "Create", description = "Criar um novo usuario", tags = "Authentication")
    public ResponseEntity<Void> newUser(@RequestBody CreateUserDTO createUserDTO){
        this.userService.createNewUser(createUserDTO);
        return ResponseEntity.ok().build();
    }

    //Login
    @PostMapping("/login")
    @Operation(summary = "Login", description = "Loga com o usuario é recebe um token", tags = "Authentication")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        LoginResponse loginResponse = this.userService.login(loginRequest);
        return ResponseEntity.ok(loginResponse);
    }


}
