package com.app.project.dao;

import com.app.project.model.Cupom;
import com.app.project.model.Entidade;
import com.app.project.model.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class CupomDAO implements IDAO {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Mapeia o resultado do banco para objeto Cupom
    private RowMapper<Cupom> cupomMapper = new RowMapper<>() {
        @Override
        public Cupom mapRow(ResultSet rs, int rowNum) throws SQLException {
            Status status = new Status(rs.getString("nome_status"), null);
            Cupom cupom = new Cupom(rs.getString("codigo_cupom"), rs.getString("validade_cupom"), rs.getString("valor_cupom"), status, Integer.parseInt(rs.getString("cliente_cupom")));
            cupom.setId(rs.getString("id_cupom"));

            return cupom;
        }
    };

    @Override
    public void salvar(Entidade entidade) {
        Cupom cupom = (Cupom) entidade;

        // Salva um status para o cupom
        String sqlStatus = "INSERT INTO `Status`(`nome_status`) VALUES (?)";

        // Usa KeyHolder para capturar o ID gerado
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sqlStatus, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, cupom.getStatus().getNome());
            return ps;
        }, keyHolder);

        // Armazena o ID gerado no objeto
        String idStatus = String.valueOf(keyHolder.getKey().intValue());

        // Salva o cupom
        String sql = "INSERT INTO `Cupom` (`codigo_cupom`, `validade_cupom`, `valor_cupom`, `cliente_cupom`, `status_cupom`) VALUES (?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql, cupom.getCodigo(), cupom.getValidade(), cupom.getValor(), cupom.getClienteId(), idStatus);
    }

    @Override
    public List<Entidade> buscar(Entidade entidade) {
        Cupom cupom = (Cupom) entidade;

        List<Cupom> cupons;

        // Se o cupom tem ID, busca por ID
        if (cupom.getId() != null && !cupom.getId().equals("0") && !cupom.getId().isEmpty()) {
            cupons = jdbcTemplate.query(
                    "SELECT id_cupom, c.codigo_cupom, c.validade_cupom, `valor_cupom`, `cliente_cupom`, s.nome_status FROM `Cupom` c JOIN `Status` s ON s.id_status = c.status_cupom WHERE c.id_cupom = ?",
                    cupomMapper,
                    cupom.getId());
        } else {
            // Caso contrário, retorna todos do cliente
            cupons = jdbcTemplate.query(
                "SELECT id_cupom, c.codigo_cupom, c.validade_cupom, `valor_cupom`, `cliente_cupom`, s.nome_status FROM `Cupom` c JOIN `Status` s ON s.id_status = c.status_cupom WHERE c.cliente_cupom = ?",
                cupomMapper, cupom.getClienteId());
        }

        return (List<Entidade>) (List<?>) cupons;
    }

    @Override
    public void atualizar(Entidade entidade) {
        Cupom cupom = (Cupom) entidade;

        String sql = "UPDATE `Cupom` SET `codigo_cupom` = ?, `validade_cupom` = ?, `valor_cupom` = ? WHERE id_cupom = ?";
        jdbcTemplate.update(sql, cupom.getCodigo(), cupom.getValidade(), cupom.getValor(), cupom.getId());
    }

    @Override
    public void deletar(Entidade entidade) {
        Cupom cupom = (Cupom) entidade;

        // Busca o id do status
        String selectSql = "SELECT status_cupom FROM `Cupom` WHERE id_cupom = ?";
        List<Integer> ids = jdbcTemplate.query(selectSql, new Object[] { cupom.getId() },
                (rs, rowNum) -> rs.getInt("status_cupom"));

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