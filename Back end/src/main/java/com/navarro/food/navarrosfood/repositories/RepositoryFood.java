package com.navarro.food.navarrosfood.repositories;

import com.navarro.food.navarrosfood.model.Enums.Status;
import com.navarro.food.navarrosfood.model.FoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RepositoryFood extends JpaRepository<FoodEntity, Long> {

    @Query("SELECT f FROM FoodEntity f WHERE f.status = :status")
    List<FoodEntity> getAllActiveFoods(@Param("status") Status status);

    @Query("SELECT f FROM FoodEntity f WHERE f.foodNumber = :id AND f.status = :status")
    Optional<FoodEntity> getFoodById(@Param("id") Long id, @Param("status") Status status);

    @Modifying
    @Query("UPDATE FoodEntity f SET f.status = :newStatus WHERE f.foodNumber = :id")
    void safeDelete(@Param("id") Long id, @Param("newStatus") Status newStatus);

}
