package com.catalogo.proyecto.Repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.catalogo.proyecto.Models.Pedido;

public interface IOPedido extends JpaRepository<Pedido, UUID>{
    
    /**
     * Metodo para obtener los pedidos realizados durante una determminada fecha
     * @param fechaInicio Fecha de inicio: LocalDateTime
     * @param fechaFinal Fecha de final: LocalDateTime
     * @return Lista de pedidos: List<Pedido>
     */
    @Query(value = "SELECT * FROM pedidos WHERE fecha_pedido BETWEEN :fechaInicio AND :fechaFin", nativeQuery = true)
    public List<Pedido> obtenerPedidoPeriodo(@Param("fecha_inicio") LocalDateTime fechaInicio, @Param("fecha_final") LocalDateTime fechaFinal);
}
