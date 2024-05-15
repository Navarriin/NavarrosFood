package com.navarro.food.navarrosfood.services;

import com.navarro.food.navarrosfood.dtos.UserRequestLogin;
import com.navarro.food.navarrosfood.dtos.UserRequestRegister;
import com.navarro.food.navarrosfood.dtos.UserResponse;

public interface UserRegistrationService {

    UserResponse login(UserRequestLogin request);
    UserResponse register(UserRequestRegister request);
}
