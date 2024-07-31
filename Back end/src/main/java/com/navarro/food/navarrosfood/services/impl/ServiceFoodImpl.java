package com.navarro.food.navarrosfood.services.impl;

import com.navarro.food.navarrosfood.dtos.food.FoodRequest;
import com.navarro.food.navarrosfood.dtos.food.FoodResponse;
import com.navarro.food.navarrosfood.dtos.mapper.FoodMapper;
import com.navarro.food.navarrosfood.exception.NotFound;
import com.navarro.food.navarrosfood.repositories.RepositoryFood;
import com.navarro.food.navarrosfood.services.ServiceFood;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceFoodImpl implements ServiceFood {

    private final RepositoryFood repository;
    private final FoodMapper mapper;

    public ServiceFoodImpl(RepositoryFood repository,
                           FoodMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<FoodResponse> listAllFoods() {
        return this.repository.findAll()
                .stream().map(this.mapper::toResponse).toList();
    }

    @Override
    public FoodResponse getFoodById(Long id) {
        return this.repository.findById(id)
                .map(this.mapper::toResponse)
                .orElseThrow(
                        () -> this.FoodNotFound(id));
    }

    @Override
    public FoodResponse createFood(FoodRequest request) {
        return this.mapper.toResponse(this.repository.save(this.mapper.toEntity(request)));
    }

    @Override
    @Transactional
    public FoodResponse updateFood(Long id, FoodRequest request) {
        return this.repository.findById(id)
                .map(data -> {
                    data.setName(request.name());
                    data.setImage(request.image());
                    data.setValue(request.value());
                    data.setType(request.type());
                    return this.mapper.toResponse(data);
                })
                .orElseThrow(() -> this.FoodNotFound(id));
    }

    @Override
    @Transactional
    public void deleteFoodById(Long id) {
        this.repository.findById(id)
                .ifPresentOrElse(this.repository::delete,
                        () -> { throw this.FoodNotFound(id); });
    }

    private NotFound FoodNotFound(Long id) {
        return new NotFound(String.format("User with id %d not found!", id));
    }
}
