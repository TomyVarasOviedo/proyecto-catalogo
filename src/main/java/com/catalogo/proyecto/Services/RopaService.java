package com.catalogo.proyecto.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.catalogo.proyecto.Models.Ropa;
import com.catalogo.proyecto.Repositories.IORopa;

public class RopaService {
    @Autowired
    private IORopa repoRopa;

    public Ropa guardRopa(Ropa ropa) {
        return repoRopa.save(ropa);
    }

    public Optional<Ropa> getRopaId(Long idRopa) {
        return repoRopa.findById(idRopa);
    }

    public boolean eliminarRopa(Long idRopa) {
        if (!(this.getRopaId(idRopa).isEmpty())) {
            repoRopa.deleteById(idRopa);
            return true;
        }
        return false;
    }
}
