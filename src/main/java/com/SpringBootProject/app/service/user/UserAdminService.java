package com.SpringBootProject.app.service.user;

import com.SpringBootProject.app.model.UserDTO;
import com.SpringBootProject.app.model.UserRequestDTO;
import com.SpringBootProject.app.service.crud.CrudAdminService;

import java.util.List;
/*
Capa de negocio para atender los requerimientos del controlador de usuarios
 */
public interface UserAdminService extends CrudAdminService<UserDTO,UserRequestDTO,Long> {
}
