package com.SpringBootProject.app.Service.User;

import com.SpringBootProject.app.Entity.UserEntity;
import com.SpringBootProject.app.model.UserDTO;
import com.SpringBootProject.app.model.UserRequestDTO;
import com.SpringBootProject.app.Repository.UserRepository;
import com.SpringBootProject.app.Service.MapperUser.UserMapper;
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
    public UserDTO update(UserDTO theUser) throws RuntimeException {
        LOGGER.trace("Busqueda de usuario por ID");
        //Buscamos el usuario por el id que recibimos
        Optional<UserEntity> optionalUser = userRepository.findById(theUser.getId());
        /*
        Validamos que en la variable optionalUser contenga el usuario si es que existe en nuestra
        db de no existir lanzamos una exepcion
         */
        if(optionalUser.isEmpty()){
            throw new RuntimeException("El usuario no existe");
        }
        // Guardamos el usuario que obtuvimos en la variable user de tipo UserEntity
        UserEntity user = optionalUser.get();
        //Utilizamos el Fill para setear en el userEntity el UserDTO
        UserEntity userFilled =  userMapper.fill(theUser,user);
        //Una vez seteado guardamos el usuario con el metodo save del repository de usuario
        UserEntity userSaved = userRepository.save(userFilled);
        //Y retornamos el usuario guardado pero usando el mapUser para pasar de Entity a DTO
        return userMapper.mapUser(userSaved);
    }

    @Override
    public void delete(Long id) throws RuntimeException {
        LOGGER.trace("Busqueda de usuario por ID para eliminar");
        //Buscamos el usuario por el id que recibimos
        Optional<UserEntity> optionalUser = userRepository.findById(id);
        if(optionalUser.isEmpty()){
            throw new RuntimeException("El usuario no existe");
        }
        // Guardamos el usuario que obtuvimos en la variable user de tipo UserEntity
        UserEntity user = optionalUser.get();
        userRepository.delete(user);
    }
}
