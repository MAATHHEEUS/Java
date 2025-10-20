package com.app.project.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.app.project.model.Material;

@Repository
public class RelProdMaterialDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void salvarMateriais(List<Material> materiais, int produtoId) {
        for (Material m : materiais) {
            // Salva a relação da produto com o material
            String sql = "INSERT INTO `RelProdMaterial` (`produto_prodmaterial`, `material_prodmaterial`) VALUES (?, ?)";

            jdbcTemplate.update(sql, produtoId, m.getNome());
        }
    }

}
