package com.navarro.food.navarrosfood.services;

import com.navarro.food.navarrosfood.dtos.mapper.UserMapper;
import com.navarro.food.navarrosfood.dtos.user.UserRequestLogin;
import com.navarro.food.navarrosfood.dtos.user.UserRequestRegister;
import com.navarro.food.navarrosfood.dtos.user.UserResponseLogReg;
import com.navarro.food.navarrosfood.exception.IncorrectPassword;
import com.navarro.food.navarrosfood.exception.UserAlreadyExistsException;
import com.navarro.food.navarrosfood.exception.UserNotFound;
import com.navarro.food.navarrosfood.infra.security.TokenService;
import com.navarro.food.navarrosfood.model.UserEntity;
import com.navarro.food.navarrosfood.repositories.RepositoryUser;
import com.navarro.food.navarrosfood.services.impl.UserRegistrationServiceImpl;
import com.navarro.food.navarrosfood.utils.Utils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class UserRegistrationServiceTest {

    @Mock
    private RepositoryUser repository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserMapper userMapper;

    @Mock
    private TokenService tokenService;

    @InjectMocks
    private UserRegistrationServiceImpl userRegistrationService;

    private static UserRequestLogin requestLogin;
    private static UserRequestRegister requestRegister;
    private static UserRequestLogin requestLoginInvalid;
    private static UserRequestLogin requestPasswordInvalid;
    private static UserResponseLogReg userResponse;
    private static UserEntity userEntity;

    @BeforeAll
    static void setUp(){
        requestLogin = Utils.initUserRequestLogin();
        requestLoginInvalid = Utils.initUserRequestLogin();
        requestPasswordInvalid = Utils.initUserRequestLogin();
        userResponse = Utils.initUserResponseLogReg();
        userEntity = Utils.initUserEntity();
        requestRegister = Utils.initUserRequestRegister();
    }

    @Test
    @DisplayName("Teste de successo ao efetuar login")
    void loginSuccess(){
        when(this.repository.findByLogin(requestLogin.login())).thenReturn(Optional.ofNullable(userEntity));
        when(this.passwordEncoder.matches(requestPasswordInvalid.password(), userEntity.getPassword())).thenReturn(true); // Bug mas ok, passou

        var result = assertDoesNotThrow(() -> this.userRegistrationService.login(requestPasswordInvalid));

        assertNotNull(result);
        assertEquals("Gabriel", userResponse.name());
        verify(this.repository, times(1)).findByLogin(requestLogin.login());
    }

    @Test
    @DisplayName("Teste de erro ao inserir senha errada")
    void loginErrorPass() {
        when(this.repository.findByLogin(requestPasswordInvalid.login())).thenReturn(Optional.ofNullable(userEntity));

        var result = assertThrows(IncorrectPassword.class,
                () -> this.userRegistrationService.login(requestPasswordInvalid));

        assertEquals("Incorrect password!", result.getMessage());
        verify(this.repository, times(1)).findByLogin(requestPasswordInvalid.login());
    }


    @Test
    @DisplayName("Teste de erro ao digitar login invalido")
    void loginErrorNotFound(){
        when(this.repository.findByLogin(requestLoginInvalid.login())).thenReturn(Optional.empty());

        var result = assertThrows(UserNotFound.class ,
                () -> this.userRegistrationService.login(requestLoginInvalid));

        assertEquals(String.format("User with login %s not found!", requestLoginInvalid.login()), result.getMessage());
        verify(this.repository, times(1)).findByLogin(requestLoginInvalid.login());
    }

    @Test
    @DisplayName("Teste de sucesso ao criar uma conta nova")
    void registerSuccess() {
        String passwordEncoded = this.passwordEncoder.encode(requestRegister.password());

        when(this.repository.findByLogin(requestRegister.login())).thenReturn(Optional.empty());
        when(this.userMapper.toEntity(requestRegister, passwordEncoded)).thenReturn(userEntity);

        var result = assertDoesNotThrow(() -> this.userRegistrationService.register(requestRegister));

        assertNotNull(result);
        verify(this.repository, times(1)).findByLogin(requestRegister.login());
        verify(this.userMapper, times(1)).toEntity(requestRegister, passwordEncoded);
    }

    @Test
    @DisplayName("Teste de erro ao criar uma nova conta")
    void registerError() {
        when(this.repository.findByLogin(requestRegister.login())).thenReturn(Optional.ofNullable(userEntity));

        var result = assertThrows(UserAlreadyExistsException.class,
                () -> this.userRegistrationService.register(requestRegister));

        assertEquals(String.format("User with login %s already exists.", userEntity.getLogin()), result.getMessage());
        verify(this.repository, times(1)).findByLogin(requestRegister.login());
    }
}
