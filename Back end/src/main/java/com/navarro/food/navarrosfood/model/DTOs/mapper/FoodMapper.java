package com.navarro.food.navarrosfood.model.DTOs.mapper;

import com.navarro.food.navarrosfood.model.DTOs.FoodResponse;
import com.navarro.food.navarrosfood.model.FoodEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class FoodMapper {

    public FoodResponse toResponse(FoodEntity entity) {
        if(Objects.isNull(entity)) return null;

        return new FoodResponse(entity);
    }
}
