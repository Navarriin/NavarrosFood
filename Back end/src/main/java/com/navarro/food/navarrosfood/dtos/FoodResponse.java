package com.navarro.food.navarrosfood.dtos;

import com.navarro.food.navarrosfood.model.FoodEntity;

import java.math.BigDecimal;

public record FoodResponse(Long id, String name, String description, String image, BigDecimal value) {

    public FoodResponse(FoodEntity entity) {
        this(entity.getFoodNumber(), entity.getName(), entity.getDescription(), entity.getImage(), entity.getValue());
    }
}
