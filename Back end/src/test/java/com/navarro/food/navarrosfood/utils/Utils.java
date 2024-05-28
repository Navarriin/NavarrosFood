package com.navarro.food.navarrosfood.utils;

import com.navarro.food.navarrosfood.dtos.food.FoodRequest;
import com.navarro.food.navarrosfood.dtos.food.FoodResponse;
import com.navarro.food.navarrosfood.model.FoodEntity;

import java.math.BigDecimal;

public class Utils {

    public static FoodResponse initResponse() {
        return new FoodResponse(1L, "FoodTest", "Descrição da imagem aqui", "Link da imagem aqui", new BigDecimal(30));
    }

    public static FoodEntity initFoodEntity() {
        return new FoodEntity("FoodTest", "Descrição da imagem aqui", "Link da imagem aqui", new BigDecimal(30));
    }

    public static FoodRequest initRequest() {
        return new FoodRequest("FoodTest", "Descrição da imagem aqui", "Link da imagem aqui", new BigDecimal(30));
    }
}
