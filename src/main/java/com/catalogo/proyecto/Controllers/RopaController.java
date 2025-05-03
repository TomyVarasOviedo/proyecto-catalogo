package com.catalogo.proyecto.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.catalogo.proyecto.Exceptions.DataNotFoundException;
import com.catalogo.proyecto.Models.Ropa;
import com.catalogo.proyecto.Services.RopaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/api/ropa")
public class RopaController {
    @Autowired
    private RopaService service;

    @GetMapping("/search")
    public ResponseEntity<Ropa> getRopaId(@RequestParam Long id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.getRopaId(id));
    }
    
    @PostMapping("/")
    public ResponseEntity<Ropa> guardarRopa(@RequestBody Ropa articulo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardarRopa(articulo));
    }
    
    @GetMapping("/delete")
    public ResponseEntity<Ropa> eliminarRopa(@RequestParam Long id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.eliminarRopa(id));
    }

    @PutMapping("modify/{id}")
    public ResponseEntity<Ropa> updateArticulo(@PathVariable Long id, @RequestBody Ropa ropa) {
        if (service.getRopaId(id) != null) {
            return ResponseEntity.status(HttpStatus.OK).body(service.updateRopa(ropa));
        }else{
            throw new DataNotFoundException("El articulo no fue encontrado");
        }
    }
    
}
