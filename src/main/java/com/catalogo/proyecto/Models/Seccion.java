package com.catalogo.proyecto.Models;

import java.util.ArrayList;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity(name = "secciones")
public class Seccion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String nombre;
    @Column
    private String descripcion;

    @OneToMany
    private ArrayList<Ropa> articulos;
    
    @OneToMany(mappedBy = "seccion")
    private ArrayList<Categoria> categoria;

    @ManyToOne
    @JoinColumn(name = "catalogo_id")
    private Catalogo catalogo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // public Categoria getCategoria() {
    //     return categoria;
    // }

    // public void setCategoria(Categoria categoria) {
    //     this.categoria = categoria;
    // }

}
