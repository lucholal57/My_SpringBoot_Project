package com.SpringBootProject.app.repository;

import com.SpringBootProject.app.entity.ShoppingOrderEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface Shopp√≠ngOrderRepository extends CrudRepository<ShoppingOrderEntity,Long> {

    Optional<ShoppingOrderEntity> findTopByName(String name);
}
