package com.navarro.food.navarrosfood.controllers;

import com.navarro.food.navarrosfood.dtos.food.InsertFoodsRequest;
import com.navarro.food.navarrosfood.dtos.user.UserResponse;
import com.navarro.food.navarrosfood.services.ServiceInsertFoodList;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users/insert")
public class ControllerInsertFoodList {

    private final ServiceInsertFoodList serviceInsertFoodList;

    public ControllerInsertFoodList(ServiceInsertFoodList serviceInsertFoodList) {
        this.serviceInsertFoodList = serviceInsertFoodList;
    }

    @Operation(description = "Método para setar novas foods em um User.")
    @PostMapping("/{login}")
    public ResponseEntity<UserResponse> insertFood(
            @PathVariable String login,
            @RequestBody InsertFoodsRequest foodEntityList) {
        return ResponseEntity.ok().body(this.serviceInsertFoodList.insertFoods(login, foodEntityList));
    }
}
