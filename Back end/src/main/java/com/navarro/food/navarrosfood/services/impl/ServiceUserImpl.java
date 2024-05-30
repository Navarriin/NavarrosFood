package com.navarro.food.navarrosfood.services.impl;

import com.navarro.food.navarrosfood.dtos.mapper.UserMapper;
import com.navarro.food.navarrosfood.dtos.user.UserRequestUpdate;
import com.navarro.food.navarrosfood.dtos.user.UserResponse;
import com.navarro.food.navarrosfood.exception.UserNotFound;
import com.navarro.food.navarrosfood.repositories.RepositoryUser;
import com.navarro.food.navarrosfood.services.ServiceUser;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceUserImpl implements ServiceUser {

    private final UserMapper userMapper;
    private final RepositoryUser repositoryUser;
    private final PasswordEncoder passwordEncoder;

    public ServiceUserImpl(UserMapper userMapper, RepositoryUser repositoryUser, PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.repositoryUser = repositoryUser;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return this.repositoryUser.findAll().stream().map(this.userMapper::toResponse).toList();
    }

    @Override
    public UserResponse getUserByLogin(String login) {
        return this.repositoryUser.findByLogin(login)
                .map(this.userMapper::toResponse)
                .orElseThrow((() -> this.userNotFound(login)));
    }

    @Override
    @Transactional
    public UserResponse updateUserByLogin(String login, UserRequestUpdate body) {
        return this.repositoryUser.findByLogin(login)
                .map(user -> {
                        user.setName(body.name());
                        user.setLogin(body.login());
                        user.setPassword(this.encodePassword(body.password()));
                        return this.userMapper.toResponse(user);
                }).orElseThrow(() -> this.userNotFound(login));
    }

    @Override
    public void deleteUserByLogin(String login) {
        this.repositoryUser.findByLogin(login)
                .ifPresentOrElse(this.repositoryUser::delete, () -> { throw this.userNotFound(login); });
    }

    private String encodePassword(String password) {
        return this.passwordEncoder.encode(password);
    }

    private UserNotFound userNotFound(String login) {
        return new UserNotFound(String.format("User com login %s não existe!", login));
    }
}
