package com.catalogo.proyecto.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catalogo.proyecto.Exceptions.DataNotFoundException;
import com.catalogo.proyecto.Exceptions.InvalidDataException;
import com.catalogo.proyecto.Models.Catalogo;
import com.catalogo.proyecto.Repositories.IOCatalogo;

@Service
public class CatalogoService {
    @Autowired
    private IOCatalogo repoCatalogo;
    @Autowired
    UsuarioService serviceUsuario;

    public Catalogo guardarCatalogo(Catalogo catalogo) {
        if (this.validarCatalogoEntry(catalogo)) {
            return repoCatalogo.save(catalogo);
        }else{
            throw new InvalidDataException("Error al insertar en la base de datos");
        }
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

    /**
     * Metodo para validar los parametros de una entrada de catalogo
     * @param catalogo Catalogo
     * @return Si la entrada es valida devuelve true
     */
    private boolean validarCatalogoEntry(Catalogo catalogo) {
        if (catalogo.getUsuario() == null|| catalogo.getCantidad() <= 0) {
            throw new InvalidDataException("Los datos ingresados no son invalidos");
        }
        // Comprobar si el usuario existe dentro de la base de datos
        serviceUsuario.getUsuarioId(catalogo.getUsuario().getId());

        return true;
    }
}
