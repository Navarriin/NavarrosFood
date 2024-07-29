package com.navarro.food.navarrosfood.dtos.food;

import com.navarro.food.navarrosfood.enums.Type;
import com.navarro.food.navarrosfood.model.FoodEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record FoodRequest(
        @NotNull @NotBlank @Size(min = 3,max = 60) String name,
        @NotNull @NotBlank String image,
        @NotNull BigDecimal value,
        @NotNull Type type
) {

    public FoodRequest(FoodEntity entity) {
        this(entity.getName(), entity.getImage(), entity.getValue(), entity.getType());
    }
}
