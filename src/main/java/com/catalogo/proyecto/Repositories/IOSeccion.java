package com.catalogo.proyecto.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.catalogo.proyecto.Models.Seccion;

public interface IOSeccion extends JpaRepository<Seccion, Long>{
    
}
