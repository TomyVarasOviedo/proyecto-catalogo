package com.catalogo.proyecto.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catalogo.proyecto.Exceptions.DataNotFoundException;
import com.catalogo.proyecto.Models.Seccion;
import com.catalogo.proyecto.Repositories.IOSeccion;

@Service
public class SeccionService {
    @Autowired
    private IOSeccion repoSeccion;

    public Seccion guardarSeccion(Seccion seccion) {
        try {
            return repoSeccion.save(seccion);
        } catch (Exception e) {
            //Modificar posteriormente para las distintas excepciones del sistema
            return null;
        }
    }

    public Seccion getSeccionId(Long seccionId) {
        Optional<Seccion> busqueda = repoSeccion.findById(seccionId);
        return busqueda.orElseThrow(
            () -> new DataNotFoundException("Seccion "+String.valueOf(seccionId)+" no encontrada")
        );
    }

    public Seccion eliminarSeccion(Long seccionId) {
        Seccion buscar = this.getSeccionId(seccionId);
        if (buscar != null) {
            repoSeccion.deleteById(seccionId);
            return buscar;
        }else{
            throw new DataNotFoundException("Seccion "+String.valueOf(seccionId)+" no encontrada");
        }
    }

    public Seccion getSeccionAllId(Long seccionId) {
            return null;
        // return repoSeccion.getSeccionAll(seccionId); Crear funcion en repository para traer los datos asociados de la secccion
    }
}
