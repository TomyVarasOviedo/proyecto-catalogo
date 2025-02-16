package com.catalogo.proyecto.Exceptions;

public class InvalidDataException extends RuntimeException{
    public InvalidDataException(String mensaje) {
        super(mensaje);
    }
}
