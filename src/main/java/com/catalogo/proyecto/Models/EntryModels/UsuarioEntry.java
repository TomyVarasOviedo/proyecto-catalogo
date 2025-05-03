package com.catalogo.proyecto.Models.EntryModels;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class UsuarioEntry {
    @Getter @Setter
    private String username;
    @Getter @Setter
    private String mail;
    @Getter @Setter
    private String password;
}
