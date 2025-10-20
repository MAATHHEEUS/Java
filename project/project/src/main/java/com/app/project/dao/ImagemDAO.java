package com.app.project.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.app.project.model.Entidade;
import com.app.project.model.Imagem;

@Repository
public class ImagemDAO implements IDAO  {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public void salvar(Entidade entidade) {
        Imagem imagem = (Imagem) entidade;

        // Salva a imagem
        String sql = "INSERT INTO `Imagem` (`textoAlt_imagem`, `caminho_imagem`, `produto_imagem`) VALUES (?, ?, ?)";

        jdbcTemplate.update(sql, imagem.getTextoAlt(), imagem.getCaminho(), imagem.getProdutoId());
    }

    public void salvarImagens(List<Imagem> imagens, int produtoId){
        for (Imagem i : imagens) {
            i.setProdutoId(produtoId);
            salvar(i);
        }
    }

    @Override
    public List<Entidade> buscar(Entidade entidade) {
        return null;
    }

    @Override
    public void atualizar(Entidade entidade) {}

    @Override
    public void deletar(Entidade entidade) {}
}
