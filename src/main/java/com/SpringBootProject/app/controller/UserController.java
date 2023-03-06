package com.SpringBootProject.app.controller;

import com.SpringBootProject.app.entity.UserEntity;
import com.SpringBootProject.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> create (@RequestBody UserEntity user){
        return ResponseEntity.status(HttpStatus.OK).body(userService.save(user));
    }
}
