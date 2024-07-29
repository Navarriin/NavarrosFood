package com.navarro.food.navarrosfood.dtos.food;

import com.navarro.food.navarrosfood.enums.Type;
import com.navarro.food.navarrosfood.model.FoodEntity;

import java.math.BigDecimal;

public record FoodResponse(Long id, String name, String image, BigDecimal value, Type type) {

    public FoodResponse(FoodEntity entity) {
        this(entity.getFoodNumber(), entity.getName(), entity.getImage(), entity.getValue(), entity.getType());
    }
}
