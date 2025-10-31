package com.app.project.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.app.project.model.Entidade;
import com.app.project.model.ProdutoVenda;
import com.app.project.model.Status;

@Repository
public class ProdutoVendaDAO implements IDAO {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    // Mapeia o resultado do banco para objeto ProdutoVenda
    private RowMapper<ProdutoVenda> pvMapper = new RowMapper<>() {
        @Override
        public ProdutoVenda mapRow(ResultSet rs, int rowNum) throws SQLException {
            Status status = new Status(rs.getString("nome_status"), null);
            ProdutoVenda pv = new ProdutoVenda(rs.getInt("quantidade_prodvenda"), rs.getInt("produto_prodvenda"), rs.getInt("venda_prodvenda"), status);

            return pv;
        }
    };

    @Override
    public void salvar(Entidade entidade) {
        ProdutoVenda pv = (ProdutoVenda) entidade;

        // Salva um status para o produto venda
        String sqlStatus = "INSERT INTO `Status`(`nome_status`) VALUES (?)";

        // Usa KeyHolder para capturar o ID gerado
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sqlStatus, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, pv.getStatus().getNome());
            return ps;
        }, keyHolder);

        // Armazena o ID gerado no objeto
        String idStatus = String.valueOf(keyHolder.getKey().intValue());

        // Salva o produto venda
        String sql = "INSERT INTO `ProdutoVenda` (`quantidade_prodvenda`, `produto_prodvenda`, `venda_prodvenda`, `status_prodvenda`) VALUES (?, ?, ?, ?)";

        jdbcTemplate.update(sql, pv.getQuantidade(), pv.getProdutoId(), pv.getVendaId(), idStatus);
    }

    @Override
    public List<Entidade> buscar(Entidade entidade) {
        ProdutoVenda pv = (ProdutoVenda) entidade;

        List<ProdutoVenda> pvs;

        // Se o Produto venda tem ID, busca por ID
        if (pv.getId() != null && !pv.getId().equals("0") && !pv.getId().isEmpty()) {
            pvs = jdbcTemplate.query(
                    "",
                    pvMapper,
                    pv.getId());
        } else {
            // Caso contrário, retorna todos da venda
            pvs = jdbcTemplate.query(
                "",
                pvMapper, pv.getVendaId());
        }

        return (List<Entidade>) (List<?>) pvs;
    }

    @Override
    public void atualizar(Entidade entidade) {
        ProdutoVenda pv = (ProdutoVenda) entidade;

        String sql = "UPDATE `ProdutoVenda` SET WHERE id_pagamento = ?";
        jdbcTemplate.update(sql, pv.getId());
    }

    @Override
    public void deletar(Entidade entidade) {
        ProdutoVenda pv = (ProdutoVenda) entidade;

        // Busca o id do status
        String selectSql = "SELECT status_prodvenda FROM `ProdutoVenda` WHERE id_prodvenda = ?";
        List<Integer> ids = jdbcTemplate.query(selectSql, new Object[] { pv.getId() },
                (rs, rowNum) -> rs.getInt("status_prodvenda"));

        int idStatus = 0;
        if (!ids.isEmpty()) {
            idStatus = ids.get(0);
        }

        String motivo = "Inativação adm";
        String nome = "Inativo";

        String sql = "UPDATE `Status` SET `nome_status` = ?, `motivo_status` = ? WHERE id_status = ?";
        jdbcTemplate.update(sql, nome, motivo, idStatus);
    }
}
