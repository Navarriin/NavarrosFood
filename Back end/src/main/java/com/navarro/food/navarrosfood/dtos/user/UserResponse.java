package com.navarro.food.navarrosfood.dtos.user;

import com.navarro.food.navarrosfood.enums.Status;
import com.navarro.food.navarrosfood.enums.UserRole;
import com.navarro.food.navarrosfood.model.UserEntity;

public record UserResponse(String id, String name, String login, String password, UserRole role, Status status) {

    public UserResponse(UserEntity entity) {
        this(entity.getId(),
            entity.getName(),
            entity.getLogin(),
            entity.getPassword(),
            entity.getRole(),
            entity.getStatus()
        );
    }
}
