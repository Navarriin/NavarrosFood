package com.navarro.food.navarrosfood.services.impl;

import com.navarro.food.navarrosfood.dtos.UserRequestLogin;
import com.navarro.food.navarrosfood.dtos.UserRequestRegister;
import com.navarro.food.navarrosfood.dtos.UserResponse;
import com.navarro.food.navarrosfood.dtos.mapper.UserMapper;
import com.navarro.food.navarrosfood.exception.IncorrectPassword;
import com.navarro.food.navarrosfood.exception.UserAlreadyExistsException;
import com.navarro.food.navarrosfood.exception.UserNotFound;
import com.navarro.food.navarrosfood.infra.security.TokenService;
import com.navarro.food.navarrosfood.model.UserEntity;
import com.navarro.food.navarrosfood.repositories.RepositoryUser;
import com.navarro.food.navarrosfood.services.UserRegistrationService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {

    private final UserMapper mapper;
    private final TokenService tokenService;
    private final RepositoryUser repositoryUser;
    private final PasswordEncoder passwordEncoder;

    public UserRegistrationServiceImpl(
                UserMapper mapper,
                TokenService tokenService,
                RepositoryUser repositoryUser,
                PasswordEncoder passwordEncoder) {
        this.mapper = mapper;
        this.tokenService = tokenService;
        this.repositoryUser = repositoryUser;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponse login(UserRequestLogin request) {
        return this.repositoryUser.findByLogin(request.login())
                .map(user -> {
                        if(passwordEncoder.matches(request.password(), user.getPassword())) {
                            return new UserResponse(user.getName(), this.generateToken(user));
                        }
                        throw new IncorrectPassword("Incorrect password!");
                }).orElseThrow(
                        () -> new UserNotFound(String.format("User with login %s not found!", request.login())));
    }

    @Override
    public UserResponse register(UserRequestRegister request) {
        this.repositoryUser.findByLogin(request.login())
            .ifPresent(user -> {
                throw new UserAlreadyExistsException(String.format("User with login %s already exists.", user.getLogin()));
            });

        String passwordEncoded = this.passwordEncoder.encode(request.password());
        UserEntity user = this.mapper.toEntity(request, passwordEncoded);
        user = this.repositoryUser.save(user);

        return new UserResponse(request.name(), generateToken(user));
    }

    private String generateToken(UserEntity user) {
        return this.tokenService.generateToken(user);
    }
}
