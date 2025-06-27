package com.pizzaria.app.controller;

import com.pizzaria.app.entity.Cliente;
import com.pizzaria.app.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.ui.Model;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public Cliente novoCliente(@RequestBody Cliente cliente) {
        return clienteService.novoCliente(cliente);
    }

    @GetMapping
    public List<Cliente> listarClientes() {
        return clienteService.listarClientes();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable String id, @RequestBody Cliente clienteAtualizado) {
        Cliente cliente = clienteService.atualizarCliente(id, clienteAtualizado);
        return ResponseEntity.ok(cliente);
    }

    @PostMapping("/atualizar/{id}")
    public String atualizarCliente(@PathVariable String id, @ModelAttribute Cliente clienteAtualizado, Model model) {
        clienteService.atualizarCliente(id, clienteAtualizado);
        return "redirect:/clientes";
    }

    @DeleteMapping("/{id}")
    public String deletarCliente(@PathVariable String id, Model model) {
        clienteService.deletarCliente(id);
        return "redirect:/clientes";
    }
}
