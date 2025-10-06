package com.app.project.dao;

import com.app.project.model.Status;
import com.app.project.model.Entidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class StatusDAO implements IDAO {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void salvar(Entidade entidade) {
        Status status = (Status) entidade;

        String sqlStatus = "INSERT INTO `Status`(`nome_status`) VALUES (?)";

        // Usa KeyHolder para capturar o ID gerado
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sqlStatus, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, status.getNome());
            return ps;
        }, keyHolder);

        // Armazena o ID gerado no objeto
        status.setId(String.valueOf(keyHolder.getKey().intValue()));
    }

    @Override
    public List<Entidade> buscar(Entidade entidade) {
        return null;
    }

    @Override
    public void atualizar(Entidade entidade) {
    }

    @Override
    public void deletar(Entidade entidade) {
    }
}