package com.navarro.food.navarrosfood.repositories;

import com.navarro.food.navarrosfood.model.FoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryFood extends JpaRepository<FoodEntity, Long> {
}
