package com.navarro.food.navarrosfood.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Type {
    SALTED('S'), SWEET('D');

    private final Character value;
}
