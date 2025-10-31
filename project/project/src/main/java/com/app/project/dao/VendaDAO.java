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
import com.app.project.model.Status;
import com.app.project.model.Venda;

@Repository
public class VendaDAO implements IDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Mapeia o resultado do banco para objeto Venda
    private RowMapper<Venda> vendaMapper = new RowMapper<>() {
        @Override
        public Venda mapRow(ResultSet rs, int rowNum) throws SQLException {
            Status status = new Status(rs.getString("nome_status"), null);
            Venda venda = new Venda(rs.getInt("cliente_venda"), rs.getString("valor_venda"), status, rs.getInt("entrega_venda"),  rs.getInt("faturamento_venda"), null, null, rs.getString("dtEntrega_venda"), rs.getString("frete_venda"), rs.getString("desconto_venda"));

            return venda;
        }
    };

    @Override
    public void salvar(Entidade entidade) {
        Venda venda = (Venda) entidade;

        // Salva um status para a venda
        String sqlStatus = "INSERT INTO `Status`(`nome_status`) VALUES (?)";

        // Usa KeyHolder para capturar o ID gerado
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sqlStatus, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, venda.getStatus().getNome());
            return ps;
        }, keyHolder);

        // Armazena o ID gerado no objeto
        String idStatus = String.valueOf(keyHolder.getKey().intValue());

        // Salva a venda
        String sql = "INSERT INTO `Venda` (`cliente_venda`, `valor_venda`, `entrega_venda`, `faturamento_venda`, `dtEntrega_venda`, `frete_venda`, `desconto_venda`, `status_venda`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        // Usa KeyHolder para capturar o ID gerado
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, venda.getClienteId());
            ps.setString(2, venda.getValor());
            ps.setInt(3, venda.getEnderecoEntrega());
            ps.setInt(4, venda.getEnderecoFaturamento());
            ps.setString(5, venda.getDataEntrega());
            ps.setString(6, venda.getFrete());
            ps.setString(7, venda.getDesconto());
            ps.setString(8, idStatus);
            return ps;
        }, keyHolder);

        // Armazena o ID gerado no objeto
        venda.setId(String.valueOf(keyHolder.getKey().intValue()));
    }

    @Override
    public List<Entidade> buscar(Entidade entidade) {
        Venda venda = (Venda) entidade;

        List<Venda> vendas;

        // Se a venda tem ID, busca por ID
        if (venda.getId() != null && !venda.getId().equals("0") && !venda.getId().isEmpty()) {
            vendas = jdbcTemplate.query(
                    "SELECT `id_venda`, `cliente_venda`, `valor_venda`, `entrega_venda`, `faturamento_venda`, `dtEntrega_venda`, `frete_venda`, `desconto_venda`, `nome_status` FROM `Venda` v JOIN `Status` s ON s.id_status = v.status_venda WHERE `id_venda` = ?",
                    vendaMapper,
                    venda.getId());
        } else {
            // Caso contrário, retorna todas as venda
            vendas = jdbcTemplate.query(
                "SELECT `id_venda`, `cliente_venda`, `valor_venda`, `entrega_venda`, `faturamento_venda`, `dtEntrega_venda`, `frete_venda`, `desconto_venda`, `nome_status` FROM `Venda` v JOIN `Status` s ON s.id_status = v.status_venda",
                vendaMapper);
        }

        return (List<Entidade>) (List<?>) vendas;
    }

    @Override
    public void atualizar(Entidade entidade) {
        Venda venda = (Venda) entidade;

        String sql = "UPDATE `Venda` SET `valor_venda` = ?, `entrega_venda` = ?,`faturamento_venda` = ?, `dtEntrega_venda` = ?, `frete_venda` = ?, `desconto_venda` = ? WHERE id_venda = ?";
        jdbcTemplate.update(sql, venda.getValor(), venda.getEnderecoEntrega(), venda.getEnderecoFaturamento(), venda.getDataEntrega(), venda.getFrete(), venda.getDesconto(),venda.getId());
    }

    @Override
    public void deletar(Entidade entidade) {
        Venda venda = (Venda) entidade;

        // Busca o id do status
        String selectSql = "SELECT status_venda FROM `Venda` WHERE id_venda = ?";
        List<Integer> ids = jdbcTemplate.query(selectSql, new Object[] { venda.getId() },
                (rs, rowNum) -> rs.getInt("status_venda"));

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
