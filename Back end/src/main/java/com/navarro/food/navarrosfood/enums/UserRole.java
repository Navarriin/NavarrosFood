package com.navarro.food.navarrosfood.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRole {
    ADMIN("admin"), USER("user");

    private final String role;
}