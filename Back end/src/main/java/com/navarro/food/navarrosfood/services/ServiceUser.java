package com.navarro.food.navarrosfood.services;

import com.navarro.food.navarrosfood.dtos.user.UserResponse;
import java.util.List;
import java.util.Optional;

public interface ServiceUser {
    List<UserResponse> getAllUsers();
    UserResponse getUserByLogin(String login);
}
