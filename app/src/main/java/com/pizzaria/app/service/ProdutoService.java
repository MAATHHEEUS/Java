package com.pizzaria.app.service;

import com.pizzaria.app.entity.Produto;
import com.pizzaria.app.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto novoProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    public Produto atualizarProduto(String id, Produto produtoAtualizado) {
        return produtoAtualizado;
    }

    public String deletarProduto(String id){
        return "Produto deletado";
    }
}
