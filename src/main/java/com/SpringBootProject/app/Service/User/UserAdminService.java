package com.SpringBootProject.app.Service.User;

import com.SpringBootProject.app.model.UserDTO;
import com.SpringBootProject.app.model.UserRequestDTO;
import com.SpringBootProject.app.Service.Crud.CrudAdminService;

import java.util.Optional;

/*
Capa de negocio para atender los requerimientos del controlador de usuarios
 */
public interface UserAdminService extends CrudAdminService<UserDTO,UserRequestDTO,Long> {
    Optional<UserDTO> findByUsername(String username);
}
