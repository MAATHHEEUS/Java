// controller/EnderecoController.java
package com.app.project.controller;

import com.app.project.facade.EnderecoFacade;
import com.app.project.model.Endereco;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {
    private final EnderecoFacade facade;

    @Autowired
    public EnderecoController(EnderecoFacade facade) {
        this.facade = facade;
    }

    @PostMapping("/salvar")
    public String adicionarEndereco(@RequestBody Endereco endereco) {
        return facade.cadastrarEndereco(endereco);
    }

    @GetMapping("/{id}")
    public List<Entidade> listarEnderecos(@PathVariable String id, @RequestParam String clienteId) {
        clienteId = clienteId.replaceAll("\\\"", ""); // Tirar as aspas

        Endereco endereco = new Endereco(null, null, null, null, null, null, null, null, Integer.parseInt(clienteId));
        endereco.setId(id);
        return facade.listarEnderecos(endereco);
    }

    @PutMapping("/atualizar/{id}")
    public String atualizarEndereco(@PathVariable String id, @RequestBody Endereco endereco) {
        endereco.setId(id);
        return facade.atualizarEndereco(endereco);
    }

    @DeleteMapping("/remover/{id}")
    public String deletarEndereco(@PathVariable String id) {
        Endereco endereco = new Endereco(null, null, null, null, null, null, null, null, 0);
        endereco.setId(id);
        return facade.removerEndereco(endereco);
    }
}
