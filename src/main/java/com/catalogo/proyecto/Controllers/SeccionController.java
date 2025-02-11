package com.catalogo.proyecto.Controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.catalogo.proyecto.Models.Seccion;
import com.catalogo.proyecto.Services.SeccionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/seccion")
public class SeccionController {
    @Autowired
    private SeccionService service;

    @PostMapping("/")
    public ResponseEntity<Seccion> guardarSeccion(@RequestBody Seccion seccion) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardarSeccion(seccion));
    }

    @GetMapping("/search")
    public Seccion getSeccionId(@RequestParam Long id) {
        Optional<Seccion> buscar = service.getSeccionId(id);;
        if (!buscar.isEmpty()) {
            return buscar.get();
        }else{
            return null;
        }
    }
    
    @GetMapping("/delete")
    public ResponseEntity<Seccion> elimiminarSeccion(@RequestParam Long id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.eliminarSeccion(id));
    }
    
}
