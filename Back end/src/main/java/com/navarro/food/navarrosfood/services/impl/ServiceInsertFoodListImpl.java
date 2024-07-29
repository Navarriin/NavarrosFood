package com.navarro.food.navarrosfood.services.impl;

import com.navarro.food.navarrosfood.model.FoodEntity;
import com.navarro.food.navarrosfood.model.UserEntity;
import com.navarro.food.navarrosfood.repositories.RepositoryFood;
import com.navarro.food.navarrosfood.repositories.RepositoryUser;
import com.navarro.food.navarrosfood.services.ServiceInsertFoodList;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceInsertFoodListImpl implements ServiceInsertFoodList {

    private final RepositoryFood repositoryFood;
    private final RepositoryUser repositoryUser;

    public ServiceInsertFoodListImpl(RepositoryFood repositoryFood,
                                     RepositoryUser repositoryUser) {
        this.repositoryFood = repositoryFood;
        this.repositoryUser = repositoryUser;
    }

    @Override
    @Transactional
    public void insertFoods(List<Long> foodIds, UserEntity user) {
        List<FoodEntity> foods = this.repositoryFood.findAllFoodsById(foodIds);
        insertOrDeleteFoods(user, foods);
    }

    @Override
    @Transactional
    public void removeFoods(UserEntity user) {
        insertOrDeleteFoods(user, List.of());
    }

    private void insertOrDeleteFoods(UserEntity user, List<FoodEntity> foods) {
        this.repositoryUser.findByUsername(user.getUsername())
                .map(userEntity -> {
                    userEntity.setFoods(foods);
                    return userEntity;
                });
    }
}
