package com.navarro.food.navarrosfood.dtos;

import com.navarro.food.navarrosfood.enums.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRequestRegister(
        @NotNull @NotBlank String name,
        @NotNull @NotBlank @Size(max = 30) String login,
        @NotNull @NotBlank @Size(max = 20) String password,
        @NotNull UserRole role) {
}
