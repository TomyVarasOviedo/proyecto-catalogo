package com.catalogo.proyecto.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.catalogo.proyecto.Models.Ropa;

public interface IORopa extends JpaRepository<Ropa, Long>{
    
}
