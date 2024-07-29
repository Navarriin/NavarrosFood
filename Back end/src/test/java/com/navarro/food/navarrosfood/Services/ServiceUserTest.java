package com.navarro.food.navarrosfood.services;

import com.navarro.food.navarrosfood.dtos.mapper.UserMapper;
import com.navarro.food.navarrosfood.dtos.user.UserRequestUpdate;
import com.navarro.food.navarrosfood.dtos.user.UserResponse;
import com.navarro.food.navarrosfood.exception.UserNotFound;
import com.navarro.food.navarrosfood.model.UserEntity;
import com.navarro.food.navarrosfood.repositories.RepositoryUser;
import com.navarro.food.navarrosfood.services.impl.ServiceUserImpl;
import com.navarro.food.navarrosfood.utils.Utils;
import org.junit.jupiter.api.BeforeAll;
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

    private static UserEntity userEntity;
    private static UserResponse userResponse;
    private static UserRequestUpdate userRequestUpdate;

    @BeforeAll
    static void setUp() {
        userEntity = Utils.initUserEntity();
        userResponse = new UserResponse(userEntity);
        userRequestUpdate = Utils.initUserRequestUpdate();
    }

    @Test
    @DisplayName("Teste para sucesso de pegar todos usuários.")
    void getAllUsers() {
        when(this.repositoryUser.findAll()).thenReturn(List.of(userEntity));
        when(this.userMapper.toResponse(userEntity)).thenReturn(userResponse);

        var result = assertDoesNotThrow(() -> this.serviceUser.getAllUsers());

        assertNotNull(result);
        assertEquals(List.of(userResponse), result);
    }

    @Test
    @DisplayName("Teste para sucesso de pegar um unico usuário.")
    void getUserByLoginSuccess(){
        when(this.repositoryUser.findByUsername(userEntity.getUsername()))
                .thenReturn(Optional.ofNullable(userEntity));
        when(this.userMapper.toResponse(userEntity)).thenReturn(userResponse);

        var result = assertDoesNotThrow(() -> this.serviceUser.getUserByUsername(userEntity.getUsername()));

        assertNotNull(result);
        assertEquals(userResponse, result);
    }

    @Test
    @DisplayName("Teste para erro ao pegar um unico usuário.")
    void getUserByLoginError(){
        when(this.repositoryUser.findByUsername(userEntity.getUsername())).thenReturn(Optional.empty());

        var result = assertThrows(UserNotFound.class, () -> this.serviceUser.getUserByUsername(userEntity.getUsername()));

        assertEquals(String.format("User with login %s does not exist!", userEntity.getUsername()), result.getMessage());
        verify(this.repositoryUser, times(1)).findByUsername(userEntity.getUsername());
    }

    @Test
    @DisplayName("Teste para sucesso ao atualizar algum usuário")
    void updateUserByLoginSuccess() {
        when(this.repositoryUser.findByUsername(userEntity.getUsername()))
                .thenReturn(Optional.ofNullable(userEntity));
        when(this.userMapper.toResponse(userEntity)).thenReturn(userResponse);
        when(this.passwordEncoder.encode(userRequestUpdate.password())).thenReturn(anyString());

        var result = assertDoesNotThrow(
                () -> this.serviceUser.updateUserByLogin(userEntity.getUsername(), userRequestUpdate));

        assertNotNull(result);
        assertEquals(userResponse, result);
    }

    @Test
    @DisplayName("Teste para erro ao atualizar algum usuário(not found)")
    void updateUserByLoginErrorNotFound() {
        when(this.repositoryUser.findByUsername(userEntity.getUsername())).thenReturn(Optional.empty());

        var result = assertThrows(UserNotFound.class,
                () -> this.serviceUser.updateUserByLogin(userEntity.getUsername(), userRequestUpdate));

        assertEquals(String.format("User with login %s does not exist!", userEntity.getUsername()), result.getMessage());
        verify(this.repositoryUser, times(1)).findByUsername(userEntity.getUsername());
    }

    @Test
    @DisplayName("Teste para sucesso ao deletar um usuário.")
    void deleteUserByLoginSuccess() {
        when(this.repositoryUser.findByUsername(userEntity.getUsername()))
                .thenReturn(Optional.ofNullable(userEntity));

        assertDoesNotThrow(() -> this.serviceUser.deleteUserByLogin(userEntity.getUsername()));

        verify(this.repositoryUser, times(1)).findByUsername(userEntity.getUsername());
        verify(this.repositoryUser, times(1)).delete(userEntity);
    }

    @Test
    @DisplayName("Teste para erro ao deletar um user.")
    void deleteUserByLoginError() {
        when(this.repositoryUser.findByUsername(userEntity.getUsername())).thenReturn(Optional.empty());

        var result = assertThrows(UserNotFound.class,
                () -> this.serviceUser.deleteUserByLogin(userEntity.getUsername()));

        assertEquals(String.format("User with login %s does not exist!", userEntity.getUsername()), result.getMessage());
        verify(this.repositoryUser, times(1)).findByUsername(userEntity.getUsername());
    }
}