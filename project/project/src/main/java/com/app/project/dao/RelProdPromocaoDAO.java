package com.app.project.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.app.project.model.Promocao;

@Repository
public class RelProdPromocaoDAO {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void salvarPromocoes(List<Promocao> promocoes, int produtoId){
        for (Promocao p : promocoes) {
            // Salva a relação da produto com a promoção
            String sql = "INSERT INTO `RelProdPromocao` (`produto_prodpromocao`, `promo_prodpromocao`) VALUES (?, ?)";

            jdbcTemplate.update(sql, produtoId, p.getNome());
        }
    }

}
