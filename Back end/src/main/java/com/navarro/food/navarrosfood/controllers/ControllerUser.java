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
    @GetMapping("/{username}")
    public ResponseEntity<UserResponse> getUserByLogin(@PathVariable String username) {
        return ResponseEntity.ok().body(this.serviceUser.getUserByUsername(username));
    }

    @Operation(description = "Método que atualiza o usuário pelo username.")
    @PutMapping("/{username}")
    public ResponseEntity<UserResponse> updateUserByLogin(
            @PathVariable String username, @Valid @RequestBody UserRequestUpdate body
    ) {
        return ResponseEntity.ok().body(this.serviceUser.updateUserByUsername(username, body));
    }

    @Operation(description = "Método que deleta o usuário pelo username.")
    @DeleteMapping("/{username}")
    public ResponseEntity<String> deleteUserByLogin(@PathVariable String username) {
        this.serviceUser.deleteUserByUsername(username);
        return ResponseEntity.ok().body(String.format("User with username %s has been successfully deleted!", username));
    }
}
