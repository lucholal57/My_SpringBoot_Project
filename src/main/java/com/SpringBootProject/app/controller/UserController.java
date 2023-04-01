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
public class UserController extends BaseController implements UsersApiDelegate {

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

    public ResponseEntity<ResponseContainerDTO> createUser(UserRequestDTO userRequestDTO) {
        Long start = System.currentTimeMillis();
        LOGGER.debug("CREAR");
        ResponseContainerDTO responseContainer = new ResponseContainerDTO();
        try {
            UserDTO response = userAdminService.create(userRequestDTO);
            responseContainer.data(response);
            responseContainer.setMeta(buildMeta(start));
            return ResponseEntity.status(HttpStatus.CREATED).body(responseContainer);
        } catch (Exception e) {
            LOGGER.error(String.format("Ocurrio un error al crear el usuario: \"%s\" ", userRequestDTO), e);
            return buildErrorResponse(responseContainer, HttpStatus.BAD_REQUEST, e, "A1", start);
        }
    }

    /*
    Busqueda de usuario por ID
     */
    public ResponseEntity<ResponseContainerDTO> getUser(Long userId) {
        Long start = System.currentTimeMillis();
        LOGGER.trace("BUSQUEDA POR ID");
        ResponseContainerDTO responseContainer = new ResponseContainerDTO();
        try {
            UserDTO response = userAdminService.get(userId);
            responseContainer.data(response);
            responseContainer.setMeta(buildMeta(start));
            return ResponseEntity.status(HttpStatus.CREATED).body(responseContainer);
        } catch (Exception e) {
            LOGGER.error("Ocurrio un error al buscar usuario", e);
            return buildErrorResponse(responseContainer, HttpStatus.NO_CONTENT, e, "A2", start);
        }


    }

    public ResponseEntity<ResponseContainerDTO> getAllUser() {
        Long start = System.currentTimeMillis();
        LOGGER.debug("LISTAR USUARIO");
        ResponseContainerDTO responseContainer = new ResponseContainerDTO();
        try {
            List<UserDTO> listUser = userAdminService.getAll();
            UserListDTO response = new UserListDTO();
            response.setItems(listUser);
            responseContainer.data(response);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseContainer);
        } catch (Exception e) {
            LOGGER.error("Ocurrio un error al listar usuarios", e);
            return buildErrorResponse(responseContainer, HttpStatus.NO_CONTENT, e, "A2", start);

        }
    }

    public ResponseEntity<ResponseContainerDTO> updateUser(Long userId, UserDTO userDTO) {
        Long start = System.currentTimeMillis();
        LOGGER.debug("UPDATE");
        ResponseContainerDTO responseContainer = new ResponseContainerDTO();
        //Validamos que el id que estamos buscando sea el mismo al que vamos a actualizar desde swagger
        if(!Objects.equals(userId, userDTO.getId())){
            //Si son distintos lo inforamos en un LOG
            LOGGER.error("El id que busca es distinto al usuario que quiere actualizar");
            return buildErrorResponse(responseContainer,HttpStatus.BAD_REQUEST,null,"A2",start);
        }
        //De ser igual realizamos un tryCatch
        try{
            // Creamos una respuesta de tipo UserDTO y le mandamos a la funcion update el modelo userDTO que estamos
            //modificando
            UserDTO response = userAdminService.update(userDTO);
            responseContainer.data(response);
            responseContainer.setMeta(buildMeta(start));
            return ResponseEntity.status(HttpStatus.OK).body(responseContainer);
        }catch (Exception e){
            LOGGER.error("Ocurrio un error al actualizar usuario");
            return buildErrorResponse(responseContainer,HttpStatus.BAD_REQUEST,e,"A2",start);
        }
    }

    public ResponseEntity<ResponseContainerDTO> deleteUser(Long userId) {
        Long start = System.currentTimeMillis();
        LOGGER.debug("BORRAR");
        ResponseContainerDTO responseContainer = new ResponseContainerDTO();
        try{
            userAdminService.delete(userId);
            EmptyResponseDTO response = new EmptyResponseDTO();
            response.setDate(OffsetDateTime.now());
            responseContainer.data(response);
            responseContainer.setMeta(buildMeta(start));
            return ResponseEntity.status(HttpStatus.OK).body(responseContainer);
        }catch (Exception e){
            LOGGER.error("Ocurrio un error al eliminar usuario");
            return buildErrorResponse(responseContainer,HttpStatus.BAD_REQUEST,e,"A2",start);
        }
    }


}
