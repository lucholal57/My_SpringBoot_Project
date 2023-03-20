package com.SpringBootProject.app.service;

import com.SpringBootProject.app.entity.UserEntity;
import com.SpringBootProject.app.model.UserDTO;
import com.SpringBootProject.app.repository.UserRepository;

import java.util.List;
/*
Capa de negocio para atender los requerimientos del controlador de usuarios
 */
public interface UserService {
    UserDTO createUser(final UserDTO theUser);
    List<UserDTO> getAllUser();
}
