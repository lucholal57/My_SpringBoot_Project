package com.SpringBootProject.app.service;

import com.SpringBootProject.app.entity.UserEntity;
import com.SpringBootProject.app.model.UserDTO;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public interface UserMapper {

    UserEntity mapUser(final UserDTO theUser);

    UserEntity mapUserEncoded (final UserDTO theUser);

    UserDTO mapUser(final UserEntity theUser);
}
