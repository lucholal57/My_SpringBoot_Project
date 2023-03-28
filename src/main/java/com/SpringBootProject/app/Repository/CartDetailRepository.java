package com.SpringBootProject.app.Repository;

import com.SpringBootProject.app.Entity.CartDetailEntity;
import org.springframework.data.repository.CrudRepository;

public interface CartDetailRepository extends CrudRepository<CartDetailEntity,Long> {
}
