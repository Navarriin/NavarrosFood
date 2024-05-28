package com.navarro.food.navarrosfood.infra.controllers;

import com.navarro.food.navarrosfood.dtos.user.UserResponse;
import com.navarro.food.navarrosfood.services.ServiceUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class ControllerUser {

    private final ServiceUser serviceUser;

    public ControllerUser(ServiceUser serviceUser) {
        this.serviceUser = serviceUser;
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok().body(this.serviceUser.getAllUsers());
    }

    @GetMapping("/{login}")
    public ResponseEntity<UserResponse> getUserByLogin(@PathVariable String login) {
        return ResponseEntity.ok().body(this.serviceUser.getUserByLogin(login));
    }
}
