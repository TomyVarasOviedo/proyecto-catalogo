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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "pedidos")
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {
    @SuppressWarnings("deprecation")
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
        )
    @Column(columnDefinition = "BINARY(16)")
    @Getter
    @Setter
    private UUID id;
    @OneToOne
    @Getter
    @Setter
    private Usuario comprador;

    @OneToMany(mappedBy = "id")
    @Getter
    @Setter
    private List<Ropa> articulos;
    @Column
    @Getter
    @Setter
    private String descripcion;
    @OneToOne
    @Getter
    @Setter
    private Usuario vendedor;

}
