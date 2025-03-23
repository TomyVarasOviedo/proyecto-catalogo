package com.catalogo.proyecto.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.catalogo.proyecto.Models.Categoria;
import com.catalogo.proyecto.Services.CategoriaService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {
    @Autowired
    private CategoriaService servicio;

    @GetMapping("/all")
    public List<Categoria> getCategoriaAll() {
        return servicio.getCategoriaAll();
    }
    
    @PostMapping("/")
    public ResponseEntity<Categoria> guardarCategoria(@RequestBody Categoria categoria) {
        return ResponseEntity.status(HttpStatus.CREATED).body(servicio.guardCategoria(categoria));
    }
    
    @GetMapping("/search")
    public ResponseEntity<Categoria> getCategoriaId(@RequestParam Long id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(servicio.getCategoriaId(id));
    }

    @GetMapping("/delete")
    public ResponseEntity<Categoria> eliminarCategoria(@RequestParam Long idCategoria) {
        return ResponseEntity.status(HttpStatus.CREATED).body(servicio.eliminaCategoria(idCategoria));
    }

}
