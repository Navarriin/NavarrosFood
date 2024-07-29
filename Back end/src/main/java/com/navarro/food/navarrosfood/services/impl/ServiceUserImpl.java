package com.navarro.food.navarrosfood.services.impl;

import com.navarro.food.navarrosfood.dtos.mapper.UserMapper;
import com.navarro.food.navarrosfood.dtos.user.UserRequestUpdate;
import com.navarro.food.navarrosfood.dtos.user.UserResponse;
import com.navarro.food.navarrosfood.enums.Status;
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
        return this.repositoryUser.findAll()
                .stream()
                .filter(data -> data.getStatus().equals(Status.ACTIVE))
                .map(this.userMapper::toResponse).toList();
    }

    public UserResponse getUserByUsername(String username) {
        return this.repositoryUser.findByUsername(username)
                .map(this.userMapper::toResponse)
                .orElseThrow((() -> this.userNotFound(username)));
    }

    @Override
    @Transactional
    public UserResponse updateUserByUsername(String username, UserRequestUpdate body) {
        return this.repositoryUser.findByUsername(username)
                .map(user -> {
                        user.setName(body.name());
                        user.setUsername(body.username());
                        user.setPassword(this.encodePassword(body.password()));
                        return this.userMapper.toResponse(user);
                }).orElseThrow(() -> this.userNotFound(username));
    }

    @Override
    @Transactional
    public void deleteUserByUsername(String username) {
        this.repositoryUser.findByUsername(username)
                .ifPresentOrElse(
                        data -> data.setStatus(Status.INACTIVE),
                        () -> { throw this.userNotFound(username); });
    }

    private String encodePassword(String password) {
        return this.passwordEncoder.encode(password);
    }

    private UserNotFound userNotFound(String username) {
        return new UserNotFound(String.format("User with username %s does not exist!", username));
    }
}
