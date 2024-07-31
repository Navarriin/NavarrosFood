package com.navarro.food.navarrosfood.services;
import com.navarro.food.navarrosfood.model.UserEntity;

import java.util.List;

public interface ServiceInsertFoodList {

    void insertFoods(List<Long> foodIds, UserEntity user);
    void removeFoods(UserEntity user);
}
