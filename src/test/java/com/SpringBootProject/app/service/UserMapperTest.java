package com.SpringBootProject.app.service;

import com.SpringBootProject.app.entity.UserEntity;
import com.SpringBootProject.app.model.UserDTO;
import com.SpringBootProject.app.utils.DateUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/*
Clase de testeo del UserMapper Service
 */
// @RunWith(JUnit4.class) No es necesaria ya que usamos Junit5
public class UserMapperTest {
    //Variable userMapper ya que es el que vamos a testear
    private UserMapper userMapper;
    //Creamos esta variable ya que al importar el UserMapperImpl necesita un Encoder
    private PasswordEncoder encoder;
    /*
    BeforeEach: antes de cada testeo se ejecuta el que contenga esta etiqueta.
    BeforeALl: Primero ejecuta este cuando de inicialice la clase y despues los demas.
     */

    @BeforeEach
    public void setupClass() {
        encoder = mock(BCryptPasswordEncoder.class);
        userMapper = new UserMapperImpl(encoder);

    }

    /*
    Test Para cuando pase los valores verificar que la conversion de UserDTO a UserEntity
     */
    @Test
    public void testMapUserDTO_allFieldOK() {
        UserDTO dto = new UserDTO();
        dto.setUsername("userTest");
        dto.setFirstName("fname");
        dto.setLastName("lname");
        dto.setPassword("123");
        dto.setEmail("prueba@prueba.com");
        dto.setDateCreated(LocalDate.now());

        UserEntity response = userMapper.mapUser(dto);

        /*assert = Comprobar.
        Esta linea comprueba que el response no venga null
         */
        assertNotNull(response);
        /*
        Validamos que el maper se haya relalizado bien y no hayan quedado datos cruzados a la hora de maper desde DTO
        a Entity
         */
        assertEquals(response.getUsername(), dto.getUsername());
        assertEquals(response.getFirstName(), dto.getFirstName());
        assertEquals(response.getLastName(), dto.getLastName());
        assertEquals(response.getPassword(), dto.getPassword());
        assertEquals(response.getEmail(), dto.getEmail());
        assertEquals(response.getId(), dto.getId());
        assertEquals(DateUtils.toLocalDate(response.getDateCreated()), dto.getDateCreated());
        Assertions.assertNull(response.getDateDeleted());

    }
    /*
   Test Para cuando pase los valores verificar que la conversion de UserDTO a UserEntity con password codificado
    */
    @Test
    public void testMapUserDTOEncoded_allFieldOK() {
        String pwd = "123";
        String encPwd = "LLLLLL";
        UserDTO dto = new UserDTO();
        dto.setUsername("userTest");
        dto.setFirstName("fname");
        dto.setLastName("lname");
        dto.setPassword(pwd);
        dto.setEmail("prueba@prueba.com");
        dto.setDateCreated(LocalDate.now());
        /*
        When libreria de Mockito para encodear una clave y devolver un string ejemplo clave codificada
         */
        when(encoder.encode(eq(pwd))).thenReturn(encPwd);
        UserEntity response = userMapper.mapUserEncoded(dto);

        /*assert = Comprobar.
        Esta linea comprueba que el response no venga null
         */
        assertNotNull(response);
        /*
        Validamos que el maper se haya relalizado bien y no hayan quedado datos cruzados a la hora de maper desde DTO
        a Entity
         */
        assertEquals(response.getUsername(), dto.getUsername());
        assertEquals(response.getFirstName(), dto.getFirstName());
        assertEquals(response.getLastName(), dto.getLastName());
        /*Comparamos la password contra la clave encriptada que creamos encPwd, ya que si usamos el test sin enviar una
        clave no setena nada genera una Exception
        */
        assertEquals(response.getPassword(), encPwd);
        assertEquals(response.getEmail(), dto.getEmail());
        assertEquals(response.getId(), dto.getId());
        assertEquals(DateUtils.toLocalDate(response.getDateCreated()), dto.getDateCreated());
        Assertions.assertNull(response.getDateDeleted());

    }

    @Test
    public void testMapUserEncodedUserDTO_missingPasswordERROR() {
        UserDTO dto = new UserDTO();
        dto.setUsername("userTest");
        dto.setFirstName("fName");
        dto.setLastName("lName");
        dto.setEmail("prueba@prueba.com");
        dto.setDateCreated(LocalDate.now());

        when(encoder.encode(eq(null))).thenReturn(null);

        Exception retrievedEx = null;
        try {
            userMapper.mapUserEncoded(dto);
        } catch (Exception e) {
            retrievedEx = e;
        }
        assertNotNull(retrievedEx);
        assertEquals(retrievedEx.getClass(), NullPointerException.class);
        assertEquals(retrievedEx.getMessage(), "El password no puede ser null o vacio");
    }

}
