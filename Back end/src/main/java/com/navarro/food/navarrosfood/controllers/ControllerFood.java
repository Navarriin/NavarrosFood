package com.navarro.food.navarrosfood.controllers;

import com.navarro.food.navarrosfood.dtos.food.FoodRequest;
import com.navarro.food.navarrosfood.dtos.food.FoodResponse;
import com.navarro.food.navarrosfood.services.ServiceFood;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
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

    @Operation(description = "Método que lista todas as comidas.")
    @GetMapping
    public ResponseEntity<List<FoodResponse>> getAllFoods() {
        return ResponseEntity.ok().body(this.serviceFood.listAllFoods());
    }

    @Operation(description = "Método que retorna uma única comida pelo id.")
    @GetMapping("/{id}")
    public ResponseEntity<FoodResponse> getFoodById(@PathVariable Long id) {
        return ResponseEntity.ok().body(this.serviceFood.getFoodById(id));
    }

    @Operation(description = "Método que cria uma comida e salva no db.")
    @PostMapping
    public ResponseEntity<FoodResponse> createFood(@Valid @RequestBody FoodRequest request) {
        return ResponseEntity.ok().body(this.serviceFood.createFood(request));
    }

    @Operation(description = "Método que atualiza uma comida existente.")
    @PutMapping("/{id}")
    public ResponseEntity<FoodResponse> updateFood(@PathVariable Long id, @Valid @RequestBody FoodRequest request) {
        return ResponseEntity.ok().body(this.serviceFood.updateFood(id, request));
    }

    @Operation(description = "Método que deleta uma comida do db pelo id.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFood(@PathVariable Long id) {
        this.serviceFood.deleteFoodById(id);
        return ResponseEntity.ok().build();
    }
}

