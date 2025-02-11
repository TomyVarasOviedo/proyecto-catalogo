package com.catalogo.proyecto.Controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.catalogo.proyecto.Models.Pedido;
import com.catalogo.proyecto.Services.PedidoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/pedido")
public class PedidoController {
    @Autowired
    private PedidoService servicio;

    // @GetMapping("/search")
    // public Pedido getPedidoId(@RequestParam UUID id) {
    //     return servicio.getPedidoId(id).get();
    // }

    @GetMapping("/search")
    public List<Pedido> getMethodName(@RequestParam LocalDateTime fechaInicio, @RequestParam LocalDateTime fechaFin) {
        return servicio.getPedidoFecha(fechaInicio, fechaFin);
    }
    @GetMapping("/all")
    public List<Pedido> getPedidoall() {
        return servicio.getPedidoAll();
    }
    
    @PostMapping("/")
    public ResponseEntity<Pedido> guardarPedido(@RequestBody Pedido pedido) {
        return ResponseEntity.status(HttpStatus.CREATED).body(servicio.realizarPedido(pedido));
    }
    
    @GetMapping("/delete")
    public ResponseEntity<Pedido> deletePedido(@RequestParam UUID idPedido) {
        return ResponseEntity.status(HttpStatus.OK).body(servicio.eliminarPedido(idPedido));
    }
    
    
}
