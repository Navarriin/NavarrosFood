package com.navarro.food.navarrosfood.services;

import com.navarro.food.navarrosfood.repositories.RepositoryUser;
import com.navarro.food.navarrosfood.services.impl.UserRegistrationServiceImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class UserRegistrationServiceTest {

    @Mock
    private RepositoryUser repositoryUser;

    @InjectMocks
    private UserRegistrationServiceImpl userRegistrationService;
}
