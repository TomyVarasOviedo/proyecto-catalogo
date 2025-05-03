package com.catalogo.proyecto.Services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catalogo.proyecto.Exceptions.DataNotFoundException;
import com.catalogo.proyecto.Exceptions.InvalidDataException;
import com.catalogo.proyecto.Models.Pedido;
import com.catalogo.proyecto.Models.Ropa;
import com.catalogo.proyecto.Repositories.IOPedido;

@Service
public class PedidoService {
    @Autowired
    private IOPedido repoPedido;
    @Autowired
    private UsuarioService serviceUsuario;

    public Pedido realizarPedido(Pedido pedido) {
        if (this.validarPedidoEntry(pedido)) {
            return repoPedido.save(pedido);
        }else{
            throw new InvalidDataException("Error al ingresar a la base de datos");
        }
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

    /**
     * Metodo para validar los parametros para la entrada de un pedido
     * @param pedido Pedido
     * @return Si los parametos son validos devuelve true
     */
    private boolean validarPedidoEntry(Pedido pedido) {
        if (pedido.getComprador() == null || pedido.getDescripcion() == "" 
        || pedido.getArticulos() == null ||pedido.getArticulos().isEmpty() ||
         pedido.getVendedor() == null) {
            throw new InvalidDataException("Los datos datos de un pedido no son validos");
        }
        serviceUsuario.getUsuarioId(pedido.getComprador().getId());
        serviceUsuario.getUsuarioId(pedido.getVendedor().getId());

        return true;
    }

    /**
     * Metodo para actualizar los parametros de un pedido
     * @param newPedido Pedido nuevo
     * @return Pedido actualizado
     */
    public Pedido updatePedido(Pedido newPedido) {
        Pedido oldPedido = this.getPedidoId(newPedido.getId());
        boolean isNullEntity = false;
        if (oldPedido.getComprador() != newPedido.getComprador() && newPedido.getComprador() != null) {
            serviceUsuario.getUsuarioId(newPedido.getComprador().getId());
            oldPedido.setComprador(newPedido.getComprador());
            isNullEntity = true;
        }

        if (oldPedido.getDescripcion() != newPedido.getDescripcion() && newPedido.getDescripcion() != null) {
            oldPedido.setDescripcion(newPedido.getDescripcion());
            isNullEntity = true;
        }

        if (oldPedido.getVendedor() != newPedido.getVendedor() && newPedido.getVendedor() != null) {
            serviceUsuario.getUsuarioId(newPedido.getVendedor().getId());
            oldPedido.setVendedor(newPedido.getVendedor());
            isNullEntity = true;
        }

        if (newPedido.getArticulos() != null) {
            for (Ropa articulo : newPedido.getArticulos()) {
                if(!oldPedido.getArticulos().contains(articulo)){
                    oldPedido.getArticulos().add(articulo);
                }
            }
        }

        if (isNullEntity) {
            return repoPedido.save(oldPedido);
        }else{
            throw new InvalidDataException("Los datos ingresados no deben ser nulos");
        }
    }
}
