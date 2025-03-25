package com.catalogo.proyecto.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catalogo.proyecto.Exceptions.DataNotFoundException;
import com.catalogo.proyecto.Exceptions.InvalidDataException;
import com.catalogo.proyecto.Models.Talle;
import com.catalogo.proyecto.Repositories.IOTalle;

@Service
public class TalleService {
    @Autowired
    IOTalle repoTalle;

    public Talle guardarTalle(Talle talle) {
        if (this.validarTalleEntry(talle)) {
            return repoTalle.save(talle);
        }else{
            throw new InvalidDataException("Error al insertar en la base de datos");
        }
    }

    public Talle getTalleId(int idTalle) {
        Optional<Talle> busqueda = repoTalle.findById(idTalle);
        return busqueda.orElseThrow(
            () -> new DataNotFoundException("Talle "+String.valueOf(idTalle)+" no encontrado")
        );
    }

    public Talle eliminarTalleId(int idTalle) {
        Talle busqueda = this.getTalleId(idTalle);
        if (busqueda != null) {
            repoTalle.deleteById(idTalle);
            return busqueda;
        }else{
            throw new DataNotFoundException("Talle "+String.valueOf(idTalle)+" no encontrado");
        }
    }

    /**
     * Metodo para validar los parametros de entrada
     * @param talle Talle
     * @return Si no hay problema con los parametros devuelve true
     */
    private boolean validarTalleEntry(Talle talle) {
        if (talle.getNombre() == "") {
            throw new InvalidDataException("Los datos del talle no deben ser nulos");
        }

        return true;
    }
}
