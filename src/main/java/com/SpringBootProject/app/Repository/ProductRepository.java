package com.SpringBootProject.app.Repository;

import com.SpringBootProject.app.Entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProductRepository extends CrudRepository<ProductEntity,Long> {
    Optional<ProductEntity> findOneByName(String name);
}
