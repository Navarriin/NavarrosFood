package com.navarro.food.navarrosfood.dtos.mapper;

import com.navarro.food.navarrosfood.dtos.user.UserRequestRegister;
import com.navarro.food.navarrosfood.dtos.user.UserResponse;
import com.navarro.food.navarrosfood.model.UserEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserMapper {

    public UserEntity toEntity(UserRequestRegister register, String password) {
        if (Objects.isNull(register)) return null;

        return new UserEntity(register.name(), register.username(), password, register.role());
    }

    public UserResponse toResponse(UserEntity entity) {
        if (Objects.isNull(entity)) return null;

        return new UserResponse(entity);
    }
}
