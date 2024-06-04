package com.navarro.food.navarrosfood.services;
import com.navarro.food.navarrosfood.dtos.food.InsertFoodsRequest;
import com.navarro.food.navarrosfood.dtos.user.UserResponse;
import com.navarro.food.navarrosfood.model.FoodEntity;

import java.util.List;

public interface ServiceInsertFoodList {

    List<FoodEntity> selectFoods(List<Long> idFoods);
    UserResponse insertFoods(String login, InsertFoodsRequest request);
}
