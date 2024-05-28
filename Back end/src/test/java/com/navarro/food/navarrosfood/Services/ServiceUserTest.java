package com.navarro.food.navarrosfood.services;

import com.navarro.food.navarrosfood.dtos.mapper.UserMapper;
import com.navarro.food.navarrosfood.dtos.user.UserResponse;
import com.navarro.food.navarrosfood.model.UserEntity;
import com.navarro.food.navarrosfood.repositories.RepositoryUser;
import com.navarro.food.navarrosfood.services.impl.ServiceUserImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class ServiceUserTest {

    @Mock
    private RepositoryUser repositoryUser;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private ServiceUserImpl serviceUser;

    private UserEntity userEntity;
    private UserResponse userResponse;

    @BeforeEach
    void setUp() {
        this.userEntity = new UserEntity();
        this.userResponse = new UserResponse(this.userEntity);
    }

    @Test
    @DisplayName("Teste para sucesso do metodo de pegar todos usuários.")
    void getAllUsers() {
        when(this.repositoryUser.findAll()).thenReturn(List.of(this.userEntity));
        when(this.userMapper.toResponse(this.userEntity)).thenReturn(this.userResponse);

        var result = assertDoesNotThrow(() -> this.serviceUser.getAllUsers());

        assertNotNull(result);
        assertEquals(List.of(this.userResponse), result);
    }
}