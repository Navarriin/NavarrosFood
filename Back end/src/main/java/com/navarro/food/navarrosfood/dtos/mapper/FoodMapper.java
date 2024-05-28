package com.navarro.food.navarrosfood.dtos.mapper;

import com.navarro.food.navarrosfood.dtos.food.FoodRequest;
import com.navarro.food.navarrosfood.dtos.food.FoodResponse;
import com.navarro.food.navarrosfood.model.FoodEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class FoodMapper {

    public FoodEntity toEntity(FoodRequest request) {
        if(Objects.isNull(request)) return null;

        return new FoodEntity(request.name(), request.description(), request.image(), request.value());
    }

    public FoodResponse toResponse(FoodEntity entity) {
        if(Objects.isNull(entity)) return null;

        return new FoodResponse(entity);
    }
}
