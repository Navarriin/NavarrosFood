package com.navarro.food.navarrosfood.model.DTOs;

import com.navarro.food.navarrosfood.model.FoodEntity;

import java.math.BigDecimal;

public record FoodResponse(String name, String description, String image, BigDecimal value) {

    public FoodResponse(FoodEntity entity) {
        this(entity.getName(), entity.getDescription(), entity.getImage(), entity.getValue());
    }
}
