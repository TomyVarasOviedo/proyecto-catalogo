package com.catalogo.proyecto.Services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catalogo.proyecto.Exceptions.DataNotFoundException;
import com.catalogo.proyecto.Models.Pedido;
import com.catalogo.proyecto.Repositories.IOPedido;

@Service
public class PedidoService {
    @Autowired
    private IOPedido repoPedido;

    public Pedido realizarPedido(Pedido pedido) {
        return repoPedido.save(pedido);
    }

    public Pedido getPedidoId(UUID pedidoId) {
        Optional<Pedido> busqueda = repoPedido.findById(pedidoId);
        return busqueda.orElseThrow(
            () -> new DataNotFoundException("Pedido: "+String.valueOf(pedidoId) +" no encontrado")
        );
    }

    public List<Pedido> getPedidoAll() {
        return repoPedido.findAll();
    }

    public Pedido eliminarPedido(UUID pedido) {
        if (this.getPedidoId(pedido) != null) {
            repoPedido.deleteById(pedido);
            return repoPedido.findById(pedido).get();
        }
        throw new DataNotFoundException("Pedido "+String.valueOf(pedido)+" no encontrado");
    }

    public List<Pedido> getPedidoFecha(LocalDateTime fechaInicio, LocalDateTime fechaFinal) {
        List<Pedido> buscar = repoPedido.obtenerPedidoPeriodo(fechaInicio, fechaFinal);
        if (!buscar.isEmpty()) {
            return buscar;
        }else{
            throw new DataNotFoundException("Pedidos no encontrados en esa fecha");
        }
    }
}
