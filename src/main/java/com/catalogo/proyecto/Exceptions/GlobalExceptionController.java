package com.catalogo.proyecto.Exceptions;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionController {
    /**
     * Metodo para lanzar un error al conectar a la BD
     * @param exception
     * @return
     */
    @ExceptionHandler(ConexionBDException.class)
    public ResponseEntity<ErrorResponse> lanzadorConexionBDExceptio(ConexionBDException exception) {
        ErrorResponse error = new ErrorResponse(LocalDateTime.now(), "Error al conectar a la base de datos", exception.getLocalizedMessage(), 500);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ErrorResponse> lanzadorDataNotFoundException(DataNotFoundException exception) {
        ErrorResponse error = new ErrorResponse(
            LocalDateTime.now(), 
            "Data Not Found", exception.getLocalizedMessage(), 404);
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> lanzadorInvalidDataException(ConstraintViolationException exception) {
        List<String> errors = exception.getConstraintViolations()
        .stream()
            .map(v -> v.getPropertyPath() + ": " + v.getMessage())
            .collect(Collectors.toList());
        ErrorResponse error = new ErrorResponse(
            LocalDateTime.now(), 
            exception.getMessage(), 
            errors.toString(),
            500
            );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> lanzadorGeneral(Exception exception) {
        ErrorResponse error = new ErrorResponse(LocalDateTime.now(), "Error en el servidor", exception.getLocalizedMessage(), 500);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
