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
import com.app.project.model.Pagamento;
import com.app.project.model.Status;
import com.app.project.model.TipoPagto;

@Repository
public class PagamentoDAO implements IDAO {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    // Mapeia o resultado do banco para objeto Pagamento
    private RowMapper<Pagamento> PagamentoMapper = new RowMapper<>() {
        @Override
        public Pagamento mapRow(ResultSet rs, int rowNum) throws SQLException {
            Status status = new Status(rs.getString("nome_status"), null);
            TipoPagto tipo = new TipoPagto(rs.getString(""), null);
            Pagamento pagamento = new Pagamento(null, null, status, tipo, 0);

            return pagamento;
        }
    };

    @Override
    public void salvar(Entidade entidade) {
        Pagamento pagamento = (Pagamento) entidade;

        // Salva um status para o pagamento
        String sqlStatus = "INSERT INTO `Status`(`nome_status`) VALUES (?)";

        // Usa KeyHolder para capturar o ID gerado
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sqlStatus, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, pagamento.getStatus().getNome());
            return ps;
        }, keyHolder);

        // Armazena o ID gerado no objeto
        String idStatus = String.valueOf(keyHolder.getKey().intValue());

        // Salva o pagamento
        String sql = "INSERT INTO `Pagamento` (`valor_pagamento`, `dados_pagamento`, `venda_pagamento`, `tipo_pagamento`, `status_pagamento`) VALUES (?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql, pagamento.getValor(), pagamento.getDados(), pagamento.getVendaId(), pagamento.getTipo().getNome(), idStatus);

        String descricao = "SALVAR;"+pagamento.getClass()+";";
        log(descricao, jdbcTemplate);
    }

    @Override
    public List<Entidade> buscar(Entidade entidade) {
        Pagamento pagamento = (Pagamento) entidade;

        List<Pagamento> pagamentos;

        // Se o pagamento tem ID, busca por ID
        if (pagamento.getId() != null && !pagamento.getId().equals("0") && !pagamento.getId().isEmpty()) {
            pagamentos = jdbcTemplate.query(
                    "SELECT `id_pagamento`, `valor_pagamento`, `dados_pagamento`, `venda_pagamento`, `tipo_pagamento`, `nome_status` FROM `Pagamento` p JOIN `Status` s ON s.id_status = p.status_pagamento WHERE `id_pagamento` = ?",
                    PagamentoMapper,
                    pagamento.getId());
        } else {
            // Caso contrário, retorna todos da venda
            pagamentos = jdbcTemplate.query(
                "SELECT `id_pagamento`, `valor_pagamento`, `dados_pagamento`, `venda_pagamento`, `tipo_pagamento`, `nome_status` FROM `Pagamento` p JOIN `Status` s ON s.id_status = p.status_pagamento WHERE `venda_pagamento` = ?",
                PagamentoMapper, pagamento.getVendaId());
        }

        return (List<Entidade>) (List<?>) pagamentos;
    }

    @Override
    public void atualizar(Entidade entidade) {
    }

    @Override
    public void deletar(Entidade entidade) {
        Pagamento pagamento = (Pagamento) entidade;

        // Busca o id do status
        String selectSql = "SELECT status_pagamento FROM `Pagamento` WHERE id_pagamento = ?";
        List<Integer> ids = jdbcTemplate.query(selectSql, new Object[] { pagamento.getId() },
                (rs, rowNum) -> rs.getInt("status_pagamento"));

        int idStatus = 0;
        if (!ids.isEmpty()) {
            idStatus = ids.get(0);
        }

        String motivo = "Inativação adm";
        String nome = "Inativo";

        String sql = "UPDATE `Status` SET `nome_status` = ?, `motivo_status` = ? WHERE id_status = ?";
        jdbcTemplate.update(sql, nome, motivo, idStatus);

        String descricao = "INATIVAR;"+pagamento.getClass()+";ID:"+pagamento.getId();
        log(descricao, jdbcTemplate);
    }
}
