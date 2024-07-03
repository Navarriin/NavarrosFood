package com.navarro.food.navarrosfood.services.impl;

import com.navarro.food.navarrosfood.dtos.food.InsertFoodsRequest;
import com.navarro.food.navarrosfood.dtos.mapper.UserMapper;
import com.navarro.food.navarrosfood.dtos.user.UserResponse;
import com.navarro.food.navarrosfood.enums.Status;
import com.navarro.food.navarrosfood.exception.FoodNotFound;
import com.navarro.food.navarrosfood.exception.UserNotFound;
import com.navarro.food.navarrosfood.model.FoodEntity;
import com.navarro.food.navarrosfood.repositories.RepositoryFood;
import com.navarro.food.navarrosfood.repositories.RepositoryUser;
import com.navarro.food.navarrosfood.services.ServiceInsertFoodList;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceInsertFoodListImpl implements ServiceInsertFoodList {

    private final UserMapper userMapper;
    private final RepositoryFood repositoryFood;
    private final RepositoryUser repositoryUser;

    public ServiceInsertFoodListImpl(
            UserMapper userMapper,
            RepositoryFood repositoryFood,
            RepositoryUser repositoryUser
    ) {
        this.userMapper = userMapper;
        this.repositoryFood = repositoryFood;
        this.repositoryUser = repositoryUser;
    }

    @Override
    @Transactional
    public UserResponse insertFoods(String login, InsertFoodsRequest request) {
        return this.repositoryUser.findByLogin(login)
                .map(user -> {
                    user.setFoods(this.selectFoods(request.ids()));
                    return this.userMapper.toResponse(user);
                }).orElseThrow(
                        () -> new UserNotFound(String.format("User with login %s not found!", login)));
    }

    @Override
    public List<FoodEntity> selectFoods(List<Long> ids) {
        return ids.stream()
                .map(id -> this.repositoryFood.getFoodById(id, Status.ACTIVE)
                .orElseThrow(() -> new FoodNotFound(String.format("Food with id %d not found!", id))))
                .toList();
    }
}
