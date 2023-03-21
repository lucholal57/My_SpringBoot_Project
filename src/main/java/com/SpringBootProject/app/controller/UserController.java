package com.SpringBootProject.app.controller;

import com.SpringBootProject.app.api.UsersApiDelegate;
import com.SpringBootProject.app.model.*;
import com.SpringBootProject.app.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class UserController extends BaseController implements UsersApiDelegate {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private UserService userService;
    public UserController(UserService theUserService){
        userService = theUserService;

    }

    public ResponseEntity<ResponseContainerDTO> createUser(UserRequestDTO userDTO) {
        Long start = System.currentTimeMillis();
        LOGGER.debug("CREAR");
        ResponseContainerDTO responseContainer = new ResponseContainerDTO();
        try {
            UserDTO response = userService.createUser(userDTO);
            responseContainer.data(response);
            responseContainer.setMeta(buildMeta(start));
            return ResponseEntity.status(HttpStatus.CREATED).body(responseContainer);
        } catch (Exception e) {
            LOGGER.error(String.format("An error occurred creating a user: \"%s\" ", userDTO), e);
            return buildErrorResponse(responseContainer, HttpStatus.BAD_REQUEST, e, "A1", start);
        }
    }

    public ResponseEntity<ResponseContainerDTO> getAllUser() {
        LOGGER.debug("LISTAR");
        ResponseContainerDTO responseContainer = new ResponseContainerDTO();
        try {
            List<UserDTO> listUser = userService.getAllUser();
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
}
