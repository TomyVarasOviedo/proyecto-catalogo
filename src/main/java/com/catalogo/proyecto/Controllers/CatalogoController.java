package com.catalogo.proyecto.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.catalogo.proyecto.Models.Catalogo;
import com.catalogo.proyecto.Repositories.IOCatalogo;
import com.catalogo.proyecto.Services.CatalogoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;





@RestController
@RequestMapping("/api/catalogo")
public class CatalogoController {

    @Autowired
    private CatalogoService servicio;
    @Autowired
    private IOCatalogo repoCatalogo;

    @GetMapping("/")
    public List<Catalogo> getCatalogoAll() {
        return repoCatalogo.findAll(); 
    }
    
    @GetMapping("/{idCatalogo}")
    public ResponseEntity<Catalogo> getCatalogoId(@PathVariable Long idCatalogo) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(servicio.getCatalogoId(idCatalogo));
    }
    
    @PostMapping("/")
    public ResponseEntity<Catalogo> guardarCatalogo(@RequestBody Catalogo catalogo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(servicio.guardarCatalogo(catalogo));
    }
    
    @GetMapping("/delete/{idCatalogo}")
    public ResponseEntity<Catalogo> eliminarCatalogoId(@PathVariable Long idCatalogo) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(servicio.eliminarCatalogo(idCatalogo));
    }
    
}
