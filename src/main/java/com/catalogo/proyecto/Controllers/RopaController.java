package com.catalogo.proyecto.Controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.catalogo.proyecto.Models.Ropa;
import com.catalogo.proyecto.Services.RopaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/ropa")
public class RopaController {
    @Autowired
    private RopaService service;

    @GetMapping("/search")
    public Ropa getRopaId(@RequestParam Long id) {
        Optional<Ropa> articulo = service.getRopaId(id);
        if (!articulo.isEmpty()) {
            return articulo.get();
        }
        return null;
    }
    
    @PostMapping("/")
    public ResponseEntity<Ropa> guardarRopa(@RequestBody Ropa articulo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardRopa(articulo));
    }
    
    @GetMapping("/delete")
    public ResponseEntity<Ropa> eliminarRopa(@RequestParam Long id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.eliminarRopa(id));
    }
    
}
