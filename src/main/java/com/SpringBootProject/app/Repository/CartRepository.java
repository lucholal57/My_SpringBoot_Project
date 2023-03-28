package com.SpringBootProject.app.Repository;

import com.SpringBootProject.app.Entity.CartEntity;
import com.SpringBootProject.app.Entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CartRepository extends CrudRepository<CartEntity,Long> {

    Optional<CartEntity> findTopByuser(UserEntity username);
}
