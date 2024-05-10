package com.navarro.food.navarrosfood.model.DTOs;

import com.navarro.food.navarrosfood.model.FoodEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record FoodRequest(
        @NotNull @NotBlank @Size(min = 3,max = 60) String name,
        @NotNull @NotBlank @Size(max = 300) String description,
        @NotNull @NotBlank String image,
        @NotNull BigDecimal value) {

    public FoodRequest(FoodEntity entity) {
        this(entity.getName(), entity.getDescription(), entity.getImage(), entity.getValue());
    }
}
