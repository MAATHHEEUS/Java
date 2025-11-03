// controller/CupomController.java
package com.app.project.controller;

import com.app.project.facade.CupomFacade;
import com.app.project.model.Cupom;
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
@RequestMapping("/cupons")
public class CupomController {
    private final CupomFacade facade;

    @Autowired
    public CupomController(CupomFacade facade) {
        this.facade = facade;
    }

    @PostMapping("/salvar")
    public String adicionarCupom(@RequestBody Cupom cupom) {
        return facade.cadastrarCupom(cupom);
    }

    @GetMapping("/{id}")
    public List<Entidade> listarCupons(@PathVariable String id, @RequestParam String clienteId) {
        clienteId = clienteId.replaceAll("\\\"", ""); // Tirar as aspas

        Cupom cupom = new Cupom(null, null, null, null, Integer.parseInt(clienteId));
        cupom.setId(id);
        return facade.listarCupons(cupom);
    }

    @PutMapping("/atualizar/{id}")
    public String atualizarCupom(@PathVariable String id, @RequestBody Cupom cupom) {
        cupom.setId(id);
        return facade.atualizarCupom(cupom);
    }

    @DeleteMapping("/remover/{id}")
    public String deletarCupom(@PathVariable String id) {
        Cupom cupom = new Cupom(null, null, null, null, 0);
        cupom.setId(id);
        return facade.removerCupom(cupom);
    }
}
