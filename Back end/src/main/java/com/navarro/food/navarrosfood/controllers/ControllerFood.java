package com.navarro.food.navarrosfood.controllers;

import com.navarro.food.navarrosfood.dtos.food.FoodRequest;
import com.navarro.food.navarrosfood.dtos.food.FoodResponse;
import com.navarro.food.navarrosfood.services.ServiceFood;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        List<FoodResponse> foods = this.serviceFood.listAllFoods();
        return ResponseEntity.ok().body(foods);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodResponse> getFoodById(@PathVariable Long id) {
        FoodResponse food = this.serviceFood.getFoodById(id);
        return ResponseEntity.ok().body(food);
    }

   @PostMapping
    public ResponseEntity<FoodResponse> createFood(@RequestBody @Valid FoodRequest request) {
        FoodResponse newFood = this.serviceFood.createFood(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(newFood);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FoodResponse> updateFood(@PathVariable Long id,
                                                    @Valid @RequestBody FoodRequest request) {
        FoodResponse food = this.serviceFood.updateFood(id, request);
        return ResponseEntity.ok().body(food);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFoodById(@PathVariable Long id) {
        this.serviceFood.deleteFoodById(id);
        return ResponseEntity.ok().body(String.format("Food with id %d successfully deleted!", id));
    }
}
