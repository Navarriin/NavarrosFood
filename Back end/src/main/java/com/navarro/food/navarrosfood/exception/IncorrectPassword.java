package com.navarro.food.navarrosfood.exception;

public class IncorrectPassword extends RuntimeException {
    public IncorrectPassword(){
        super();
    }
    public IncorrectPassword(String message){
        super(message);
    }
}
