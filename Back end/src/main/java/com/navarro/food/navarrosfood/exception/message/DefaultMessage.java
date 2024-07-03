package com.navarro.food.navarrosfood.exception.message;

import org.springframework.http.HttpStatus;

public record DefaultMessage(HttpStatus status, String message) {
}
