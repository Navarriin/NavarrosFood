package com.navarro.food.navarrosfood.exception;

public class UserNotFound extends RuntimeException{

    public UserNotFound() {
        super();
    }

    public UserNotFound(String message) {
        super(message);
    }
}

