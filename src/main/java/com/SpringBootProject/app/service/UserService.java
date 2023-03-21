package com.SpringBootProject.app.service;

import com.SpringBootProject.app.model.UserDTO;
import com.SpringBootProject.app.model.UserRequestDTO;

import java.util.List;
/*
Capa de negocio para atender los requerimientos del controlador de usuarios
 */
public interface UserService {
    UserDTO createUser(final UserRequestDTO theUser);
    List<UserDTO> getAllUser();
}
