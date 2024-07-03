package com.navarro.food.navarrosfood.dtos.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRequestLogin(
        @NotNull @NotBlank @Size(max = 30) String login,
        @NotNull @NotBlank @Size(max = 20) String password) {
}
