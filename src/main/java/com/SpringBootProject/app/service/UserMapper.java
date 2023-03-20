package com.SpringBootProject.app.service;

import com.SpringBootProject.app.entity.UserEntity;
import com.SpringBootProject.app.model.UserDTO;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public interface UserMapper {
    /*
    Metodo de mapero de UserDTO a UserEntity
     */
    UserEntity mapUser(final UserDTO theUser);
    /*
   Metodo de mapero de UserDTO a UserEntity, pero codificando el password
    */
    UserEntity mapUserEncoded (final UserDTO theUser);
    /*
   Metodo de mapero de UserEntity a UserDTO
    */
    UserDTO mapUser(final UserEntity theUser);

}
