package com.SpringBootProject.app.service;

import com.SpringBootProject.app.entity.UserEntity;
import com.SpringBootProject.app.model.UserDTO;
import com.SpringBootProject.app.repository.UserRepository;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.*;
import java.time.ZoneId;



@Service
public class UserServiceImpl implements UserService {

    /*
    Variable para LOGS
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private UserRepository userRepository;
    private UserMapper userMapper;


    public UserServiceImpl(UserMapper theUserMapper,UserRepository theUserRepository){
        userRepository = theUserRepository;
        userMapper = theUserMapper;

    }
    @Override
    public UserDTO createUser(UserDTO theUser) {
        //validacion que el usuario no sea null
        Validate.notNull(theUser,"El usuario no puede ser NULO");
        LOGGER.trace(String.format("Creacion de usuario : %s", theUser.toString()));
        UserEntity toCreate = userMapper.mapUserEncoded(theUser);
        UserEntity created = userRepository.save(toCreate);
        return userMapper.mapUser(created);
    }

    @Override
    public List<UserDTO> getAllUser() {
        LOGGER.trace(String.format("Lista de usuarios"));
        Iterable<UserEntity> users = userRepository.findAll();
        Iterator<UserEntity> iter = users.iterator();
        List<UserDTO> response = new ArrayList<>();
        while (iter.hasNext()) {
            response.add(userMapper.mapUser(iter.next()));
        }
        return response;
    }


}
