package com.catalogo.proyecto.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catalogo.proyecto.Exceptions.DataNotFoundException;
import com.catalogo.proyecto.Models.Catalogo;
import com.catalogo.proyecto.Repositories.IOCatalogo;

@Service
public class CatalogoService {
    @Autowired
    private IOCatalogo repoCatalogo;

    public Catalogo guardarCatalogo(Catalogo catalogo) {
        return repoCatalogo.save(catalogo);
    }

    public Catalogo getCatalogoId(Long idCatalogo) {
        Optional<Catalogo> busqueda = repoCatalogo.findById(idCatalogo);
        return busqueda.orElseThrow(
            () -> new DataNotFoundException("Catalogo "+String.valueOf(idCatalogo)+" no encontrado")
        );
    }

    public Catalogo eliminarCatalogo(Long idCatalogo) {
        Catalogo busqueda = this.getCatalogoId(idCatalogo);
        if (busqueda != null) {
            repoCatalogo.deleteById(idCatalogo);
            return busqueda;
        }else{
            throw new DataNotFoundException("Catalogo "+String.valueOf(idCatalogo)+" no encontrado");
        }
    }
}
