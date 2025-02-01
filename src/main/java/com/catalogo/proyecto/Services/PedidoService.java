package com.catalogo.proyecto.Services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catalogo.proyecto.Models.Pedido;
import com.catalogo.proyecto.Repositories.IOPedido;

@Service
public class PedidoService {
    @Autowired
    private IOPedido repoPedido;

    public Pedido realizarPedido(Pedido pedido) {
        return repoPedido.save(pedido);
    }

    public Optional<Pedido> getPedidoId(UUID pedidoId) {
        return repoPedido.findById(pedidoId);
    }

    public boolean eliminarPedido(UUID pedido) {
        if (!(this.getPedidoId(pedido).isEmpty())) {
            repoPedido.deleteById(pedido);
            return true;
        }
        return false;
    }

    public List<Pedido> getPedidoFecha(LocalDateTime fechaInicio, LocalDateTime fechaFinal) {
        try {
            return repoPedido.obtenerPedidoPeriodo(fechaInicio, fechaFinal);
        } catch (Exception e) {
            // Agregar excepciones acordes a los resultados que el sistema necesita
            return null;
        }
    }
}
