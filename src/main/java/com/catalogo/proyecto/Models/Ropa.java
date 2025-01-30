package com.catalogo.proyecto.Models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity(name = "articulos")
public class Ropa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String nombre;
    @Column
    private String descripcion;
    
    @ManyToOne
    @JoinColumn(name = "seccion_id")
    private Seccion seccion;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido compra;

    @OneToOne
    private Talle talle;
    
}
