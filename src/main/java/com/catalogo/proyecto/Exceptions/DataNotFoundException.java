package com.catalogo.proyecto.Exceptions;

public class DataNotFoundException extends RuntimeException{
    public DataNotFoundException(String mensaje) {
        super(mensaje);
    }
}
