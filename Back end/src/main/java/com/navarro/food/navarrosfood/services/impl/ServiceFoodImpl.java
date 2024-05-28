package com.navarro.food.navarrosfood.services.impl;

import com.navarro.food.navarrosfood.exception.FoodNotFound;
import com.navarro.food.navarrosfood.dtos.food.FoodRequest;
import com.navarro.food.navarrosfood.dtos.food.FoodResponse;
import com.navarro.food.navarrosfood.dtos.mapper.FoodMapper;
import com.navarro.food.navarrosfood.enums.Status;
import com.navarro.food.navarrosfood.repositories.RepositoryFood;
import com.navarro.food.navarrosfood.services.ServiceFood;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceFoodImpl implements ServiceFood {

    private final FoodMapper mapper;
    private final RepositoryFood repositoryFood;

    public ServiceFoodImpl(FoodMapper mapper, RepositoryFood repositoryFood) {
        this.mapper = mapper;
        this.repositoryFood = repositoryFood;
    }

    @Override
    public List<FoodResponse> listAllFoods() {
        return this.repositoryFood.getAllActiveFoods(Status.ACTIVE)
                .stream().map(this.mapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public FoodResponse getFoodById(Long id) {
        return this.mapper.toResponse(
                this.repositoryFood.getFoodById(id, Status.ACTIVE)
                       .orElseThrow(() -> this.initFoodNotFoundById(id)));
    }

    @Override
    public FoodResponse createFood(FoodRequest request) {
        return this.mapper.toResponse(this.repositoryFood.save(this.mapper.toEntity(request)));
    }

    @Override
    @Transactional
    public FoodResponse updateFood(Long id, FoodRequest request) {
        return this.repositoryFood.getFoodById(id, Status.ACTIVE).map(food -> {
                   try {
                       food.setName(request.name());
                       food.setDescription(request.description());
                       food.setImage(request.image());
                       food.setValue(request.value());
                       return this.mapper.toResponse(food);
                   } catch (ConstraintViolationException exception) {
                       throw new ValidationException(exception);
                   }
                }).orElseThrow(() -> this.initFoodNotFoundById(id));
    }

    @Override
    @Transactional
    public void deleteFoodById(Long id) {
        this.repositoryFood.getFoodById(id, Status.ACTIVE)
                .ifPresentOrElse(
                        food -> this.repositoryFood.safeDelete(food.getFoodNumber(), Status.INACTIVE),
                        () -> { throw this.initFoodNotFoundById(id); }
                );
    }

    private FoodNotFound initFoodNotFoundById(Long id) {
        return new FoodNotFound(String.format("Food with id %s not found!", id));
    }
}
