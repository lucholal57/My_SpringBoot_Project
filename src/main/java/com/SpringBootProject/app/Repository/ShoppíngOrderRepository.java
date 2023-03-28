package com.SpringBootProject.app.Repository;

import com.SpringBootProject.app.Entity.ShoppingOrderEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ShoppíngOrderRepository extends CrudRepository<ShoppingOrderEntity,Long> {

    Optional<ShoppingOrderEntity> findTopByName(String name);
}
