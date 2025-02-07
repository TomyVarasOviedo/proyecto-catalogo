package com.catalogo.proyecto.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catalogo.proyecto.Models.Categoria;
import com.catalogo.proyecto.Repositories.IOCategoria;

@Service
public class CategoriaService {
    @Autowired
    private IOCategoria repoCategoria;

    public Categoria guardCategoria(Categoria categoria) {
        return repoCategoria.save(categoria);
    }

    public Optional<Categoria> getCategoriaId(Long idCategoria) {
        Optional<Categoria> busqueda = repoCategoria.findById(idCategoria);
        if (!busqueda.isEmpty()) {
            return busqueda;
        }
        return null;
    }

    public List<Categoria> getCategoriaAll() {
        return repoCategoria.findAll();
    }

    public Categoria eliminaCategoria(Long idCategoria) {
        Optional<Categoria> busqueda = this.getCategoriaId(idCategoria);
        if (!busqueda.isEmpty()) {
            repoCategoria.deleteById(idCategoria);
            return busqueda.get();
        }else{
            return null;
        }
    }
}       
