package com.catalogo.proyecto.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.catalogo.proyecto.Models.Categoria;

public interface IOCategoria extends JpaRepository<Categoria, Long>{

    
}