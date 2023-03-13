package com.SpringBootProject.app.controller;

import com.SpringBootProject.app.entity.UserEntity;
import com.SpringBootProject.app.service.UserService;
import org.openapitools.api.UserApiDelegate;
import org.openapitools.model.UserRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/*
Esta clase extiende de userapidelegate, que representa a nuestro contrato en Yaml
 */

public class UserController implements UserApiDelegate {
    public UserController(){
    }
    @Override
    public ResponseEntity<UserRequestDTO> createUser(UserRequestDTO userDTO) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
