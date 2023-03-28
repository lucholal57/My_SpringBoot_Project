package com.SpringBootProject.app.Service.MapperUser;

import com.SpringBootProject.app.Entity.RoleEnum;
import com.SpringBootProject.app.Entity.UserEntity;
import com.SpringBootProject.app.model.UserDTO;
import com.SpringBootProject.app.model.UserRequestDTO;
import com.SpringBootProject.app.Service.User.UserAdminServiceImpl;
import com.SpringBootProject.app.Utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Date;


public class UserMapperImpl implements UserMapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserAdminServiceImpl.class);

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

    @Override
    public UserEntity mapUserEncoded(UserRequestDTO theUser) {
        LOGGER.trace(String.format("Mapping encoded UserRequestDTO with attributes: %s to: UserEntity", theUser.toString()));
        UserEntity response = new UserEntity();
        response.setUsername(theUser.getUsername());
        response.setPassword(encoder.encode(theUser.getPassword()));
        response.setFirstName(theUser.getFirstName());
        response.setLastName(theUser.getLastName());
        response.setEmail(theUser.getEmail());
        response.setRole(RoleEnum.valueOf(theUser.getRole().getValue()));
        response.setDateCreated(new Date());
        return response;
    }

    @Override
    /*
    Este metodo es para castear y poder actualizar en donde Source es lo que recibimos para actualizar
    y target es el usuario de la base de dato al cual se vamos a cambiar los valores de sus atributos
     */
    public UserEntity fill(UserDTO source, UserEntity target) {
        target.setUsername(source.getUsername());
        if(source.getPassword() != null && source.getPassword() != ""){
            target.setPassword(encoder.encode(source.getPassword()));
        }
        target.setFirstName(source.getFirstName());
        target.setLastName(source.getLastName());
        target.setEmail(source.getEmail());
        return target;
    }




}
