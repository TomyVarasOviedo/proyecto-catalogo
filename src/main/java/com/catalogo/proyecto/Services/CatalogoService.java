package com.catalogo.proyecto.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catalogo.proyecto.Exceptions.DataNotFoundException;
import com.catalogo.proyecto.Exceptions.InvalidDataException;
import com.catalogo.proyecto.Models.Catalogo;
import com.catalogo.proyecto.Models.Seccion;
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

        if (!catalogo.getSecciones().isEmpty()) {
            // Si el catalogo ya tiene secciones
            for (Seccion seccion : catalogo.getSecciones()) {
                this.getCatalogoId(seccion.getId());
            }
        }
        // Comprobar si el usuario existe dentro de la base de datos
        serviceUsuario.getUsuarioId(catalogo.getUsuario().getId());

        return true;
    }

    /**
     * Metodo para actualizar el base de datos un Catalogo
     * @param newCatalogo Catalogo ~ Entrada sobre lo que va a cambiar
     * @return Catalogo
     */
    public Catalogo updateCatalogo(Catalogo newCatalogo) {
        Catalogo oldCatalogo = this.getCatalogoId(newCatalogo.getId());
        boolean isNullEntity = false;
        if (oldCatalogo.getUsuario() != newCatalogo.getUsuario() && newCatalogo.getUsuario().getId() != null ) {
            // Si hay un cambio en el usuario lo agrega
            oldCatalogo.setUsuario(newCatalogo.getUsuario());
            isNullEntity = true;
        }

        if (oldCatalogo.getCantidad() != newCatalogo.getCantidad() && newCatalogo.getCantidad() >= 0) {
            // Si hay cambio en la cantidad lo agrega
            oldCatalogo.setCantidad(newCatalogo.getCantidad());
            isNullEntity = true;
        }

        if (newCatalogo.getSecciones() != null) {
            // Si la lista de seccion cambio la actuliza
            for (Seccion seccion : newCatalogo.getSecciones()) {
                if (!oldCatalogo.getSecciones().contains(seccion)) {
                    oldCatalogo.getSecciones().add(seccion);
                }
            }
            isNullEntity = true;
        }

        if (isNullEntity) {
            return repoCatalogo.save(oldCatalogo);
        }else{
            throw new InvalidDataException("Los datos ingresados para modificar fueron todos nulos");
        }
    }
}
