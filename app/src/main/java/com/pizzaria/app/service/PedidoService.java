package com.pizzaria.app.service;

import com.pizzaria.app.entity.Pedido;
import com.pizzaria.app.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido novoPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    public Pedido atualizarPedido(String id, Pedido pedidoAtualizado) {
        return pedidoAtualizado;
    }

    public String deletarPedido(String id){
        return "Pedido deletado";
    }
}
