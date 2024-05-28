package com.navarro.food.navarrosfood.services;

import com.navarro.food.navarrosfood.dtos.user.UserResponse;
import java.util.List;

public interface ServiceUser {
    List<UserResponse> getAllUsers();
}
