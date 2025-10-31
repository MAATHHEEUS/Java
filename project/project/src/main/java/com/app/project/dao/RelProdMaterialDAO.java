package com.app.project.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.app.project.model.Material;

@Repository
public class RelProdMaterialDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Mapeia o resultado do banco
    private RowMapper<Integer> Mapper = new RowMapper<>() {
        @Override
        public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {

            return rs.getInt("material_prodmaterial");
        }
    };

    // Método usado para salvar um único material no produto, pela classe ProdMaterialController
    public void salvar(Material material, int produtoId) {
        String sql = "INSERT INTO `RelProdMaterial` (`produto_prodmaterial`, `material_prodmaterial`) VALUES (?, ?)";

        jdbcTemplate.update(sql, produtoId, material.getNome());
    }

    // Método usado quando salva o produto inteiro, pela classe ProdutoFacade
    public void salvarMateriais(List<Material> materiais, int produtoId) {
        for (Material m : materiais) {
            // Salva a relação da produto com o material
            String sql = "INSERT INTO `RelProdMaterial` (`produto_prodmaterial`, `material_prodmaterial`) VALUES (?, ?)";

            jdbcTemplate.update(sql, produtoId, m.getNome());
        }
    }

    public List<Integer> buscarMateriais(int produtoId) {
        List<Integer> materiais;

        materiais = jdbcTemplate.query(
                "SELECT * FROM `RelProdMaterial` WHERE produto_prodmaterial = ?",
                Mapper,
                produtoId);

        return materiais;

    }

    public void atualizarMaterialProduto(int idProdMaterial, Material material) {
        String sql = "UPDATE `RelProdMaterial` SET `material_prodmaterial` = ? WHERE `id_prodmaterial` = ?";
        jdbcTemplate.update(sql, material.getNome(), idProdMaterial);
    }

    public void deletarMaterialProduto(int idProdMaterial) {
        String sql = "DELETE FROM `RelProdMaterial` WHERE id_prodmaterial = ?";
        jdbcTemplate.update(sql, idProdMaterial);
    }
}
