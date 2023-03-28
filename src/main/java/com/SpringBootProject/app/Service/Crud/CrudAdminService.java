package com.SpringBootProject.app.Service.Crud;

import java.util.List;

/*
Interfaz para realizar el CRUD general de las entidades, ya que lo podemos extender de ella para reutilizar
los metodos.
Las variables tienen el significado ya que vamos a trabajar con modelo / modelo-request / ID
A : MODELO
B : MODELOREQUEST
I : ID
 */
public interface CrudAdminService<A,B,I> {

    List<A> getAll() throws RuntimeException;

    A get(final I id) throws RuntimeException;

    A create(final B element) throws RuntimeException;

    A update(final A element) throws RuntimeException;

    void delete(final I id) throws RuntimeException;
}
