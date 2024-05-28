package com.navarro.food.navarrosfood.services;

import com.navarro.food.navarrosfood.exception.FoodNotFound;
import com.navarro.food.navarrosfood.dtos.food.FoodRequest;
import com.navarro.food.navarrosfood.dtos.food.FoodResponse;
import com.navarro.food.navarrosfood.dtos.mapper.FoodMapper;
import com.navarro.food.navarrosfood.enums.Status;
import com.navarro.food.navarrosfood.model.FoodEntity;
import com.navarro.food.navarrosfood.repositories.RepositoryFood;
import com.navarro.food.navarrosfood.services.impl.ServiceFoodImpl;
import com.navarro.food.navarrosfood.utils.Utils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
    private static FoodRequest request;
    private static FoodResponse response;


    @BeforeAll
    static void setUp(){
        food = Utils.initFoodEntity();
        request = Utils.initRequest();
        response = Utils.initResponse();
    }

    @Test
    @DisplayName("Teste de sucesso ao solicitar lista de comidas")
    void ListAllFoods() {
        when(this.repositoryFood.getAllActiveFoods(Status.ACTIVE)).thenReturn(List.of(food));
        when(this.mapper.toResponse(food)).thenReturn(response);

        var result = assertDoesNotThrow(() -> this.serviceFood.listAllFoods());

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(List.of(response), result);
    }

    @Test
    @DisplayName("Teste de sucesso ao tentar capturar uma comida pelo id")
    void getFoodByIdSuccess() {
        when(this.repositoryFood.getFoodById(food.getFoodNumber(), Status.ACTIVE)).thenReturn(Optional.of(food));
        when(this.mapper.toResponse(food)).thenReturn(response);

        var result = assertDoesNotThrow(() -> this.serviceFood.getFoodById(food.getFoodNumber()));

        assertNotNull(result);
        assertEquals(response, result);
    }

    @Test
    @DisplayName("Teste de erro ao tentar capturar uma comida pelo id")
    void getFoodByIdError() {
        when(this.repositoryFood.getFoodById(food.getFoodNumber(), Status.ACTIVE)).thenReturn(Optional.empty());

        var result = assertThrows(FoodNotFound.class, () -> this.serviceFood.getFoodById(food.getFoodNumber()));

        assertEquals(String.format("Food with id %s not found!", food.getFoodNumber()), result.getMessage());
    }

    @Test
    @DisplayName("Teste de sucesso ao cadastrar uma comida nova")
    void createFoodSuccess() {
        when(this.mapper.toEntity(request)).thenReturn(food);
        when(this.repositoryFood.save(food)).thenReturn(food);
        when(this.mapper.toResponse(food)).thenReturn(response);

        var result = assertDoesNotThrow(() -> this.serviceFood.createFood(request));

        assertNotNull(result);
        assertEquals(response, result);
    }

    @Test
    @DisplayName("Teste de sucesso ao atualizar comida existente")
    void updateFoodSuccess() {
        when(this.mapper.toResponse(food)).thenReturn(response);
        when(this.repositoryFood.getFoodById(food.getFoodNumber(), Status.ACTIVE)).thenReturn(Optional.ofNullable(food));

        assertDoesNotThrow(() -> this.serviceFood.updateFood(food.getFoodNumber(), request));

        verify(repositoryFood, times(1)).getFoodById(food.getFoodNumber(), Status.ACTIVE);
    }

    @Test
    @DisplayName("Teste de erro ao atualizar comida existente")
    void updateFoodError() {
        when(this.repositoryFood.getFoodById(food.getFoodNumber(), Status.ACTIVE)).thenReturn(Optional.empty());

        var result = assertThrows(FoodNotFound.class,
                () -> this.serviceFood.updateFood(food.getFoodNumber(), request));

        assertEquals(String.format("Food with id %s not found!", food.getFoodNumber()), result.getMessage());
    }

    @Test
    @DisplayName("Teste de sucesso ao deletar uma comida")
    void deleteFoodSuccess() {
        when(this.repositoryFood.getFoodById(food.getFoodNumber(), Status.ACTIVE)).thenReturn(Optional.ofNullable(food));

        assertDoesNotThrow(() -> this.serviceFood.deleteFoodById(food.getFoodNumber()));

        verify(repositoryFood, times(1)).getFoodById(food.getFoodNumber(), Status.ACTIVE);
        verify(repositoryFood, times(1)).safeDelete(food.getFoodNumber(), Status.INACTIVE);
    }


    @Test
    @DisplayName("Teste de erro ao deletar comida")
    void deleteFoodError() {

        var result = assertThrows(FoodNotFound.class,
                () -> this.serviceFood.deleteFoodById(food.getFoodNumber()));

        assertEquals(String.format("Food with id %s not found!", food.getFoodNumber()), result.getMessage());
    }
}
