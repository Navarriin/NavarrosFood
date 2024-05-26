package com.navarro.food.navarrosfood.controllers;

import com.navarro.food.navarrosfood.dtos.FoodRequest;
import com.navarro.food.navarrosfood.dtos.FoodResponse;
import com.navarro.food.navarrosfood.services.ServiceFood;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/foods")
public class ControllerFood {

    private final ServiceFood serviceFood;

    public ControllerFood(ServiceFood serviceFood) {
        this.serviceFood = serviceFood;
    }

    @GetMapping
    public ResponseEntity<List<FoodResponse>> getAllFoods() {
        return ResponseEntity.ok().body(this.serviceFood.listAllFoods());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodResponse> getFoodById(@PathVariable Long id) {
        return ResponseEntity.ok().body(this.serviceFood.getFoodById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<FoodResponse> createFood(@Valid @RequestBody FoodRequest request) {
        return ResponseEntity.ok().body(this.serviceFood.createFood(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FoodResponse> updateFood(@PathVariable Long id, @Valid @RequestBody FoodRequest request) {
        return ResponseEntity.ok().body(this.serviceFood.updateFood(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFood(@PathVariable Long id) {
        this.serviceFood.deleteFoodById(id);
        return ResponseEntity.ok().build();
    }
}

