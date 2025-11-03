// controller/VendaController.java
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

import com.app.project.facade.VendaFacade;
import com.app.project.model.Entidade;
import com.app.project.model.ProdutoVenda;
import com.app.project.model.Status;
import com.app.project.model.Venda;

@RestController
@RequestMapping("/vendas")
public class VendaController {
    private final VendaFacade facade;

    @Autowired
    public VendaController(VendaFacade facade) {
        this.facade = facade;
    }

    @PostMapping("/salvar")
    public String adicionarVenda(@RequestBody Venda venda){
        return facade.cadastrarVenda(venda);
    }

    @GetMapping("/{id}")
    public List<Entidade> listarVendas(@PathVariable String id){
        Venda venda = new Venda(0, null, null, 0, 0, null, null, null, null, null);
        venda.setId(id);
        return facade.listarVendas(venda);
    }

    @PutMapping("/atualizar/{id}")
    public String atualizarVenda(@PathVariable String id, @RequestBody Venda venda){
        venda.setId(id);
        return facade.atualizarVenda(venda);
    }

    @PutMapping("/atualizarStatus/{id}")
    public String atualizarVenda(@PathVariable String id, @RequestBody Status status){
        Venda venda = new Venda(0, null, null, 0, 0, null, null, null, null, null);
        venda.setId(id);
        return facade.atualizarStatus(venda, status);
    }

    @PutMapping("/itemVenda/{id}")
    public String atualizarItemVenda(@PathVariable String id, @RequestBody ProdutoVenda pv){
        pv.setId(id);
        return facade.atualizarItemVenda(pv);
    }

    @DeleteMapping("/remover/{id}")
    public String deletarVenda(@PathVariable String id){
        Venda venda = new Venda(0, null, null, 0, 0, null, null, null, null, null);
        venda.setId(id);
        return facade.removerVenda(venda);
    }
}
