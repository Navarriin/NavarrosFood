package com.navarro.food.navarrosfood.model.Enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {
    ACTIVE("ativo"), INACTIVE("inativo");

    private final String value;

    @Override
    public String toString() {
        return value;
    }
}
