package com.pizzaria.app.controller;

import com.pizzaria.app.entity.Pedido;
import com.pizzaria.app.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public Pedido novoPedido(@RequestBody Pedido pedido) {
        return pedidoService.novoPedido(pedido);
    }

    @GetMapping
    public List<Pedido> listarPedidos() {
        return pedidoService.listarPedidos();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> atualizarPedido(@PathVariable String id, @RequestBody Pedido pedidoAtualizado) {
        Pedido pedido = pedidoService.atualizarPedido(id, pedidoAtualizado);
        return ResponseEntity.ok(pedido);
    }

    @PostMapping("/atualizar/{id}")
    public String atualizarPedido(@PathVariable String id, @ModelAttribute Pedido pedidoAtualizado, Model model) {
        pedidoService.atualizarPedido(id, pedidoAtualizado);
        return "redirect:/pedidos";
    }

    @DeleteMapping("/{id}")
    public String deletarPedido(@PathVariable String id, Model model) {
        pedidoService.deletarPedido(id);
        return "redirect:/pedidos";
    }
}
