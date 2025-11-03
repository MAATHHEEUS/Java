// controller/TelefoneController.java
package com.app.project.controller;

import com.app.project.facade.TelefoneFacade;
import com.app.project.model.Telefone;
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
@RequestMapping("/telefones")
public class TelefoneController {
    private final TelefoneFacade facade;

    @Autowired
    public TelefoneController(TelefoneFacade facade) {
        this.facade = facade;
    }

    @PostMapping("/salvar")
    public String adicionarTelefone(@RequestBody Telefone telefone) {
        return facade.cadastrarTelefone(telefone);
    }

    @GetMapping("/{id}")
    public List<Entidade> listarTelefones(@PathVariable String id, @RequestParam String clienteId) {
        clienteId = clienteId.replaceAll("\\\"", ""); // Tirar as aspas

        Telefone telefone = new Telefone(null, null, null, null, Integer.parseInt(clienteId));
        telefone.setId(id);
        return facade.listarTelefones(telefone);
    }

    @PutMapping("/atualizar/{id}")
    public String atualizarTelefone(@PathVariable String id, @RequestBody Telefone telefone) {
        telefone.setId(id);
        return facade.atualizarTelefone(telefone);
    }

    @DeleteMapping("/remover/{id}")
    public String deletarTelefone(@PathVariable String id) {
        Telefone telefone = new Telefone(null, null, null, null, 0);
        telefone.setId(id);
        return facade.removerTelefone(telefone);
    }
}
