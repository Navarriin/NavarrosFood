package com.navarro.food.navarrosfood.repositories;

import com.navarro.food.navarrosfood.model.FoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositoryFood extends JpaRepository<FoodEntity, Long> {

    Optional<FoodEntity> getFoodById(Long id);
}
