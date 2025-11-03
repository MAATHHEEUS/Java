// controller/EstoqueController.java
package com.app.project.controller;

import com.app.project.facade.EstoqueFacade;
import com.app.project.model.Estoque;
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
@RequestMapping("/estoque")
public class EstoqueController {
    private final EstoqueFacade facade;

    @Autowired
    public EstoqueController(EstoqueFacade facade) {
        this.facade = facade;
    }

    @PostMapping("/salvar")
    public String adicionarEstoque(@RequestBody Estoque estoque) {
        return facade.cadastrarEstoque(estoque);
    }

    @GetMapping("")
    public List<Entidade> listarEstoque(@RequestBody String produtoId) {
        produtoId = produtoId.replaceAll("\\\"", ""); // Tirar as aspas

        Estoque estoque = new Estoque(0, Integer.parseInt(produtoId));
        return facade.listarEstoque(estoque);
    }

    @PutMapping("/atualizar/{id}")
    public String atualizarEstoque(@PathVariable String id, @RequestBody Estoque estoque) {
        estoque.setId(id);
        return facade.atualizarEstoque(estoque);
    }

    @DeleteMapping("/remover/{id}")
    public String deletarEstoque(@PathVariable String id) {
        Estoque estoque = new Estoque(0, 0);
        estoque.setId(id);
        return facade.removerEstoque(estoque);
    }
}
