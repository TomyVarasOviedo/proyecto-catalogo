package com.catalogo.proyecto.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catalogo.proyecto.Exceptions.DataNotFoundException;
import com.catalogo.proyecto.Exceptions.InvalidDataException;
import com.catalogo.proyecto.Models.Categoria;
import com.catalogo.proyecto.Models.Seccion;
import com.catalogo.proyecto.Repositories.IOSeccion;

@Service
public class SeccionService {
    @Autowired
    private IOSeccion repoSeccion;
    @Autowired
    private CatalogoService servicioCatalogo;
    @Autowired
    private CategoriaService servicioCategoria;

    public Seccion guardarSeccion(Seccion seccion) {
        if (this.validarSeccionEntry(seccion)) {
            return repoSeccion.save(seccion);
        }else{
            throw new InvalidDataException("Error al ingresar en la base de datos");
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

    /**
     * Metodo para validar los parametros de entrada de Seccion
     * @param seccion Seccion
     * @return Si los parametros son validos devuelve true
     */
    private boolean validarSeccionEntry(Seccion seccion) {
        if (seccion.getNombre() == "" ||seccion.getNombre() == null 
            || seccion.getDescripcion() == "" || seccion.getDescripcion() == null
            || seccion.getArticulos() == null || seccion.getCatalogo() == null
            || seccion.getCategoria() == null) {
            throw new InvalidDataException("Los datos de la seccion no deben ser nulos");
        }
        servicioCatalogo.getCatalogoId(seccion.getCatalogo().getId());
        for (Categoria categoria : seccion.getCategoria()) {
            servicioCategoria.getCategoriaId(categoria.getId());
        }

        return true;
    }

    /**
     * Metodo para actualizar una Seccion en la base de datos
     * @param newSeccion Seccion nueva
     * @return Seccion actualizada
     */
    public Seccion updatSeccion(Seccion newSeccion) {
        Seccion oldSeccion = this.getSeccionId(newSeccion.getId());
        boolean isNullEntity = false;
        if (oldSeccion.getNombre() != newSeccion.getNombre() && newSeccion.getNombre() != null) {
            oldSeccion.setNombre(newSeccion.getNombre());
            isNullEntity = true;
        }

        if (oldSeccion.getDescripcion() != newSeccion.getDescripcion() && newSeccion.getDescripcion() != null) {
            oldSeccion.setDescripcion(newSeccion.getDescripcion());
            isNullEntity = true;
        }

        if (newSeccion.getCategoria() != null) {
            newSeccion.getCategoria().forEach((categoria) ->{
                if (!oldSeccion.getCategoria().contains(categoria)) {
                    oldSeccion.getCategoria().add(categoria);
                }
            });
            isNullEntity = true;
        }

        if (newSeccion.getCatalogo() != oldSeccion.getCatalogo() && newSeccion.getCatalogo() != null) {
            oldSeccion.setCatalogo(newSeccion.getCatalogo());
            isNullEntity = true;
        }

        if (newSeccion.getArticulos() != null) {
            newSeccion.getArticulos().forEach((articulo)->{
                if (!oldSeccion.getArticulos().contains(articulo)) {
                    oldSeccion.getArticulos().add(articulo);
                }
            });
            isNullEntity = true;
        }

        if (isNullEntity) {
            return repoSeccion.save(oldSeccion);
        }else{
            throw new InvalidDataException("Los datos ingresados no deben ser nulos");
        }
    }
}
