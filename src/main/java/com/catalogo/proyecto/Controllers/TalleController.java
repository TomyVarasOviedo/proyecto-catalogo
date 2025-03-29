package com.catalogo.proyecto.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.catalogo.proyecto.Models.Talle;
import com.catalogo.proyecto.Services.TalleService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/talle")
public class TalleController {
    @Autowired
    private TalleService talleService;

    @GetMapping("/")
    public List<Talle> getAllTalles() {
        return talleService.getAllTalles();
    }
    
    @PostMapping("/")
    public ResponseEntity<Talle> guardarTalle(@RequestBody Talle talle) {
        Talle talleCreado = talleService.guardarTalle(talle);
        return ResponseEntity.status(HttpStatus.CREATED).body(talleCreado);
    }
    @GetMapping("/search/{id}")
    public ResponseEntity<Talle> getTalleId(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(talleService.getTalleId(id));
    }
    
    @GetMapping("/delete/{id}")
    public ResponseEntity<Talle> eliminarTalleId(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(talleService.eliminarTalleId(id));
    }
    
}
