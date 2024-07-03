package com.navarro.food.navarrosfood.services;

import com.navarro.food.navarrosfood.dtos.user.UserRequestLogin;
import com.navarro.food.navarrosfood.dtos.user.UserRequestRegister;
import com.navarro.food.navarrosfood.dtos.user.UserResponseLogReg;

public interface UserRegistrationService {
    UserResponseLogReg login(UserRequestLogin request);
    UserResponseLogReg register(UserRequestRegister request);
}
