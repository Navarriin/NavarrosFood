package com.navarro.food.navarrosfood.controllers;

import com.navarro.food.navarrosfood.model.UserEntity;
import com.navarro.food.navarrosfood.services.ServiceInsertFoodList;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class ControllerInsertFoods {

    private final ServiceInsertFoodList service;

    public ControllerInsertFoods(ServiceInsertFoodList service) {
        this.service = service;
    }

    @PostMapping("insert")
    public ResponseEntity<String> insertFoods(@RequestBody List<Long> ids,
                                              @AuthenticationPrincipal UserEntity user) {
        this.service.insertFoods(ids, user);
        return ResponseEntity.ok().body("Foods inserted successfully!");
    }

    @PostMapping("remove")
    public ResponseEntity<String> removeFoods(@AuthenticationPrincipal UserEntity user) {
        this.service.removeFoods(user);
        return ResponseEntity.ok().body("Foods removed successfully!");
    }
}
