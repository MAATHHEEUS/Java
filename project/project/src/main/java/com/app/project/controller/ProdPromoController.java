// controller/ProdPromoController.java
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

import com.app.project.dao.RelProdPromocaoDAO;
import com.app.project.model.Promocao;

@RestController
@RequestMapping("/produtopromos")
public class ProdPromoController {

    @Autowired
    private RelProdPromocaoDAO prodPromoDAO;

    @Autowired
    public ProdPromoController(RelProdPromocaoDAO prodPromoDAO){
        this.prodPromoDAO = prodPromoDAO;
    }

    @GetMapping("/{produtoId}")
    public List<Integer> listarPromos(@PathVariable String produtoId) {
        return prodPromoDAO.buscarPromocoes(Integer.parseInt(produtoId));
    }

    @PostMapping("/salvar/{produtoId}")
    public String salvarProdPromo(@PathVariable String produtoId, @RequestBody Promocao promo){
        prodPromoDAO.salvar(promo, Integer.parseInt(produtoId));
        
        return "Promoção salva com sucesso!";
    }

    @PutMapping("/atualizar/{id}")
    public String atualizarPromoProduto(@PathVariable String id, @RequestBody Promocao promo){
        prodPromoDAO.atualizarPromoProduto(Integer.parseInt(id), promo);
        
        return "Promoção atualizada!";
    }

    @DeleteMapping("/remover/{id}")
    public String deletarProdPromo(@PathVariable String id) {
        prodPromoDAO.deletarPromoProduto(Integer.parseInt(id));
        return "Promoção deletada do produto";
    }
    
}
