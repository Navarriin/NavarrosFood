package com.navarro.food.navarrosfood.services.impl;

import com.navarro.food.navarrosfood.dtos.UserRequestLogin;
import com.navarro.food.navarrosfood.dtos.UserRequestRegister;
import com.navarro.food.navarrosfood.dtos.UserResponse;
import com.navarro.food.navarrosfood.repositories.RepositoryUser;
import com.navarro.food.navarrosfood.services.UserRegistrationService;

import java.util.Objects;

public class UserRegistrationServiceImpl implements UserRegistrationService {

    private final RepositoryUser repositoryUser;

    public UserRegistrationServiceImpl(RepositoryUser repositoryUser) {
        this.repositoryUser = repositoryUser;
    }

    @Override
    public UserResponse login(UserRequestLogin request) {
        return this.repositoryUser.findByLogin(request.login())
                .map(food -> {
                        if(Objects.equals(food.getPassword(), request.password())) {
                            return new UserResponse(food.getName(), "foi em");
                        }
                    throw new RuntimeException();
                }).orElseThrow(RuntimeException::new);
    }

    @Override
    public UserResponse register(UserRequestRegister request) {
        return null;
    }
}
