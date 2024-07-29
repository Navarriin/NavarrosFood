package com.navarro.food.navarrosfood.controllers;

import com.navarro.food.navarrosfood.dtos.user.UserRequestLogin;
import com.navarro.food.navarrosfood.dtos.user.UserRequestRegister;
import com.navarro.food.navarrosfood.dtos.user.UserResponseLogReg;
import com.navarro.food.navarrosfood.services.UserRegistrationService;
import com.navarro.food.navarrosfood.services.impl.UserRegistrationServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ControllerRegistrationUser {

    private final UserRegistrationService userRegistrationService;

    public ControllerRegistrationUser(UserRegistrationServiceImpl userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }

    @Operation(description = "Método que faz o login do usuário e retorna o token de acesso.")
    @PostMapping("login")
    public ResponseEntity<UserResponseLogReg> userLogin(@RequestBody @Valid UserRequestLogin login) {
        return ResponseEntity.ok().body(this.userRegistrationService.login(login));
    }

    @Operation(description = "Método que cadastra um novo usuário e retorna o token de acesso.")
    @PostMapping("register")
    public ResponseEntity<UserResponseLogReg> userRegister(@RequestBody @Valid UserRequestRegister register) {
        return ResponseEntity.ok().body(this.userRegistrationService.register(register));
    }
}
