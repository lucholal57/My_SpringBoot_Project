package com.SpringBootProject.app.controller;

import com.SpringBootProject.app.api.UserApiDelegate;
import com.SpringBootProject.app.model.*;
import com.SpringBootProject.app.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class UserController implements UserApiDelegate {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private UserService userService;
    public UserController(UserService theUserService){
        userService = theUserService;

    }

    public ResponseEntity<ResponseContainerUserDTO> createUser(UserDTO userDTO) {
        LOGGER.debug("CREAR");
        ResponseContainerUserDTO responseContainer = new ResponseContainerUserDTO();
        try {
            UserDTO response = userService.createUser(userDTO);
            responseContainer.data(response);
            return new ResponseEntity<ResponseContainerUserDTO>(responseContainer,HttpStatus.CREATED);
        }catch (Exception e) {
            LOGGER.error("Ocurrio un error al crear usuario", e);
            List<ErrorItemDTO> errorList = new ArrayList<>();
            ErrorItemDTO errorItem = new ErrorItemDTO();
            errorItem.setCode("A1");
            errorItem.setDetail(e.getMessage());
            errorList.add(errorItem);
            responseContainer.errors(errorList);
           ResponseEntity error = new ResponseEntity(responseContainer,HttpStatus.BAD_REQUEST);
            return error;
        }
    }

    public ResponseEntity<ResponseContainerUserListDTO> getAllUser() {
        LOGGER.debug("LISTAR");
        ResponseContainerUserListDTO responseContainer = new ResponseContainerUserListDTO();
        try {
            List<UserDTO> listUser = userService.getAllUser();
            UserListDTO response = new UserListDTO();
            response.setItems(listUser);
            responseContainer.data(response);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseContainer);
        } catch (Exception e) {
            LOGGER.error("Ocurrio un error al listar usuarios", e);
            LOGGER.error(String.format("An error occurred listing uses"), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseContainer);

        }
    }
}
