package com.catalogo.proyecto.Models;

import java.util.ArrayList;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private Usuario comprador;
    @Column
    private ArrayList<Ropa> articulos;
    @Column
    private String descripcion;
    @Column
    private Usuario vendedor;

}
