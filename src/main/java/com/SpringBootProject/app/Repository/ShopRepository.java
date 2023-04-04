package com.SpringBootProject.app.Repository;

import com.SpringBootProject.app.Entity.ShopEntity;
import com.SpringBootProject.app.Entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ShopRepository extends CrudRepository<ShopEntity,Long> {

    Optional<ShopEntity> findTopByuser(UserEntity username);
}
