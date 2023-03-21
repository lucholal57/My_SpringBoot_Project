package com.SpringBootProject.app.controller;

import com.SpringBootProject.app.model.ErrorItemDTO;
import com.SpringBootProject.app.model.MetaInformationResponseDTO;
import com.SpringBootProject.app.model.ResponseContainerDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
/*
Esta clase se genera con el proposito de poder standarizar las respuestas, construyento tanto el ErrorResponse
como la meta. para que sea utilizada como clase base a la hora de necesitar estas respuestas desde los Controllers
 */
public class BaseController {
    /*
    Logger apuntanto a la misma CLASE.class
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    /*
    Construccion de Respuesta de Error
    1-Creacion de lista de ErrorItemDTO que esta creada en el schema del contrato
    2-Creacion de ErrorItemDTO modelo del schema del contrato
    3-Seteams en el elemento errorItem el code, el detail el cual contiene una excepcion.getMessage
    4-Agregamos a la lista el error que creamos con todas sus caracteristicas
    5-En la variable responseContainer de tipo ResponseContainerDTO del modelo de schema del contrato en el atributo
    errors le pasamos la lista de errores que creamos con anterioridad, en el Meta seteamos la construccion del meta
    que codificamos en otra funcion mas abajo, pasandole un startTime.
    6-Retornamos un ResponseEntity y el atributo de status le pasamos el status para poder contestar el tipo de
    HttpStatus y en body el responseContainer
     */
    protected ResponseEntity<ResponseContainerDTO> buildErrorResponse(
            ResponseContainerDTO responseContainer,  HttpStatus status,
            Exception e, String code, Long startTime) {
        List<ErrorItemDTO> errorList = new ArrayList<>();
        ErrorItemDTO errorItem = new ErrorItemDTO();
        errorItem.setCode(code);
        errorItem.setDetail(e.getMessage());
        errorList.add(errorItem);
        responseContainer.errors(errorList);
        responseContainer.setMeta(buildMeta(startTime));
        return ResponseEntity.status(status).body(responseContainer);
    }

    /*
    Construccion de Respuesta de Meta
    1-Creamos un atributo meta del tipo MetaInformationResponseDTO del modelos de schema del contrato
    2. endTIme para pasarte un currentTimeMillis
    3 elapsedTime es igual a la diferencia de endTime - startTime. ¿Que valor tiene startTime? ya que la recibimos por
    parametros pero no tiene seteado ningun valor de Long
    4-A la variable meta le seteamos el Time elapsedTime
    5-Retornamos la meta.
     */
    protected MetaInformationResponseDTO buildMeta(final Long startTime) {
        MetaInformationResponseDTO meta = new MetaInformationResponseDTO();
        Long endTime = System.currentTimeMillis();
        Long elapsedTime = endTime - startTime;
        meta.setTime(elapsedTime);
        return meta;
    }
}
