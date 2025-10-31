// controller/ProdCorController.java
package com.app.project.controller;

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

import com.app.project.dao.RelProdCorDAO;
import com.app.project.model.Cor;

@RestController
@RequestMapping("/produtocores")
public class ProdCorController {

    @Autowired
    private RelProdCorDAO prodCorDAO;

    @Autowired
    public ProdCorController(RelProdCorDAO prodCorDAO){
        this.prodCorDAO = prodCorDAO;
    }

    @GetMapping("/{produtoId}")
    public List<Integer> listarCores(@PathVariable String produtoId) {
        return prodCorDAO.buscarCores(Integer.parseInt(produtoId));
    }

    @PostMapping("/salvar/{produtoId},{cor}")
    public String salvarProdCor(@PathVariable String produtoId, @PathVariable String cor){
        prodCorDAO.salvar(cor, Integer.parseInt(produtoId));
        
        return "Cor salva com sucesso!";
    }

    @PutMapping("/atualizar/{id}")
    public String atualizarProdCor(@PathVariable String id, @RequestBody Cor cor){
        prodCorDAO.atualizarCorProduto(Integer.parseInt(id), cor);
        
        return "Cor atualizada!";
    }

    @DeleteMapping("/remover/{id}")
    public String deletarProdCor(@PathVariable String id) {
        prodCorDAO.deletarCorProduto(Integer.parseInt(id));

        return "Cor deletada do produto";
    }
    
}
