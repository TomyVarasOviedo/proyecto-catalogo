package com.catalogo.proyecto.Exceptions;

import java.time.LocalDateTime;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionController {
    /**
     * Metodo para lanzar un error al conectar a la BD
     * @param exception
     * @return
     */
    @ExceptionHandler(ConexionBDException.class)
    public ResponseEntity<ErrorResponse> lanzadorConexionBDExceptio(DataAccessException exception) {
        ErrorResponse error = new ErrorResponse(LocalDateTime.now(), "Error al conectar a la base de datos", exception.getLocalizedMessage(), 500);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ErrorResponse> lanzadorDataNotFoundException(DataAccessException exception) {
        ErrorResponse error = new ErrorResponse(
            LocalDateTime.now(), 
            "Data Not Found", exception.getLocalizedMessage(), 404);
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> lanzadorGeneral(Exception exception) {
        ErrorResponse error = new ErrorResponse(LocalDateTime.now(), "Error en el servidor", exception.getLocalizedMessage(), 500);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
