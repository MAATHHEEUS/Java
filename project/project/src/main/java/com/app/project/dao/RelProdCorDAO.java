package com.app.project.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.app.project.model.Cor;

@Repository
public class RelProdCorDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void salvarCores(List<Cor> cores, int produtoId) {
        for (Cor c : cores) {
            // Salva a relação da produto com a cor
            String sql = "INSERT INTO `RelProdCor` (`produto_prodcor`, `cor_prodcor`) VALUES (?, ?)";

            jdbcTemplate.update(sql, produtoId, c.getNome());
        }
    }

}
