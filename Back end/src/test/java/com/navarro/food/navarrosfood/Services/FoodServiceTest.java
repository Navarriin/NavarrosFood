package com.navarro.food.navarrosfood.Services;

import com.navarro.food.navarrosfood.exception.FoodNotFound;
import com.navarro.food.navarrosfood.model.DTOs.FoodResponse;
import com.navarro.food.navarrosfood.model.DTOs.mapper.FoodMapper;
import com.navarro.food.navarrosfood.model.FoodEntity;
import com.navarro.food.navarrosfood.repositories.RepositoryFood;
import com.navarro.food.navarrosfood.services.impl.ServiceFoodImpl;
import com.navarro.food.navarrosfood.utils.Utils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class FoodServiceTest {

    @Mock
    private RepositoryFood repositoryFood;

    @Mock
    private FoodMapper mapper;

    @InjectMocks
    private ServiceFoodImpl serviceFood;

    private static FoodEntity food;
    private static FoodResponse response;


    @BeforeAll
    static void setUp(){
        food = Utils.initFoodEntity();
        response = Utils.initResponse();
    }

    @Test
    void ListAllFoods() {
        when(this.repositoryFood.findAll()).thenReturn(List.of(food));
        when(this.mapper.toResponse(food)).thenReturn(response);

        var result = assertDoesNotThrow(() -> this.serviceFood.listAllFoods());
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(List.of(response), result);
    }

    @Test
    void getFoodByIdSuccess() {
        when(this.repositoryFood.getFoodById(anyLong())).thenReturn(Optional.of(food));
        when(this.mapper.toResponse(food)).thenReturn(response);

        var result = assertDoesNotThrow(() -> this.serviceFood.getFoodById(anyLong()));
        assertNotNull(result);
        assertEquals(response, result);
    }

    @Test
    void getFoodByIdError() {
        when(this.repositoryFood.getFoodById(anyLong())).thenReturn(Optional.empty());
        when(this.mapper.toResponse(food)).thenReturn(response);

        var result = assertThrows(FoodNotFound.class, () -> this.serviceFood.getFoodById(anyLong()));
        assertEquals("Food with id" + anyLong() + " not found!", result.getMessage());
    }
}
