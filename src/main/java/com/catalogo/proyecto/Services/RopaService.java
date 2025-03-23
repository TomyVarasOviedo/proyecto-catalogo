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

    public Ropa guardarRopa(Ropa ropa) {
        if (ropa.getNombre() == "" || ropa.getDescripcion() == "") {
            throw new InvalidDataException("Ropa invalida para guardarse");
        }
        return repoRopa.save(ropa);
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
}
