package com.catalogo.proyecto.Exceptions;

public class ConexionBDException extends RuntimeException{
    public ConexionBDException(String mensaje) {
        super(mensaje);
    }
}
