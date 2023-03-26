package com.SpringBootProject.app.service.mapper;

import com.SpringBootProject.app.entity.UserEntity;
import com.SpringBootProject.app.model.UserDTO;
import com.SpringBootProject.app.model.UserRequestDTO;

public interface UserMapper {
    /*
    Metodo de mapero de UserDTO a UserEntity
     */
    UserEntity mapUser(final UserDTO theUser);
    /*
   Metodo de mapero de UserDTO a UserEntity, pero codificando el password
    */
    UserEntity mapUserEncoded (final UserDTO theUser);

    UserEntity mapUserEncoded(final UserRequestDTO theUser);
    /*
   Metodo de mapero de UserEntity a UserDTO
    */
    UserDTO mapUser(final UserEntity theUser);

    UserEntity fill (final UserDTO source, final UserEntity target);

}
