package com.SpringBootProject.app.controller;

import com.SpringBootProject.app.api.UsersApiDelegate;
import com.SpringBootProject.app.model.*;
import com.SpringBootProject.app.service.user.UserAdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

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

    public ResponseEntity<ResponseContainerDTO> createUser(UserRequestDTO userDTO) {
        Long start = System.currentTimeMillis();
        LOGGER.debug("CREAR");
        ResponseContainerDTO responseContainer = new ResponseContainerDTO();
        try {
            UserDTO response = userAdminService.create(userDTO);
            responseContainer.data(response);
            responseContainer.setMeta(buildMeta(start));
            return ResponseEntity.status(HttpStatus.CREATED).body(responseContainer);
        } catch (Exception e) {
            LOGGER.error(String.format("An error occurred creating a user: \"%s\" ", userDTO), e);
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
        LOGGER.debug("LISTAR");
        ResponseContainerDTO responseContainer = new ResponseContainerDTO();
        try {
            List<UserDTO> listUser = userAdminService.getAll();
            UserListDTO response = new UserListDTO();
            response.setItems(listUser);
            responseContainer.data(response);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseContainer);
        } catch (Exception e) {
            LOGGER.error("Ocurrio un error al listar usuarios", e);
            LOGGER.error("An error occurred listing uses", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseContainer);

        }
    }

    public ResponseEntity<ResponseContainerDTO> updateUser(Long userId,
                                                           UserDTO userDTO) {
        Long start = System.currentTimeMillis();
        LOGGER.debug("ACTUALIZAR");
        return null;
    }


    public ResponseEntity<EmptyResponseDTO> deleteUser(Long userId,
                                                       UserDTO userDTO) {
        LOGGER.debug("BORRAR");
        ResponseContainerDTO responseContainer = new ResponseContainerDTO();
        return null;
    }
}
