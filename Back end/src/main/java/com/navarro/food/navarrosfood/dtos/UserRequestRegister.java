package com.navarro.food.navarrosfood.dtos;

import com.navarro.food.navarrosfood.enums.UserRole;

public record UserRequestRegister(String id, String login, String password, UserRole role) {
}
