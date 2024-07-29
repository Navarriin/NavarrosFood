package com.navarro.food.navarrosfood.repositories;

import com.navarro.food.navarrosfood.enums.Status;
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

    @Query("SELECT f FROM FoodEntity f WHERE f.id IN :id")
    List<FoodEntity> findAllFoodsById(@Param("id") List<Long> id);

}
