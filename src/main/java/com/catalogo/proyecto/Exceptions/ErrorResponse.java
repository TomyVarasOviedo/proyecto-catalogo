package com.catalogo.proyecto.Exceptions;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ErrorResponse {
    private LocalDateTime tiempo;
    private String mensaje;
    private String detalle;
    private int code;
    
    public void setTiempo(LocalDateTime tiempo) {
        this.tiempo = tiempo;
    }
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public LocalDateTime getTiempo() {
        return tiempo;
    }
    public String getMensaje() {
        return mensaje;
    }
    public String getDetalle() {
        return detalle;
    }
    public int getCode() {
        return code;
    }
}
