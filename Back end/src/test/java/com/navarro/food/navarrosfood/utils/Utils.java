package com.navarro.food.navarrosfood.utils;

import com.navarro.food.navarrosfood.dtos.food.FoodRequest;
import com.navarro.food.navarrosfood.dtos.food.FoodResponse;
import com.navarro.food.navarrosfood.dtos.user.UserRequestRegister;
import com.navarro.food.navarrosfood.dtos.user.UserRequestUpdate;
import com.navarro.food.navarrosfood.enums.UserRole;
import com.navarro.food.navarrosfood.model.FoodEntity;
import com.navarro.food.navarrosfood.model.UserEntity;

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

    public static UserEntity initUserEntity() {
        return new UserEntity(
                "Gabriel",
                "Navarro",
                "$2a$10$b6Xr6lEHfmFY7s7UlFZ0hOxo7ZiVcM3l74CHuGbPue9sqz0.A0fGG",
                UserRole.ADMIN
        );
    }

    public static UserRequestUpdate initUserRequestUpdate() {
        return new UserRequestUpdate("Gabriel", "Navarro", "1234");
    }
}
