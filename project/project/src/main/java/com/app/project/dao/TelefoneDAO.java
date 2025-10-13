package com.app.project.dao;

import com.app.project.model.Telefone;
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
public class TelefoneDAO implements IDAO {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Mapeia o resultado do banco para objeto Cartao
    private RowMapper<Telefone> telefoneMapper = new RowMapper<>() {
        @Override
        public Telefone mapRow(ResultSet rs, int rowNum) throws SQLException {
            Status status = new Status(rs.getString("nome_status"), null);
            Telefone telefone = new Telefone(rs.getString("numero_telefone"), rs.getString("ddd_telefone"), rs.getString("tipo_telefone"), status, Integer.parseInt(rs.getString("cliente_telefone")));
            telefone.setId(rs.getString("id_telefone"));

            return telefone;
        }
    };

    @Override
    public void salvar(Entidade entidade) {
        Telefone telefone = (Telefone) entidade;

        // Salva um status para o cartão
        String sqlStatus = "INSERT INTO `Status`(`nome_status`) VALUES (?)";

        // Usa KeyHolder para capturar o ID gerado
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sqlStatus, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, telefone.getStatus().getNome());
            return ps;
        }, keyHolder);

        // Armazena o ID gerado no objeto
        String idStatus = String.valueOf(keyHolder.getKey().intValue());

        // Salva o cartao
        String sql = "INSERT INTO `Telefone` (`numero_telefone`, `DDD_telefone`, `tipo_telefone`, `cliente_telefone`, `status_telefone`) VALUES (?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql, telefone.getNumero(), telefone.getDDD(), telefone.getTipo(), telefone.getClienteId(), idStatus);
    }

    @Override
    public List<Entidade> buscar(Entidade entidade) {
        Telefone telefone = (Telefone) entidade;

        List<Telefone> telefones;

        // Se o cartao tem ID, busca por ID
        if (telefone.getId() != null && !telefone.getId().equals("0") && !telefone.getId().isEmpty()) {
            telefones = jdbcTemplate.query(
                    "SELECT t.id_telefone, t.numero_telefone, t.DDD_telefone, t.tipo_telefone, t.cliente_telefone, s.nome_status FROM `Telefone` t JOIN `Status` s ON s.id_status = t.status_telefone WHERE t.id_telefone = ?",
                    telefoneMapper,
                    telefone.getId());
        } else {
            // Caso contrário, retorna todos do cliente
            telefones = jdbcTemplate.query(
                "SELECT t.id_telefone, t.numero_telefone, t.DDD_telefone, t.tipo_telefone, t.cliente_telefone, s.nome_status FROM `Telefone` t JOIN `Status` s ON s.id_status = t.status_telefone WHERE t.cliente_telefone = ?",
                telefoneMapper, telefone.getClienteId());
        }

        return (List<Entidade>) (List<?>) telefones;
    }

    @Override
    public void atualizar(Entidade entidade) {
        Telefone tel = (Telefone) entidade;

        String sql = "UPDATE `Telefone` SET `numero_telefone` = ?, `DDD_telefone` = ?, `tipo_telefone` = ? WHERE id_telefone = ?";
        jdbcTemplate.update(sql, tel.getNumero(), tel.getDDD(), tel.getTipo(), tel.getId());
    }

    @Override
    public void deletar(Entidade entidade) {
        Telefone tel = (Telefone) entidade;

        // Busca o id do status
        String selectSql = "SELECT status_telefone FROM `Telefone` WHERE id_telefone = ?";
        List<Integer> ids = jdbcTemplate.query(selectSql, new Object[] { tel.getId() },
                (rs, rowNum) -> rs.getInt("status_telefone"));

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