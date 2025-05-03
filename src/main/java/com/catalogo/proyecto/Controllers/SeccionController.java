package com.catalogo.proyecto.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.catalogo.proyecto.Exceptions.DataNotFoundException;
import com.catalogo.proyecto.Models.Seccion;
import com.catalogo.proyecto.Services.SeccionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




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
    public ResponseEntity<Seccion> getSeccionId(@RequestParam Long id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.getSeccionId(id));
    }
    
    @GetMapping("/delete")
    public ResponseEntity<Seccion> elimiminarSeccion(@RequestParam Long id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.eliminarSeccion(id));
    }
    
    @PutMapping("modify/{id}")
    public ResponseEntity<Seccion> updateSeccio(@PathVariable Long id, @RequestBody Seccion seccion) {
        if (service.getSeccionId(id) != null) {
            return ResponseEntity.status(HttpStatus.OK).body(service.updatSeccion(seccion));
        }else{
            throw new DataNotFoundException("La seccion no se ha encontrado");
        }
    }
}
