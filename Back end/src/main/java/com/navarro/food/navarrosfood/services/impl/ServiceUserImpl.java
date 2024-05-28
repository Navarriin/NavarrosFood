package com.navarro.food.navarrosfood.services.impl;

import com.navarro.food.navarrosfood.dtos.mapper.UserMapper;
import com.navarro.food.navarrosfood.dtos.user.UserResponse;
import com.navarro.food.navarrosfood.repositories.RepositoryUser;
import com.navarro.food.navarrosfood.services.ServiceUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceUserImpl implements ServiceUser {

    private final UserMapper userMapper;
    private final RepositoryUser repositoryUser;

    public ServiceUserImpl(UserMapper userMapper, RepositoryUser repositoryUser) {
        this.userMapper = userMapper;
        this.repositoryUser = repositoryUser;
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return this.repositoryUser.findAll().stream().map(userMapper::toResponse).toList();
    }
}
