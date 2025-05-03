package com.catalogo.proyecto.Services;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catalogo.proyecto.Exceptions.DataNotFoundException;
import com.catalogo.proyecto.Exceptions.InvalidDataException;
import com.catalogo.proyecto.Models.Pedido;
import com.catalogo.proyecto.Models.Usuario;
import com.catalogo.proyecto.Models.EntryModels.UsuarioEntry;
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
        if (this.validarUsuarioEntry(usuario)) {
            return repositorioUsuario.save(usuario);
        }else{
            throw new InvalidDataException("Error al insertar en la base de datos");
        }
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

    /**
     * Metodo para verificar los parametros de usuario
     * @param usuario Usuario
     * @return Si los parametros son validos devuelve true
     */
    private boolean validarUsuarioEntry(Usuario usuario){
        if (usuario.getUsername() == null || usuario.getUsername() == ""
            || usuario.getPassword() == null || usuario.getPassword() == ""
            || usuario.getMail() == null || usuario.getMail() == "") {
            // Si alguna de las entradas es vacia lanza un mensaje de error
            throw new InvalidDataException("Los datos de usuario no deben ser nulos");
        }
        System.out.println(usuario.getMail());
        boolean validacionMail = Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"+ "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$").matcher(usuario.getMail()).matches();
        if (!validacionMail) {
            // Si la expresion regular encuentra coincidencia para el mail
            throw new InvalidDataException("Mail no valido");
        }

        return true;
    }

    /**
     * Metodo para actualizar un Usuario en la base de datos
     * @param newUsuario Usuario con parametros nuevos
     * @return Usuario modificado
     */
    public Usuario updateUsuario(Long id, UsuarioEntry newUsuario) {
        Usuario oldUsuario = this.getUsuarioId(id);
        boolean isNullEntity = false;
        if (oldUsuario.getUsername() != newUsuario.getUsername() && newUsuario.getUsername() != null) {
            System.out.println("Entre");

            oldUsuario.setUsername(newUsuario.getUsername());
            isNullEntity = true;
        }

        if (oldUsuario.getPassword() != newUsuario.getPassword() && newUsuario.getPassword() != null) {
            System.out.println("Entre");
            // Futuro fix: una vez este implementado la encriptacion de contraseña, habra que primero descriptarla
            // para poder luego encriptarla nuevamente para volver a insertarla en la base de datos
            oldUsuario.setPassword(newUsuario.getPassword());
            isNullEntity = true;
        }

        if (oldUsuario.getMail() != newUsuario.getMail() && newUsuario.getMail() != null) {
            System.out.println(newUsuario.getMail());

            oldUsuario.setMail(newUsuario.getMail());
            isNullEntity = true;
        }

        if (isNullEntity) {
            return repositorioUsuario.save(oldUsuario);
        }else{
            throw new InvalidDataException("Los datos ingresados no deben ser nulos");
        }
    }
}
