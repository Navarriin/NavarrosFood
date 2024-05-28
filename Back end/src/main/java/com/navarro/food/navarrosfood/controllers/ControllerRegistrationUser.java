package com.navarro.food.navarrosfood.controllers;

import com.navarro.food.navarrosfood.dtos.user.UserRequestLogin;
import com.navarro.food.navarrosfood.dtos.user.UserRequestRegister;
import com.navarro.food.navarrosfood.dtos.user.UserResponseLogReg;
import com.navarro.food.navarrosfood.services.UserRegistrationService;
import com.navarro.food.navarrosfood.services.impl.UserRegistrationServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class ControllerRegistrationUser {

    private final UserRegistrationService userRegistrationService;

    public ControllerRegistrationUser(UserRegistrationServiceImpl userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }

    @PostMapping("login")
    public ResponseEntity<UserResponseLogReg> userLogin(@RequestBody @Valid UserRequestLogin login) {
        return ResponseEntity.ok().body(this.userRegistrationService.login(login));
    }

    @PostMapping("register")
    public ResponseEntity<UserResponseLogReg> userRegister(@RequestBody @Valid UserRequestRegister register) {
        return ResponseEntity.ok().body(this.userRegistrationService.register(register));
    }
}
