package com.app.project.dao;

import com.app.project.model.Bandeira;
import com.app.project.model.Cartao;
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
public class CartaoDAO implements IDAO {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Mapeia o resultado do banco para objeto Cartao
    private RowMapper<Cartao> cartaoMapper = new RowMapper<>() {
        @Override
        public Cartao mapRow(ResultSet rs, int rowNum) throws SQLException {
            Status status = new Status(rs.getString("nome_status"), null);
            Bandeira bandeira = new Bandeira(rs.getString("bandeira_cartao"), null);
            Cartao cartao = new Cartao(rs.getString("numero_cartao"), rs.getString("nome_cartao"), rs.getString("tipo_cartao"), rs.getString("cvv_cartao"), bandeira, status, rs.getInt("cliente_cartao"));
            cartao.setId(rs.getString("id_cartao"));

            return cartao;
        }
    };

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
        Cartao cartao = (Cartao) entidade;

        List<Cartao> cartoes;

        // Se o cartao tem ID, busca por ID
        if (cartao.getId() != null && !cartao.getId().equals("0") && !cartao.getId().isEmpty()) {
            cartoes = jdbcTemplate.query(
                    "SELECT c.id_cartao, c.numero_cartao, c.nome_cartao, c.tipo_cartao, c.cvv_cartao, c.cliente_cartao, c.bandeira_cartao, s.nome_status FROM `Cartao` c JOIN `Status` s ON s.id_status = c.status_cartao WHERE c.id_cartao = ?",
                    cartaoMapper,
                    cartao.getId());
        } else {
            // Caso contrário, retorna todos do cliente
            cartoes = jdbcTemplate.query(
                "SELECT c.id_cartao, c.numero_cartao, c.nome_cartao, c.tipo_cartao, c.cvv_cartao, c.cliente_cartao, c.bandeira_cartao, s.nome_status FROM `Cartao` c JOIN `Status` s ON s.id_status = c.status_cartao WHERE c.cliente_cartao = ?",
                cartaoMapper, cartao.getClienteId());
        }

        return (List<Entidade>) (List<?>) cartoes;
    }

    @Override
    public void atualizar(Entidade entidade) {
        Cartao cartao = (Cartao) entidade;

        String sql = "UPDATE `Cartao` SET `numero_cartao` = ?, `nome_cartao` = ?, `tipo_cartao` = ?, `cvv_cartao` = ?, `bandeira_cartao` = ? WHERE id_cartao = ?";
        jdbcTemplate.update(sql, cartao.getNumero(), cartao.getNome(), cartao.getTipo(), cartao.getCVV(), cartao.getBandeira().getNome(), cartao.getId());
    }

    @Override
    public void deletar(Entidade entidade) {
        Cartao cartao = (Cartao) entidade;

        // Busca o id do status
        String selectSql = "SELECT status_cartao FROM `Cartao` WHERE id_cartao = ?";
        List<Integer> ids = jdbcTemplate.query(selectSql, new Object[] { cartao.getId() },
                (rs, rowNum) -> rs.getInt("status_cartao"));

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