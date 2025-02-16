package com.catalogo.proyecto.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.catalogo.proyecto.Exceptions.ConexionBDException;
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

    public Optional<Usuario> getUsuarioId(Long idUsuario) {
        return Optional.ofNullable(repositorioUsuario.findById(idUsuario).orElseThrow(
            ()-> new DataNotFoundException("Usuario: "+String.valueOf(idUsuario)+" no encontrado"))
        );
    }

    public Usuario guardarUsuario(Usuario usuario) {
        try {
            return repositorioUsuario.save(usuario);
        } 
        catch (DataAccessException e) {
            throw new ConexionBDException("Error al acceder a la BD");
        }
    }

    public boolean eliminarUsuario(Long idUsuario) {
        if (!(this.getUsuarioId(idUsuario).isEmpty())) {
            repositorioUsuario.deleteById(idUsuario);
            return true;
        }else{
            return false;
        }
    }
    
    public boolean modificarUsuario(Long idUsuario) {
        if (repositorioUsuario.existsById(idUsuario)) {
            return true;
        }
        return false;
        
    }
}
