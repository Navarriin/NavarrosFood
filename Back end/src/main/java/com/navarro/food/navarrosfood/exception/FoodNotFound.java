package com.navarro.food.navarrosfood.exception;

import com.navarro.food.navarrosfood.model.FoodEntity;

public class FoodNotFound extends RuntimeException{

    public FoodNotFound(){
        super("Food not Found!");
    }

    public FoodNotFound(String message) {
        super(message);
    }
}
