package com.navarro.food.navarrosfood.Services;

import com.navarro.food.navarrosfood.model.DTOs.FoodResponse;
import com.navarro.food.navarrosfood.model.DTOs.mapper.FoodMapper;
import com.navarro.food.navarrosfood.model.FoodEntity;
import com.navarro.food.navarrosfood.repositories.RepositoryFood;
import com.navarro.food.navarrosfood.services.impl.ServiceFoodImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;

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

    private FoodEntity food;
    private FoodResponse response;


    @BeforeEach
    void setUp(){
        this.food = new FoodEntity("FoodTest", "Descrição da imagem aqui", "Link da imagem aqui", new BigDecimal(30));
        this.response = new FoodResponse("FoodTest", "Descrição da imagem aqui", "Link da imagem aqui", new BigDecimal(30));
    }

    @Test
    void ListAllFoods() {
        when(this.repositoryFood.findAll()).thenReturn(List.of(this.food));
        when(this.mapper.toResponse(this.food)).thenReturn(this.response);

        var result = assertDoesNotThrow(() -> this.serviceFood.listAllFoods());
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(List.of(this.response), result);
    }
}
