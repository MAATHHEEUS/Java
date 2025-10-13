// controller/CartaoController.java
package com.app.project.controller;

import com.app.project.facade.CartaoFacade;
import com.app.project.model.Cartao;
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
@RequestMapping("/cartoes")
public class CartaoController {
    private final CartaoFacade facade;

    @Autowired
    public CartaoController(CartaoFacade facade) {
        this.facade = facade;
    }

    @PostMapping("/salvar")
    public String adicionarCartao(@RequestBody Cartao cartao) {
        return facade.cadastrarCartao(cartao);
    }

    @GetMapping("/{id}")
    public List<Entidade> listarCartoes(@PathVariable String id, @RequestBody String clienteId) {
        clienteId = clienteId.replaceAll("\\\"", ""); // Tirar as aspas

        Cartao cartao = new Cartao(null, null, null, null, null, null, Integer.parseInt(clienteId));
        cartao.setId(id);
        return facade.listarCartoes(cartao);
    }

    @PutMapping("/atualizar/{id}")
    public String atualizarCartao(@PathVariable String id, @RequestBody Cartao cartao) {
        cartao.setId(id);
        return facade.atualizarCartao(cartao);
    }

    @DeleteMapping("/remover/{id}")
    public String deletarCartao(@PathVariable String id) {
        Cartao cartao = new Cartao(null, null, null, null, null, null, 0);
        cartao.setId(id);
        return facade.removerCartao(cartao);
    }
}
