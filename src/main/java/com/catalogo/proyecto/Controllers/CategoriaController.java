package com.catalogo.proyecto.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.catalogo.proyecto.Models.Categoria;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
    // private CategoriaService servicio;

    // @GetMapping("/all")
    // public List<Categoria> getCategoriaAll() {
    //     return servicio.getCategoriaAll();
    // }
    
    // @PostMapping("/")
    // public ResponseEntity<Categoria> guardarCategoria(@RequestBody Categoria categoria) {
    //     return ResponseEntity.status(HttpStatus.CREATED).body(servicio.guardarCategoria(categoria));
    // }
    
    // @GetMapping("/search")
    // public Categoria getCategoriaId(@RequestParam Long id) {
    //     return servicio.getCategoriaId(id).get();
    // }

    // @GetMapping("/delete")
    // public ResponseEntity<Categoria> eliminarCategoria(@RequestParam Long idCategoria) {
    //     return ResponseEntity.status(HttpStatus.CREATED).body(servicio.eliminarCategoria(idCategoria));
    // }

}
