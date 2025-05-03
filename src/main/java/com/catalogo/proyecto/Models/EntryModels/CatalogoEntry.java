package com.catalogo.proyecto.Models.EntryModels;

import java.util.List;

import com.catalogo.proyecto.Models.Seccion;
import com.catalogo.proyecto.Models.Usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class CatalogoEntry {
    @Getter @Setter
    private Usuario usuario;
    @Getter @Setter
    private int cantidad;
    @Getter @Setter
    private List<Seccion> secciones;

}
