package com.SpringBootProject.app.repository;

import com.SpringBootProject.app.entity.CartDetailEntity;
import org.springframework.data.repository.CrudRepository;

public interface CartDetailRepository extends CrudRepository<CartDetailEntity,Long> {
}
