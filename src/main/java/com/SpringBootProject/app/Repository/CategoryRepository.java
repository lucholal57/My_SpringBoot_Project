package com.SpringBootProject.app.Repository;

import com.SpringBootProject.app.Entity.CategoryEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<CategoryEntity,Long> {
    Optional<CategoryEntity> findByName(String name);
}
