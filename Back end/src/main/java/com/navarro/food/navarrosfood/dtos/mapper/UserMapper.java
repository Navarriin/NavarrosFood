package com.navarro.food.navarrosfood.dtos.mapper;

import com.navarro.food.navarrosfood.dtos.UserRequestRegister;
import com.navarro.food.navarrosfood.model.UserEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserMapper {

    public UserEntity toEntity(UserRequestRegister register) {
        if (Objects.isNull(register)) return null;

        return new UserEntity(register.name(), register.login(), register.password(), register.role());
    }
}
