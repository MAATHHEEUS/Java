package com.app.project.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.app.project.model.Promocao;

@Repository
public class RelProdPromocaoDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Mapeia o resultado do banco
    private RowMapper<Integer> Mapper = new RowMapper<>() {
        @Override
        public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {

            return rs.getInt("promo_prodpromocao");
        }
    };

    // Método usado para salvar uma única promoção no produto, pela classe ProdPromocaoController
    public void salvar(Promocao promo, int produtoId) {
        String sql = "INSERT INTO `RelProdPromocao` (`produto_prodpromocao`, `promo_prodpromocao`) VALUES (?, ?)";

        jdbcTemplate.update(sql, produtoId, promo.getNome());
    }

    // Método usado quando salva o produto inteiro, pela classe ProdutoFacade
    public void salvarPromocoes(List<Promocao> promocoes, int produtoId) {
        for (Promocao p : promocoes) {
            // Salva a relação da produto com a promoção
            String sql = "INSERT INTO `RelProdPromocao` (`produto_prodpromocao`, `promo_prodpromocao`) VALUES (?, ?)";

            jdbcTemplate.update(sql, produtoId, p.getNome());
        }
    }

    public List<Integer> buscarPromocoes(int produtoId) {
        List<Integer> promos;

        promos = jdbcTemplate.query(
                "SELECT * FROM `RelProdPromocao` WHERE produto_prodpromocao = ?",
                Mapper,
                produtoId);

        return promos;

    }

    public void atualizarPromoProduto(int idProdPromo, Promocao promo) {
        String sql = "UPDATE `RelProdPromocao` SET `promo_prodpromocao` = ? WHERE `id_prodpromocao` = ?";
        jdbcTemplate.update(sql, promo.getNome(), idProdPromo);
    }

    public void deletarPromoProduto(int idProdPromo) {
        String sql = "DELETE FROM `RelProdPromocao` WHERE id_prodpromocao = ?";
        jdbcTemplate.update(sql, idProdPromo);
    }

}
