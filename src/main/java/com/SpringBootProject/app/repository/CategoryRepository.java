package com.SpringBootProject.app.repository;

import com.SpringBootProject.app.entity.CategoryEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<CategoryEntity,Long> {
    Optional<CategoryEntity> findTopByName(String name);
}
