package com.pizzaria.app.controller;

import com.pizzaria.app.entity.Produto;
import com.pizzaria.app.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public Produto novoProduto(@RequestBody Produto produto) {
        return produtoService.novoProduto(produto);
    }

    @GetMapping
    public List<Produto> listarProdutos() {
        return produtoService.listarProdutos();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable String id, @RequestBody Produto produtoAtualizado) {
        Produto produto = produtoService.atualizarProduto(id, produtoAtualizado);
        return ResponseEntity.ok(produto);
    }

    @PostMapping("/atualizar/{id}")
    public String atualizarProduto(@PathVariable String id, @ModelAttribute Produto produtoAtualizado, Model model) {
        produtoService.atualizarProduto(id, produtoAtualizado);
        return "redirect:/produtos";
    }

    @DeleteMapping("/{id}")
    public String deletarProduto(@PathVariable String id, Model model) {
        produtoService.deletarProduto(id);
        return "redirect:/produtos";
    }
}
