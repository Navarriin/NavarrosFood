package com.navarro.food.navarrosfood.model.Enums;

import lombok.Getter;

@Getter
public enum UserRole {

    ADMIN("admin"),
    USER("user");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }
}