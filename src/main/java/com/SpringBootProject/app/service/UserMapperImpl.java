package com.SpringBootProject.app.service;

import com.SpringBootProject.app.entity.UserEntity;
import com.SpringBootProject.app.model.UserDTO;
import com.SpringBootProject.app.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class UserMapperImpl implements UserMapper{

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private PasswordEncoder encoder;

    public UserMapperImpl(PasswordEncoder theEncoder){
        encoder = theEncoder;
    }

    public UserEntity mapUser(final UserDTO theUser){
        LOGGER.trace(String.format("Mapeo de UserDTO a UserEntity : %s", theUser.toString()));
        UserEntity response = new UserEntity();
        response.setId(theUser.getId());
        response.setUsername(theUser.getUsername());
        response.setPassword(theUser.getPassword());
        response.setEmail(theUser.getEmail());
        response.setFirstName(theUser.getFirstName());
        response.setLastName(theUser.getLastName());
        if(theUser.getDateCreated() != null) {
            response.setDateCreated(DateUtils.toDate(theUser.getDateCreated()));
        }
        if(theUser.getDateDeleted() != null) {
            response.setDateDeleted(DateUtils.toDate(theUser.getDateDeleted()));
        }
        return response;
    }

    public UserEntity mapUserEncoded(final UserDTO theUser){
        LOGGER.trace(String.format("Mapeo encodeado de UserDTO a UserEntity : %s", theUser.toString()));
        UserEntity response = new UserEntity();
        response.setId(theUser.getId());
        response.setUsername(theUser.getUsername());
        response.setPassword(encoder.encode(theUser.getPassword()));
        response.setEmail(theUser.getEmail());
        response.setFirstName(theUser.getFirstName());
        response.setLastName(theUser.getLastName());
        if(theUser.getDateCreated() != null) {
            response.setDateCreated(DateUtils.toDate(theUser.getDateCreated()));
        }
        if(theUser.getDateDeleted() != null) {
            response.setDateDeleted(DateUtils.toDate(theUser.getDateDeleted()));
        }
        return response;
    }

    public UserDTO mapUser(final UserEntity theUser){
        LOGGER.trace(String.format("Mapeo de UserEntity a UserDTO : %s", theUser.toString()));
        UserDTO response = new UserDTO();
        response.setId(theUser.getId());
        response.setUsername(theUser.getUsername());
        response.setPassword(theUser.getPassword());
        response.setEmail(theUser.getEmail());
        response.setFirstName(theUser.getFirstName());
        response.setLastName(theUser.getLastName());
        if(theUser.getDateCreated() != null) {
            LocalDate createLocalDate = DateUtils.toLocalDate(theUser.getDateCreated());
            response.setDateCreated(createLocalDate);
        }
        if(theUser.getDateCreated() != null) {
            LocalDate deleteLocalDate = DateUtils.toLocalDate(theUser.getDateDeleted());
            response.setDateDeleted(deleteLocalDate);
        }
        return response;
    }
}
