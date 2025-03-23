package com.catalogo.proyecto.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catalogo.proyecto.Exceptions.DataNotFoundException;
import com.catalogo.proyecto.Models.Pedido;
import com.catalogo.proyecto.Models.Usuario;
import com.catalogo.proyecto.Repositories.IOUsuario;

@Service
public class UsuarioService {
    @Autowired
    private IOUsuario repositorioUsuario;

    /**
     * Metodo para acceder a UserRepository
     * @param idUsuario id de usuario: Long
     * @return List<Pedido> 
     */
    public List<Pedido> obtenerComprasUsuario(Long idUsuario) {
        return repositorioUsuario.obtenerComprasUsuario(idUsuario);
    }

    public List<Usuario> getUsuariosAll() {
        return repositorioUsuario.findAll();
    }

    public Usuario getUsuarioId(Long idUsuario) {
        Optional<Usuario> busqueda = repositorioUsuario.findById(idUsuario);
        return busqueda.orElseThrow(
            () -> new DataNotFoundException("Usuario "+String.valueOf(idUsuario)+" no encontrado")
        );
    }

    public Usuario guardarUsuario(Usuario usuario) {
        return repositorioUsuario.save(usuario);
    }

    public Usuario eliminarUsuario(Long idUsuario) {
        Usuario busqueda = this.getUsuarioId(idUsuario);
        if (busqueda != null) {
            repositorioUsuario.deleteById(idUsuario);
            return busqueda;
        }else{
            throw new DataNotFoundException("Usuario "+String.valueOf(idUsuario)+" no encontrado");
        }
    }
    
    public boolean modificarUsuario(Long idUsuario) {
        if (repositorioUsuario.existsById(idUsuario)) {
            return true;
        }
        return false;
        
    }
}
