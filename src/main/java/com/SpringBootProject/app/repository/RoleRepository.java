package com.SpringBootProject.app.repository;

import com.SpringBootProject.app.entity.RoleEntity;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<RoleEntity,Long> {
}
