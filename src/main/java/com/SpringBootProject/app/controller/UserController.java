package com.SpringBootProject.app.controller;

import com.SpringBootProject.app.api.UsersApiDelegate;
import com.SpringBootProject.app.model.*;
import com.SpringBootProject.app.Service.User.UserAdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

/*
Esta clase user controler va a ser la encarcada de trabajar con las peticiones. Extiende de una clase BASE
que contiene los responses generados para no tener que estar copiando lineas de codigo iguales.
A su vez implementa UsersApiDelegate seria el path quien contiene la ejecuccion de los verbos del contrato
 */
public class UserController implements UsersApiDelegate {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    /*
    Utilizamos la interfaz UserAdminService quien se va a encargar internamente de hacer uso de
    UserAdminImpl atravez de CrudAdminService
     */
    private UserAdminService userAdminService;

    //Hacemos instancia de UserController pasandole UserAdminService
    public UserController(UserAdminService theUserAdminService) {
        userAdminService = theUserAdminService;

    }


    public ResponseEntity<UserResponseContainerDTO> createUser(UserRequestDTO userRequestDTO) {
        LOGGER.debug("CREAR");
        UserResponseContainerDTO responseContainer = new UserResponseContainerDTO();
        try {
            UserDTO response = userAdminService.create(userRequestDTO);
            responseContainer.user(response);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseContainer);
        } catch (Exception e) {
            LOGGER.error(String.format("Ocurrio un error al crear el usuario: \"%s\" ", userRequestDTO), e);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(responseContainer);
        }
    }

    //Busqueda de usuario por ID
    public ResponseEntity<UserResponseContainerDTO> getUser(Long userId) {
        LOGGER.trace("BUSQUEDA POR ID");
        UserResponseContainerDTO responseContainer = new UserResponseContainerDTO();
        try {
            UserDTO response = userAdminService.get(userId);
            responseContainer.user(response);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseContainer);
        } catch (Exception e) {
            LOGGER.error("Ocurrio un error al buscar usuario", e);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(responseContainer);
        }
    }
    @Override
    public ResponseEntity<EmptyResponseDTO> removeRoleToUser(Long userId, Long roleId) {
        return UsersApiDelegate.super.removeRoleToUser(userId, roleId);
    }

    public ResponseEntity<UserListResponseContainerDTO> getAllUser() {
        LOGGER.debug("LISTAR USUARIO");
        UserListResponseContainerDTO responseContainer = new UserListResponseContainerDTO();
        try {
            List<UserDTO> listUser = userAdminService.getAll();
            responseContainer.users(listUser);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseContainer);
        } catch (Exception e) {
            LOGGER.error("Ocurrio un error al listar usuarios", e);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(responseContainer);

        }
    }

    public ResponseEntity<UserResponseContainerDTO> updateUser(Long userId, UserDTO userDTO) {
        LOGGER.debug("UPDATE");
        UserResponseContainerDTO responseContainer = new UserResponseContainerDTO();
        //Validamos que el id que estamos buscando sea el mismo al que vamos a actualizar desde swagger
        if(!Objects.equals(userId, userDTO.getId())){
            //Si son distintos lo inforamos en un LOG
            LOGGER.error("El id que busca es distinto al usuario que quiere actualizar");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(responseContainer);
        }
        //De ser igual realizamos un tryCatch
        try{
            // Creamos una respuesta de tipo UserDTO y le mandamos a la funcion update el modelo userDTO que estamos
            //modificando
            UserDTO response = userAdminService.update(userDTO);
            responseContainer.user(response);
            return ResponseEntity.status(HttpStatus.OK).body(responseContainer);
        }catch (Exception e){
            LOGGER.error("Ocurrio un error al actualizar usuario");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(responseContainer);
        }
    }

    public ResponseEntity<EmptyResponseDTO> deleteUser(Long userId) {
        LOGGER.debug("BORRAR");
        EmptyResponseDTO responseContainer = new EmptyResponseDTO();
        try{
            userAdminService.delete(userId);
            EmptyResponseDTO response = new EmptyResponseDTO();
            response.setDate(OffsetDateTime.now());
            return ResponseEntity.status(HttpStatus.OK).body(responseContainer);
        }catch (Exception e){
            LOGGER.error("Ocurrio un error al eliminar usuario");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(responseContainer);
        }
    }


    @Override
    public ResponseEntity<EmptyResponseDTO> addRoleToUser(Long userId, Long roleId) {
        return UsersApiDelegate.super.addRoleToUser(userId, roleId);
    }

    @Override
    public ResponseEntity<UserRoleResponseContainerDTO> createRole(UserRoleRequestDTO userRoleRequestDTO) {
        return UsersApiDelegate.super.createRole(userRoleRequestDTO);
    }

    @Override
    public ResponseEntity<UserRoleListResponseContainerDTO> getAllRoles() {
        return UsersApiDelegate.super.getAllRoles();
    }

    @Override
    public ResponseEntity<UserRoleResponseContainerDTO> getUserRoles(Long userId) {
        return UsersApiDelegate.super.getUserRoles(userId);
    }


}
