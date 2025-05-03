package com.catalogo.proyecto.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catalogo.proyecto.Exceptions.DataNotFoundException;
import com.catalogo.proyecto.Exceptions.InvalidDataException;
import com.catalogo.proyecto.Models.Ropa;
import com.catalogo.proyecto.Repositories.IORopa;

@Service
public class RopaService {
    @Autowired
    private IORopa repoRopa;

    @Autowired
    private TalleService servicioTalle;

    public Ropa guardarRopa(Ropa ropa) {
        if (this.validarRopaEntry(ropa)) {
            return repoRopa.save(ropa);
        }else{
            throw new InvalidDataException("Error al ingresar en la base de datos");
        }
    }

    public Ropa getRopaId(Long idRopa) {
        Optional<Ropa> buscar = repoRopa.findById(idRopa);

        return buscar.orElseThrow(
            () -> new DataNotFoundException("Ropa: "+String.valueOf(idRopa)+" no encontrado")
        );
    }

    public Ropa eliminarRopa(Long idRopa) {
        if (this.getRopaId(idRopa) != null) {
            repoRopa.deleteById(idRopa);
            return this.getRopaId(idRopa);
        }
        return null;
    }

    /**
     * Metodo para validar los parametros de entrada de una Ropa
     * @param ropa Ropa
     * @return Si los parametros son validos devuelve true
     */
    private boolean validarRopaEntry(Ropa ropa) {
        if (ropa.getNombre() == null || ropa.getNombre() == ""
            || ropa.getDescripcion() == null || ropa.getDescripcion() == ""|| ropa.getTalle() == null) {
                throw new InvalidDataException("Los datos ingresados no son validos");
            
        }
        servicioTalle.getTalleId(ropa.getTalle().getId());

        return true;
    }

    /**
     * Metodo para actualizar los parametros de un articulo
     * @param newRopa Nueva Ropa para actualizar
     * @return Ropa actualizada
     */
    public Ropa updateRopa(Ropa newRopa) {
        Ropa oldRopa = this.getRopaId(newRopa.getId());
        boolean isNullEntity = false;
        if (oldRopa.getNombre() != newRopa.getNombre() && newRopa.getNombre() != null) {
            oldRopa.setNombre(newRopa.getNombre());
            isNullEntity = true;
        }

        if (oldRopa.getDescripcion() != newRopa.getDescripcion() && newRopa.getDescripcion() != null) {
            oldRopa.setDescripcion(newRopa.getDescripcion());
            isNullEntity = true;
        }

        if (newRopa.getSeccion() != oldRopa.getSeccion() && newRopa.getSeccion() != null) {
            oldRopa.setSeccion(newRopa.getSeccion());
            isNullEntity = true;
        }

        if (oldRopa.getTalle() != newRopa.getTalle() && newRopa.getTalle() != null) {
            oldRopa.setTalle(newRopa.getTalle());
            isNullEntity = true;
        }

        if (isNullEntity) {
            return repoRopa.save(oldRopa);
        }else{
            throw new InvalidDataException("Los datos ingresados no pueden ser nulos");
        }
    }
}
