package com.navarro.food.navarrosfood.services.impl;

import com.navarro.food.navarrosfood.dtos.UserRequestLogin;
import com.navarro.food.navarrosfood.dtos.UserRequestRegister;
import com.navarro.food.navarrosfood.dtos.UserResponse;
import com.navarro.food.navarrosfood.dtos.mapper.UserMapper;
import com.navarro.food.navarrosfood.exception.IncorrectPassword;
import com.navarro.food.navarrosfood.exception.UserNotFound;
import com.navarro.food.navarrosfood.model.UserEntity;
import com.navarro.food.navarrosfood.repositories.RepositoryUser;
import com.navarro.food.navarrosfood.services.UserRegistrationService;

import java.util.Objects;
import java.util.Optional;

public class UserRegistrationServiceImpl implements UserRegistrationService {

    private final RepositoryUser repositoryUser;
    private final UserMapper mapper;

    public UserRegistrationServiceImpl(UserMapper mapper, RepositoryUser repositoryUser) {
        this.mapper = mapper;
        this.repositoryUser = repositoryUser;
    }

    @Override
    public UserResponse login(UserRequestLogin request) {
        return this.repositoryUser.findByLogin(request.login())
                .map(user -> {
                        if(Objects.equals(user.getPassword(), request.password())) {
                            return new UserResponse(user.getName(), "foi em");
                        }
                    throw new IncorrectPassword("Incorrect password!");
                }).orElseThrow(
                        () -> new UserNotFound(String.format("User with login %s not found!", request.login())));
    }

    @Override
    public UserResponse register(UserRequestRegister request) {
        Optional<UserEntity> userById = this.repositoryUser.findByLogin(request.login());
        if(userById.isPresent()){
           throw new RuntimeException();
        } else {
            this.repositoryUser.save(this.mapper.toEntity(request));
            return new UserResponse(request.name(), "foi em");
        }
    }
}
