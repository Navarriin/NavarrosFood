package com.navarro.food.navarrosfood.repositories;

import com.navarro.food.navarrosfood.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RepositoryUser extends JpaRepository<UserEntity, UUID> {
}
