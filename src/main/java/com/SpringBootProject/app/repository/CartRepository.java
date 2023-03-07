package com.SpringBootProject.app.repository;

import com.SpringBootProject.app.entity.CartEntity;
import com.SpringBootProject.app.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CartRepository extends CrudRepository<CartEntity,Long> {

    Optional<CartEntity> findTopByuser(UserEntity username);
}
