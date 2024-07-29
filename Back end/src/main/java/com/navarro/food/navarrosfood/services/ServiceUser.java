package com.navarro.food.navarrosfood.services;

import com.navarro.food.navarrosfood.dtos.user.UserRequestRegister;
import com.navarro.food.navarrosfood.dtos.user.UserRequestUpdate;
import com.navarro.food.navarrosfood.dtos.user.UserResponse;
import java.util.List;
import java.util.Optional;

public interface ServiceUser {
    List<UserResponse> getAllUsers();
    UserResponse getUserByUsername(String username);
    UserResponse updateUserByUsername(String username, UserRequestUpdate body);
    void deleteUserByUsername(String login);
}
