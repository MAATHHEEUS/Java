// controller/ClienteController.java
package com.app.project.controller;

import com.app.project.facade.ClienteFacade;
import com.app.project.model.Cliente;
import com.app.project.model.Entidade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private final ClienteFacade facade;

    @Autowired
    public ClienteController(ClienteFacade facade) {
        this.facade = facade;
    }

    @PostMapping("/salvar")
    public String adicionarCliente(@RequestBody Cliente cliente) {
        return facade.cadastrarCliente(cliente);
    }

    @GetMapping("/{id}")
    public List<Entidade> listarClientes(@PathVariable String id) {
        Cliente cliente = new Cliente(id, null, null, null, null, null);
        return facade.listarClientes(cliente);
    }

    @PutMapping("/atualizar/{id}")
    public String atualizarCliente(@PathVariable String id, @RequestBody Cliente cliente) {
        cliente.setId(id);
        return facade.atualizarCliente(cliente);
    }

    @DeleteMapping("/remover/{id}")
    public void deletarCliente(@PathVariable String id) {
        Cliente cliente = new Cliente(id, null, null, null, null, null);
        facade.removerCliente(cliente);
    }

    @DeleteMapping("/enderecos/{id}")
    public void deletarEndereco(@PathVariable String id) {
        Cliente cliente = new Cliente(id, null, null, null, null, null);
        facade.removerEndereco(cliente);
    }
}
