package com.navarro.food.navarrosfood.exception;

public class FoodNotFound extends RuntimeException{

    public FoodNotFound(){
        super("Food not Found!");
    }

    public FoodNotFound(String message) {
        super(message);
    }
}
