package com.navarro.food.navarrosfood.controllers;

import com.navarro.food.navarrosfood.dtos.UserRequestLogin;
import com.navarro.food.navarrosfood.dtos.UserRequestRegister;
import com.navarro.food.navarrosfood.dtos.UserResponse;
import com.navarro.food.navarrosfood.services.impl.UserRegistrationServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class ControllerUser {

    private final UserRegistrationServiceImpl userRegistrationService;

    public ControllerUser(UserRegistrationServiceImpl userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }

    @PostMapping("login")
    public ResponseEntity<UserResponse> userLogin(@RequestBody @Valid UserRequestLogin login) {
        return ResponseEntity.ok().body(this.userRegistrationService.login(login));
    }

    @PostMapping("register")
    public ResponseEntity<UserResponse> userRegister(@RequestBody @Valid UserRequestRegister register) {
        return ResponseEntity.ok().body(this.userRegistrationService.register(register));
    }
}
