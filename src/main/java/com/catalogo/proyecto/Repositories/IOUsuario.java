package com.catalogo.proyecto.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.catalogo.proyecto.Models.Pedido;
import com.catalogo.proyecto.Models.Usuario;

public interface IOUsuario extends JpaRepository<Usuario, Long>{

    /**
     * Metodo para obtener las compras realizadas por un Usuario
     * @param idUsuario ID de Usuario
     * @return Lista de compras: List<Pedido>
     */
    @Query(value = "SELECT  * FROM usuarios", nativeQuery = true)
    public List<Pedido> obtenerComprasUsuario(@Param("id_usuario") Long idUsuario);
}
