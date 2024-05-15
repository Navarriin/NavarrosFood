package com.navarro.food.navarrosfood.services;

import com.navarro.food.navarrosfood.dtos.FoodRequest;
import com.navarro.food.navarrosfood.dtos.FoodResponse;

import java.util.List;

public interface ServiceFood {
    List<FoodResponse> listAllFoods();
    FoodResponse getFoodById(Long id);
    FoodResponse createFood(FoodRequest request);
    FoodResponse updateFood(Long id, FoodRequest request);
    void deleteFoodById(Long id);
}
