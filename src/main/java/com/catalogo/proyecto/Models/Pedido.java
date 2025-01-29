package com.catalogo.proyecto.Models;

import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity(name = "pedidos")
public class Pedido {
    @SuppressWarnings("deprecation")
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
        )
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;
    @OneToOne
    private Usuario comprador;

    @OneToMany(mappedBy = "id")
    private List<Ropa> articulos;
    @Column
    private String descripcion;
    @OneToOne
    private Usuario vendedor;

}
