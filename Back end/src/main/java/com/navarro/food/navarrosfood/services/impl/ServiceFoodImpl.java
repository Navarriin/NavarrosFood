package com.navarro.food.navarrosfood.services.impl;

import com.navarro.food.navarrosfood.exception.FoodNotFound;
import com.navarro.food.navarrosfood.exception.ViolationException;
import com.navarro.food.navarrosfood.model.DTOs.FoodRequest;
import com.navarro.food.navarrosfood.model.DTOs.FoodResponse;
import com.navarro.food.navarrosfood.model.DTOs.mapper.FoodMapper;
import com.navarro.food.navarrosfood.model.FoodEntity;
import com.navarro.food.navarrosfood.repositories.RepositoryFood;
import com.navarro.food.navarrosfood.services.ServiceFood;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
        return repositoryFood.findAll()
                .stream().map(mapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public FoodResponse getFoodById(Long id) {
        return mapper.toResponse(
                repositoryFood.getFoodById(id)
                        .orElseThrow(() -> new FoodNotFound("Food with id " + id + " not found!")));
    }

    @Override
    public FoodResponse createFood(FoodRequest request) {
        return mapper.toResponse(repositoryFood.save(mapper.toEntity(request)));
    }

    @Override
    public FoodResponse updateFood(Long id, FoodRequest request) {
        return repositoryFood.getFoodById(id).map(food -> {
                    food.setName(request.name());
                    food.setDescription(request.description());
                    food.setImage(request.image());
                    food.setValue(request.value());
                    return mapper.toResponse(food);
                }).orElseThrow(() -> new ViolationException("Violarion error!"));
    }

    @Override
    public void deleteFoodById(Long id) {
        repositoryFood.delete(repositoryFood.findById(id)
                .orElseThrow(() -> new FoodNotFound("Food with id " + id + " not found!")));
    }
}
