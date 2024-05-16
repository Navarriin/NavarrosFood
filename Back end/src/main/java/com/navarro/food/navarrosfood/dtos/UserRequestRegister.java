package com.navarro.food.navarrosfood.dtos;

import com.navarro.food.navarrosfood.enums.UserRole;

public record UserRequestRegister(String name, String login, String password, UserRole role) {
}
