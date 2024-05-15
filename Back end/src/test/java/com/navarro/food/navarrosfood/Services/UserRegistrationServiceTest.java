package com.navarro.food.navarrosfood.services;

import com.navarro.food.navarrosfood.dtos.UserRequestLogin;
import com.navarro.food.navarrosfood.dtos.UserResponse;
import com.navarro.food.navarrosfood.repositories.RepositoryUser;
import com.navarro.food.navarrosfood.services.impl.UserRegistrationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class UserRegistrationServiceTest {

    @Mock
    private RepositoryUser repository;

    @InjectMocks
    private UserRegistrationServiceImpl userRegistrationService;

    private UserRequestLogin requestLogin;
    private UserResponse userResponse;

    @BeforeEach
    void setUp(){
        this.requestLogin = new UserRequestLogin("login", "password");
        this.userResponse = new UserResponse("Gabriel", "senha criptografada");
    }

    @Test
    @DisplayName("Teste de successo ao efetuar login")
    void loginSuccess(){
        when(this.repository.findByLogin(this.requestLogin.login())).thenReturn(this.userResponse);

        var result = assertDoesNotThrow(() -> this.userRegistrationService.login(this.requestLogin));

        assertEquals("Gabriel", this.userResponse.name());
    }
}
