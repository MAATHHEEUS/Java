// controller/ProdutoController.java
package com.app.project.controller;

import com.app.project.facade.ProdutoFacade;
import com.app.project.model.Produto;
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
@RequestMapping("/produtos")
public class ProdutoController {
    private final ProdutoFacade facade;

    @Autowired
    public ProdutoController(ProdutoFacade facade) {
        this.facade = facade;
    }

    @PostMapping("/salvar")
    public String adicionarProduto(@RequestBody Produto produto) {
        return facade.salvarProduto(produto);
    }

    @GetMapping("/{id}")
    public List<Entidade> listarProdutos(@PathVariable String id) {
        Produto produto = new Produto(id, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
        produto.setId(id);
        return facade.listarProdutos(produto);
    }

    @PutMapping("/atualizar/{id}")
    public String atualizarProduto(@PathVariable String id, @RequestBody Produto produto) {
        produto.setId(id);
        return facade.atualizarProduto(produto);
    }

    @DeleteMapping("/remover/{id}")
    public String deletarProduto(@PathVariable String id) {
        Produto produto = new Produto(id, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
        produto.setId(id);
        return facade.removerProduto(produto);
    }
}
