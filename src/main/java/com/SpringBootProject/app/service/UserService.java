package com.SpringBootProject.app.service;

import com.SpringBootProject.app.entity.UserEntity;

import java.util.Optional;

public interface UserService {
    public Iterable<UserEntity> findAll();

    public Optional<UserEntity> findById(Long id);

    public UserEntity save(UserEntity user);

    public void deleteById (Long id);
}
