package com.app.project.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.app.project.model.Cor;

@Repository
public class RelProdCorDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Mapeia o resultado do banco 
    private RowMapper<Integer> Mapper = new RowMapper<>() {
        @Override
        public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {

            return rs.getInt("cor_prodcor");
        }
    };

    // Método usado para salvar uma única cor no produto, pela classe ProdCorController
    public void salvar(String cor, int produtoId) {
        String sql = "INSERT INTO `RelProdCor` (`produto_prodcor`, `cor_prodcor`) VALUES (?, ?)";

        jdbcTemplate.update(sql, produtoId, cor);
    }

    // Método usado quando salva o produto inteiro, pela classe ProdutoFacade
    public void salvarCores(List<Cor> cores, int produtoId) {
        for (Cor c : cores) {
            // Salva a relação da produto com a cor
            String sql = "INSERT INTO `RelProdCor` (`produto_prodcor`, `cor_prodcor`) VALUES (?, ?)";

            jdbcTemplate.update(sql, produtoId, c.getNome());
        }
    }

    public List<Integer> buscarCores(int produtoId) {
        List<Integer> cores;

        cores = jdbcTemplate.query(
                "SELECT * FROM `RelProdCor` WHERE produto_prodcor = ?",
                Mapper,
                produtoId);

        return cores;

    }

    public void atualizarCorProduto(int idProdCor, Cor cor) {
        String sql = "UPDATE `RelProdCor` SET `cor_prodcor` = ? WHERE `id_prodcor` = ?";
        jdbcTemplate.update(sql, cor.getNome(), idProdCor);
    }

    public void deletarCorProduto(int idProdCor) {
        String sql = "DELETE FROM `RelProdCor` WHERE id_prodcor = ?";
        jdbcTemplate.update(sql, idProdCor);
    }

}
