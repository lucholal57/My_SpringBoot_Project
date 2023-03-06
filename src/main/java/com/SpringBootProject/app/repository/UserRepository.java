package com.SpringBootProject.app.repository;

import com.SpringBootProject.app.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;


import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity,Long> {

    Optional<UserEntity> findOneByUsername(String username);

}

