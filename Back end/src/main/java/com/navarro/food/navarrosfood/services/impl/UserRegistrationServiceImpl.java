package com.navarro.food.navarrosfood.services.impl;

import com.navarro.food.navarrosfood.dtos.user.UserRequestLogin;
import com.navarro.food.navarrosfood.dtos.user.UserRequestRegister;
import com.navarro.food.navarrosfood.dtos.user.UserResponseLogReg;
import com.navarro.food.navarrosfood.dtos.mapper.UserMapper;
import com.navarro.food.navarrosfood.exception.IncorrectPassword;
import com.navarro.food.navarrosfood.exception.UserAlreadyExistsException;
import com.navarro.food.navarrosfood.exception.NotFound;
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
    public UserResponseLogReg login(UserRequestLogin request) {
        return this.repositoryUser.findByUsername(request.username())
                .map(user -> {
                        if(this.passwordEncoder.matches(request.password(), user.getPassword())) {
                            return new UserResponseLogReg(user.getName(), this.generateToken(user));
                        }
                        throw new IncorrectPassword("Incorrect password!");
                }).orElseThrow(
                        () -> new NotFound(String.format("User with login %s not found!", request.username())));
    }

    @Override
    public UserResponseLogReg register(UserRequestRegister request) {
        this.repositoryUser.findByUsername(request.username())
            .ifPresent(user -> {
                throw new UserAlreadyExistsException(String.format("User with login %s already exists.", user.getUsername()));
            });

        String passwordEncoded = this.passwordEncoder.encode(request.password());
        UserEntity user = this.mapper.toEntity(request, passwordEncoded);
        user = this.repositoryUser.save(user);

        return new UserResponseLogReg(request.name(), this.generateToken(user));
    }

    private String generateToken(UserEntity user) {
        return this.tokenService.generateToken(user);
    }
}
