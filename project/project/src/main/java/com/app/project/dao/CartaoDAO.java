package com.app.project.dao;

import com.app.project.model.Cartao;
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
public class CartaoDAO implements IDAO {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void salvar(Entidade entidade) {
        Cartao cartao = (Cartao) entidade;

        // Salva um status para o cartão
        String sqlStatus = "INSERT INTO `Status`(`nome_status`) VALUES (?)";

        // Usa KeyHolder para capturar o ID gerado
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sqlStatus, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, cartao.getStatus().getNome());
            return ps;
        }, keyHolder);

        // Armazena o ID gerado no objeto
        String idStatus = String.valueOf(keyHolder.getKey().intValue());

        // Salva o cartao
        String sql = "INSERT INTO `Cartao` (`numero_cartao`, `nome_cartao`, `tipo_cartao`, `cvv_cartao`, `cliente_cartao`, `bandeira_cartao`, `status_cartao`) VALUES (?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql, cartao.getNumero(), cartao.getNome(), cartao.getTipo(), cartao.getCVV(), cartao.getClienteId(), cartao.getBandeira().getNome(), idStatus);
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