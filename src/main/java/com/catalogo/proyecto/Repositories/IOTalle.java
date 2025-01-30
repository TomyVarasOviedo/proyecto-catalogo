package com.catalogo.proyecto.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.catalogo.proyecto.Models.Talle;

public interface IOTalle extends JpaRepository<Talle, Integer>{
    
}
