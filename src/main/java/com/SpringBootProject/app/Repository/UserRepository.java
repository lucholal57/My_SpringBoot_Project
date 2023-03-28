package com.SpringBootProject.app.Repository;

import com.SpringBootProject.app.Entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity,Long> {

    Optional<UserEntity> findOneByUsername(String username);

}

