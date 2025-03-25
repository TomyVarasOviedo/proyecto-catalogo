package com.catalogo.proyecto.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catalogo.proyecto.Exceptions.DataNotFoundException;
import com.catalogo.proyecto.Exceptions.InvalidDataException;
import com.catalogo.proyecto.Models.Categoria;
import com.catalogo.proyecto.Repositories.IOCategoria;

@Service
public class CategoriaService {
    @Autowired
    private IOCategoria repoCategoria;

    public Categoria guardCategoria(Categoria categoria) {
        if (this.validarCategoriaEntry(categoria)) {
            return repoCategoria.save(categoria);
        }else{
            throw new InvalidDataException("Error al insertar en la base de datos");
        }
    }

    public Categoria getCategoriaId(Long idCategoria) {
        Optional<Categoria> busqueda = repoCategoria.findById(idCategoria);
        return busqueda.orElseThrow(
            ()-> new DataNotFoundException("Categoria "+String.valueOf(idCategoria) +" no encontrado")
        );
    }

    public List<Categoria> getCategoriaAll() {
        return repoCategoria.findAll();
    }

    public Categoria eliminaCategoria(Long idCategoria) {
        Categoria busqueda = this.getCategoriaId(idCategoria);
        if (busqueda != null) {
            repoCategoria.deleteById(idCategoria);
            return busqueda;
        }else{
            throw new DataNotFoundException("Categoria "+String.valueOf(idCategoria)+" no encontrada");
        }
    }

    private boolean validarCategoriaEntry(Categoria categoria) {
        if (categoria.getNombre() == "" || categoria.getNombre() == null 
        || categoria.getDescripcion() == "" || categoria.getDescripcion() == null) {
            throw new InvalidDataException("Los datos de una categoria no deben ser nulos");
        }
        return true;
    }
}       
