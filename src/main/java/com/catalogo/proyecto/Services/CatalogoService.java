package com.catalogo.proyecto.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catalogo.proyecto.Models.Catalogo;
import com.catalogo.proyecto.Repositories.IOCatalogo;

@Service
public class CatalogoService {
    @Autowired
    private IOCatalogo repoCatalogo;

    public Catalogo guardarCatalogo(Catalogo catalogo) {
        return repoCatalogo.save(catalogo);
    }

    public Optional<Catalogo> getCatalogoId(Long idCatalogo) {
        return repoCatalogo.findById(idCatalogo);
    }

    public boolean eliminarCatalogo(Long idCatalogo) {
        if (!(this.getCatalogoId(idCatalogo).isEmpty())) {
            repoCatalogo.deleteById(idCatalogo);
            return true;
        }
        return false;
    }
}
