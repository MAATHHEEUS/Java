// dao/ClienteDAO.java
package com.app.project.dao;

import com.app.project.model.Entidade;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

public interface IDAO {
    void salvar(Entidade entidade);
    List<Entidade> buscar(Entidade entidade);
    void atualizar(Entidade entidade);
    void deletar(Entidade entidade);

    default void log(String descricao, JdbcTemplate jdbcTemplate) {
        // Salva o log
        String sql = "INSERT INTO `Log` (`descricao_log`) VALUES (?)";

        jdbcTemplate.update(sql, descricao);
    }
}
