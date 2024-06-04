package com.navarro.food.navarrosfood.dtos.user;

import com.navarro.food.navarrosfood.enums.UserRole;
import com.navarro.food.navarrosfood.model.FoodEntity;
import com.navarro.food.navarrosfood.model.UserEntity;

import java.util.List;
import java.util.UUID;

public record UserResponse(UUID id, String name, String login, String password, UserRole role, List<FoodEntity> foods) {

    public UserResponse(UserEntity entity) {
        this(entity.getId(),
            entity.getName(),
            entity.getLogin(),
            entity.getPassword(),
            entity.getRole(),
            entity.getFoods()
        );
    }
}
