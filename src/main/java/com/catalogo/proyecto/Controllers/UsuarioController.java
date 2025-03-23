package com.catalogo.proyecto.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.catalogo.proyecto.Models.Usuario;
import com.catalogo.proyecto.Services.UsuarioService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {
    
    @Autowired
    private UsuarioService service;

    @PostMapping("/")
    public ResponseEntity<Usuario> guardarUsuario(@RequestBody Usuario usuario) {
        Usuario usuarioCreado = service.guardarUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCreado);
    }

    @GetMapping("/")
    public List<Usuario> getUsuariosAll() {
        return service.getUsuariosAll();
    }
    
    @GetMapping("/{idUsuario}")
    public ResponseEntity<Usuario> getUsuarioId(@PathVariable Long idUsuario) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.getUsuarioId(idUsuario));
    }

    @GetMapping("/delete/{idUsuario}")
    public ResponseEntity<Usuario> eliminarUsuario(@PathVariable Long idUsuario) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.eliminarUsuario(idUsuario));
    }
    
}
