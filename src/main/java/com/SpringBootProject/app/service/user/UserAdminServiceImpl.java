package com.SpringBootProject.app.service.user;

import com.SpringBootProject.app.entity.UserEntity;
import com.SpringBootProject.app.model.UserDTO;
import com.SpringBootProject.app.model.UserRequestDTO;
import com.SpringBootProject.app.repository.UserRepository;
import com.SpringBootProject.app.service.mapper.UserMapper;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;


@Service
public class UserAdminServiceImpl implements UserAdminService {

    /*
    Variable para LOGS
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(UserAdminServiceImpl.class);

    //Importamos el repositorio de usuario para poder hacer uso del crud interno a la DB
    private UserRepository userRepository;

    //Usamos el mapper de user para poder transformar de UserDTO a UserEntity y viceversa
    private UserMapper userMapper;


    /*Importamos en el constructor lo necesario para hacer una instancia de UserAdminServiceImpl y
    tenes disponible en este caso el repositorio como el mapper
    */
    public UserAdminServiceImpl(UserMapper theUserMapper, UserRepository theUserRepository) {
        userRepository = theUserRepository;
        userMapper = theUserMapper;

    }

    /*
    Metodo create que viene de UserAdminService que extiende del CrudAdminService que implementamos y esto
    nos obliga a sobrescribir los metodos del mismo
     */
    @Override
    public UserDTO create(UserRequestDTO theUser) throws RuntimeException {
        //validacion que el usuario no sea null
        Validate.notNull(theUser, "El usuario no puede ser NULO");
        LOGGER.trace(String.format("Creacion de usuario : %s", theUser.toString()));
        UserEntity toCreate = userMapper.mapUserEncoded(theUser);
        UserEntity created = userRepository.save(toCreate);
        return userMapper.mapUser(created);
    }

    /*
    Busqueda de usuario por ID
     */
    @Override
    public UserDTO get(Long userId) throws RuntimeException {
        LOGGER.trace("Busqueda de usuario por ID");
        //Usamos optional User por que puede o no encontrar el Usuario
        Optional<UserEntity> optionalUser = userRepository.findById(userId);
        //Parseamos el optionalUser a User entiti para devolverlo
        UserEntity user = optionalUser.orElseThrow(NoSuchElementException::new);
        //Mapeamos de UserEntity a UserDTO para devolver la respuesta
        return userMapper.mapUser(user);
    }

    @Override
    public List<UserDTO> getAll() throws RuntimeException {
        LOGGER.trace("Lista de usuarios");
        Iterable<UserEntity> users = userRepository.findAll();
        Iterator<UserEntity> iter = users.iterator();
        List<UserDTO> response = new ArrayList<>();
        while (iter.hasNext()) {
            response.add(userMapper.mapUser(iter.next()));
        }
        return response;
    }

    @Override
    public UserDTO update(UserDTO theUser, Long id) throws RuntimeException {
        LOGGER.trace("Busqueda de usuario por ID");
        Optional<UserEntity> optionalUser = userRepository.findById(id);
        UserEntity toUpdate = userMapper.mapUserEncoded(theUser);
        UserEntity created = userRepository.save(toUpdate);
        return userMapper.mapUser(created);
    }

    @Override
    public void delete(Long id) throws RuntimeException {

    }
}
