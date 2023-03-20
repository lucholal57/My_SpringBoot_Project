package com.SpringBootProject.app.entity;

import org.apache.commons.lang3.Validate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "users")
public class UserEntity {
    /*
 Las etiquetas en este caso id es contiene una particular que es la que genera el valor en la base de datos.
 Las de column tiene sus caracteristicas por ejemplo da la posibilidad de name? es para darle un nombre y un sentido
 en la misma base de datos.
 Por otra parte usamos nulleable para evitar que la base de datos haga la validacion y directamente salga desde aquí.
  */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private Date dateCreated;
    @Column(nullable = true)
    private Date dateDeleted;
    /*
    Constructor sin parametros
     */

    public UserEntity() {
    }
    /*
    Constructor con parametros
     */

    public UserEntity(Long id, String username,
                      String firstName, String lastName, String email,
                      String password, Date dateCreated,
                      Date dateDeleted) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.dateCreated = dateCreated;
        this.dateDeleted = dateDeleted;
    }
    /*
    Setters and Getters
     */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        Validate.notBlank(password,"El password no puede ser null o vacio");
        this.password = password;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateDeleted() {
        return dateDeleted;
    }

    public void setDateDeleted(Date dateDeleted) {
        this.dateDeleted = dateDeleted;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", dateCreated=" + dateCreated +
                ", dateDeleted=" + dateDeleted +
                '}';
    }
}
