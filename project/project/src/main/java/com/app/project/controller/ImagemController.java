// controller/ImagemController.java
package com.app.project.controller;

import com.app.project.facade.ImagemFacade;
import com.app.project.model.Imagem;
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
@RequestMapping("/imagens")
public class ImagemController {
    private final ImagemFacade facade;

    @Autowired
    public ImagemController(ImagemFacade facade) {
        this.facade = facade;
    }

    @PostMapping("/salvar")
    public String adicionarImagem(@RequestBody Imagem imagem) {
        return facade.cadastrarImagem(imagem);
    }

    @GetMapping("/{id}")
    public List<Entidade> listarImagens(@PathVariable String id, @RequestBody String produtoId) {
        produtoId = produtoId.replaceAll("\\\"", ""); // Tirar as aspas

        Imagem imagem = new Imagem(null, null, null, Integer.parseInt(produtoId));
        imagem.setId(id);
        return facade.listarImagens(imagem);
    }

    @PutMapping("/atualizar/{id}")
    public String atualizarImagem(@PathVariable String id, @RequestBody Imagem imagem) {
        imagem.setId(id);
        return facade.atualizarImagem(imagem);
    }

    @DeleteMapping("/remover/{id}")
    public String deletarImagem(@PathVariable String id) {
        Imagem imagem = new Imagem(null, null, null, 0);
        imagem.setId(id);
        return facade.removerImagem(imagem);
    }
}
