package com.navarro.food.navarrosfood.repositories;

import com.navarro.food.navarrosfood.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RepositoryUser extends JpaRepository<UserEntity, UUID> {

    @Query("SELECT u FROM UserEntity u WHERE u.username = :username AND u.status = 'ativo'")
    Optional<UserEntity> findByUsername(@Param("username") String username);
}
