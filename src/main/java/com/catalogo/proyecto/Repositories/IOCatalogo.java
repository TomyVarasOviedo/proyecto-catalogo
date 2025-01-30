package com.catalogo.proyecto.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.catalogo.proyecto.Models.Catalogo;

public interface IOCatalogo extends JpaRepository<Catalogo, Long>{
    
    
}