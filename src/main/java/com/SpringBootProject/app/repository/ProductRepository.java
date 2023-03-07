package com.SpringBootProject.app.repository;

import com.SpringBootProject.app.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProductRepository extends CrudRepository<ProductEntity,Long> {
    Optional<ProductEntity> findOneByName(String name);
}
