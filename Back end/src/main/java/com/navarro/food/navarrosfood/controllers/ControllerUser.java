package com.navarro.food.navarrosfood.controllers;

import com.navarro.food.navarrosfood.dtos.user.UserRequestUpdate;
import com.navarro.food.navarrosfood.dtos.user.UserResponse;
import com.navarro.food.navarrosfood.services.ServiceUser;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class ControllerUser {

    private final ServiceUser serviceUser;

    public ControllerUser(ServiceUser serviceUser) {
        this.serviceUser = serviceUser;
    }

    @Operation(description = "Método que lista todos os usuários.")
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok().body(this.serviceUser.getAllUsers());
    }

    @Operation(description = "Método que retorna um unico usuário.")
    @GetMapping("/{login}")
    public ResponseEntity<UserResponse> getUserByLogin(@PathVariable String login) {
        return ResponseEntity.ok().body(this.serviceUser.getUserByLogin(login));
    }

    @Operation(description = "Método que atualiza o usuário pelo login.")
    @PutMapping("/{login}")
    public ResponseEntity<UserResponse> updateUserByLogin(
            @PathVariable String login, @Valid @RequestBody UserRequestUpdate body
    ) {
        return ResponseEntity.ok().body(this.serviceUser.updateUserByLogin(login, body));
    }

    @Operation(description = "Método que deleta o usuário pelo login.")
    @DeleteMapping("/{login}")
    public ResponseEntity<String> deleteUserByLogin(@PathVariable String login) {
        this.serviceUser.deleteUserByLogin(login);
        return ResponseEntity.ok().body(String.format("User with login %s has been successfully deleted!", login));
    }
}
