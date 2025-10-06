package com.app.project.dao;

import com.app.project.model.Cliente;
import com.app.project.model.Entidade;
import com.app.project.model.Genero;
import com.app.project.model.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ClienteDAO implements IDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Mapeia o resultado do banco para objeto Cliente
    private RowMapper<Cliente> clienteMapper = new RowMapper<>() {
        @Override
        public Cliente mapRow(ResultSet rs, int rowNum) throws SQLException {
            Genero genero = new Genero(rs.getString("genero_cliente"), null);
            Status status = new Status(rs.getString("status_cliente"), null);
            Cliente cliente = new Cliente(rs.getString("id_cliente"), null,
                    rs.getString("nome_cliente"), rs.getString("cpf_cliente"), rs.getString("nascimento_cliente"),
                    rs.getString("email_cliente"), genero, status, null, null, null, null, null);

            return cliente;
        }
    };

    @Override
    public void salvar(Entidade entidade) {
        Cliente cliente = (Cliente) entidade;

        // Usa KeyHolder para capturar o ID gerado
        KeyHolder keyHolder = new GeneratedKeyHolder();

        String sqlCliente = "INSERT INTO `Cliente`(`nome_cliente`, `cpf_cliente`, `nascimento_cliente`, `email_cliente`, `genero_cliente`, `senha_cliente`, `status_cliente`) "
                +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sqlCliente, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getCpf());
            ps.setString(3, cliente.getNascimento());
            ps.setString(4, cliente.getEmail());
            ps.setString(5, cliente.getGenero().getNome());
            ps.setString(6, cliente.getSenha());
            ps.setString(7, cliente.getStatus().getId());
            return ps;
        }, keyHolder);

        // Armazena o ID gerado no objeto
        cliente.setId(String.valueOf(keyHolder.getKey().intValue()));
    }

    @Override
    public List<Entidade> buscar(Entidade entidade) {
        Cliente cliente = (Cliente) entidade;

        List<Cliente> clientes;

        // Se o cliente tem ID, busca por ID
        if (cliente.getId() != null && !cliente.getId().equals("0") && !cliente.getId().isEmpty()) {
            clientes = jdbcTemplate.query(
                    "SELECT c.id_cliente, c.nome_cliente, c.cpf_cliente, c.nascimento_cliente, c.email_cliente, c.genero_cliente, s.nome_status as status_cliente FROM `Cliente` c JOIN `Status` s ON c.status_cliente = s.id_status WHERE id_cliente = ?",
                    clienteMapper,
                    cliente.getId());
        } else {
            // Caso contrário, retorna todos
            clientes = jdbcTemplate.query("SELECT c.id_cliente, c.nome_cliente, c.cpf_cliente, c.nascimento_cliente, c.email_cliente, c.genero_cliente, s.nome_status as status_cliente FROM `Cliente` c JOIN `Status` s ON c.status_cliente = s.id_status", clienteMapper);
        }

        return (List<Entidade>) (List<?>) clientes;
    }

    @Override
    public void atualizar(Entidade entidade) {
        Cliente cliente = (Cliente) entidade;
        String sql = "UPDATE `Cliente` SET `nome_cliente` = ?, `cpf_cliente` = ?, `nascimento_cliente` = ?, `email_cliente` = ?, `genero_cliente` = ? WHERE id_cliente = ?";
        jdbcTemplate.update(sql, cliente.getNome(), cliente.getCpf(), cliente.getNascimento(), cliente.getEmail(),
                cliente.getGenero().getNome(), cliente.getId());

        // Atualiza a senha se estiver diferente de vazia
        if (cliente.getSenha() != "") {
            atualizarSenha(cliente);
        }
    }

    @Override
    public void deletar(Entidade entidade) {
        Cliente cliente = (Cliente) entidade;

        // Busca o id do status
        String selectSql = "SELECT status_cliente FROM `Cliente` WHERE id_cliente = ?";
        List<Integer> ids = jdbcTemplate.query(selectSql, new Object[] { cliente.getId() },
                (rs, rowNum) -> rs.getInt("status_cliente"));

        int idStatus = 0;
        if (!ids.isEmpty()) {
            idStatus = ids.get(0);
        }

        String motivo = "Inativação adm";
        String nome = "Inativo";

        String sql = "UPDATE `Status` SET `nome_status` = ?, `motivo_status` = ? WHERE id_status = ?";
        jdbcTemplate.update(sql, nome, motivo, idStatus);
    }

    public void atualizarSenha(Cliente cliente) {
        String sql = "UPDATE `Cliente` SET `senha_cliente` = ? WHERE id_cliente = ?";
        jdbcTemplate.update(sql, cliente.getSenha(), cliente.getId());
    }
}
