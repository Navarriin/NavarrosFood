package com.navarro.food.navarrosfood.services;

import com.navarro.food.navarrosfood.dtos.mapper.UserMapper;
import com.navarro.food.navarrosfood.dtos.user.UserRequestUpdate;
import com.navarro.food.navarrosfood.dtos.user.UserResponse;
import com.navarro.food.navarrosfood.exception.UserNotFound;
import com.navarro.food.navarrosfood.model.UserEntity;
import com.navarro.food.navarrosfood.repositories.RepositoryUser;
import com.navarro.food.navarrosfood.services.impl.ServiceUserImpl;
import com.navarro.food.navarrosfood.utils.Utils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class ServiceUserTest {

    @Mock
    private RepositoryUser repositoryUser;

    @Mock
    private UserMapper userMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private ServiceUserImpl serviceUser;

    private UserEntity userEntity;
    private UserResponse userResponse;
    private UserRequestUpdate userRequestUpdate;

    @BeforeEach
    void setUp() {
        this.userEntity = Utils.initUserEntity();
        this.userResponse = new UserResponse(this.userEntity);
        this.userRequestUpdate = Utils.initUserRequestUpdate();
    }

    @Test
    @DisplayName("Teste para sucesso de pegar todos usuários.")
    void getAllUsers() {
        when(this.repositoryUser.findAll()).thenReturn(List.of(this.userEntity));
        when(this.userMapper.toResponse(this.userEntity)).thenReturn(this.userResponse);

        var result = assertDoesNotThrow(() -> this.serviceUser.getAllUsers());

        assertNotNull(result);
        assertEquals(List.of(this.userResponse), result);
    }

    @Test
    @DisplayName("Teste para sucesso de pegar um unico usuário.")
    void getUserByLoginSuccess(){
        when(this.repositoryUser.findByLogin(this.userEntity.getLogin()))
                .thenReturn(Optional.ofNullable(this.userEntity));
        when(this.userMapper.toResponse(this.userEntity)).thenReturn(this.userResponse);

        var result = assertDoesNotThrow(() -> this.serviceUser.getUserByLogin(this.userEntity.getLogin()));


        assertNotNull(result);
        assertEquals(this.userResponse, result);
    }

    @Test
    @DisplayName("Teste para erro ao pegar um unico usuário.")
    void getUserByLoginError(){
        when(this.repositoryUser.findByLogin(this.userEntity.getLogin())).thenReturn(Optional.empty());

        var result = assertThrows(UserNotFound.class, () -> this.serviceUser.getUserByLogin(this.userEntity.getLogin()));

        assertEquals(String.format("User with login %s does not exist!", this.userEntity.getLogin()), result.getMessage());
        verify(this.repositoryUser, times(1)).findByLogin(this.userEntity.getLogin());
    }

    @Test
    @DisplayName("Teste para sucesso ao atualizar algum usuário")
    void updateUserByLoginSuccess() {
        when(this.repositoryUser.findByLogin(this.userEntity.getLogin()))
                .thenReturn(Optional.ofNullable(this.userEntity));
        when(this.userMapper.toResponse(this.userEntity)).thenReturn(this.userResponse);
        when(this.passwordEncoder.encode(this.userRequestUpdate.password())).thenReturn(anyString());

        var result = assertDoesNotThrow(
                () -> this.serviceUser.updateUserByLogin(this.userEntity.getLogin(), this.userRequestUpdate));

        assertNotNull(result);
        assertEquals(this.userResponse, result);
    }

    @Test
    @DisplayName("Teste para erro ao atualizar algum usuário(not found)")
    void updateUserByLoginErrorNotFound() {
        when(this.repositoryUser.findByLogin(this.userEntity.getLogin())).thenReturn(Optional.empty());

        var result = assertThrows(UserNotFound.class,
                () -> this.serviceUser.updateUserByLogin(this.userEntity.getLogin(), this.userRequestUpdate));

        assertEquals(String.format("User with login %s does not exist!", this.userEntity.getLogin()), result.getMessage());
        verify(this.repositoryUser, times(1)).findByLogin(this.userEntity.getLogin());
    }

    @Test
    @DisplayName("Teste para sucesso ao deletar um usuário.")
    void deleteUserByLoginSuccess() {
        when(this.repositoryUser.findByLogin(this.userEntity.getLogin()))
                .thenReturn(Optional.ofNullable(this.userEntity));

        assertDoesNotThrow(() -> this.serviceUser.deleteUserByLogin(this.userEntity.getLogin()));

        verify(this.repositoryUser, times(1)).findByLogin(this.userEntity.getLogin());
        verify(this.repositoryUser, times(1)).delete(this.userEntity);
    }

    @Test
    @DisplayName("Teste para erro ao deletar um user.")
    void deleteUserByLoginError() {
        when(this.repositoryUser.findByLogin(this.userEntity.getLogin())).thenReturn(Optional.empty());

        var result = assertThrows(UserNotFound.class,
                () -> this.serviceUser.deleteUserByLogin(this.userEntity.getLogin()));

        assertEquals(String.format("User with login %s does not exist!", this.userEntity.getLogin()), result.getMessage());
        verify(this.repositoryUser, times(1)).findByLogin(this.userEntity.getLogin());
    }
}