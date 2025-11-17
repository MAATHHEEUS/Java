// controller/ProdMaterialController.java
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

import com.app.project.dao.RelProdMaterialDAO;
import com.app.project.model.Material;

@RestController
@RequestMapping("/produtomateriais")
public class ProdMaterialController {

    @Autowired
    private RelProdMaterialDAO prodMaterialDAO;

    @Autowired
    public ProdMaterialController(RelProdMaterialDAO prodMaterialDAO){
        this.prodMaterialDAO = prodMaterialDAO;
    }

    @GetMapping("/{produtoId}")
    public List<Integer> listarMateriais(@PathVariable String produtoId) {
        return prodMaterialDAO.buscarMateriais(Integer.parseInt(produtoId));
    }

    @PostMapping("/salvar/{produtoId}")
    public String salvarProdMaterial(@PathVariable String produtoId, @RequestBody Material material){
        prodMaterialDAO.salvar(material, Integer.parseInt(produtoId));
        
        return "Material salvo com sucesso!";
    }

    @PutMapping("/atualizar/{produtoId},{oldMaterial}")
    public String atualizarMaterialProduto(@PathVariable String produtoId, @PathVariable String oldMaterial, @RequestBody Material material){
        prodMaterialDAO.atualizarMaterialProduto(Integer.parseInt(produtoId), Integer.parseInt(oldMaterial), material);
        
        return "Material atualizado!";
    }

    @DeleteMapping("/remover/{produtoId},{material}")
    public String deletarProdMaterial(@PathVariable String produtoId, @PathVariable String material) {
        prodMaterialDAO.deletarMaterialProduto(Integer.parseInt(produtoId), Integer.parseInt(material));
        return "Material deletado do produto";
    }
    
}
