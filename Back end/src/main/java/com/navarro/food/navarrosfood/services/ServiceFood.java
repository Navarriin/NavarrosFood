package com.navarro.food.navarrosfood.services;

import com.navarro.food.navarrosfood.model.DTOs.FoodResponse;

import java.util.List;

public interface ServiceFood {
    List<FoodResponse> listAllFoods();
}
