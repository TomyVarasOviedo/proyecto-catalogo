package com.catalogo.proyecto.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Optional<Seccion> getSeccionId(Long seccionId) {
        return repoSeccion.findById(seccionId);
    }

    public Seccion eliminarSeccion(Long seccionId) {
        Optional<Seccion> buscar = this.getSeccionId(seccionId);
        if (!buscar.isEmpty()) {
            repoSeccion.deleteById(seccionId);
            return buscar.get();
        }else{
            return null;
        }
    }

    public Seccion getSeccionAllId(Long seccionId) {
            return null;
        // return repoSeccion.getSeccionAll(seccionId); Crear funcion en repository para traer los datos asociados de la secccion
    }
}
