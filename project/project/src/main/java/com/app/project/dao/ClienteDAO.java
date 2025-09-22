// dao/ClienteDAO.java
package com.app.project.dao;

import com.app.project.model.Cliente;
import com.app.project.model.Entidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

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
            Cliente cliente = new Cliente(rs.getString("id_cliente"), rs.getString("dtCad_cliente"), rs.getString("nome_cliente"), rs.getString("cpf_cliente"), rs.getString("nascimento_cliente"), rs.getString("email_cliente"), null, null, rs.getString("senha_cliente"), null, null, null, null);
            // caso tenha endereços, pode buscar em outra tabela
            return cliente;
        }
    };

    @Override
    public void salvar(Entidade entidade) {
        Cliente cliente = (Cliente) entidade;
        String sql = "INSERT INTO cliente (id, nome, email) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, cliente.getId(), cliente.getNome(), cliente.getEmail());
    }

    @Override
    public List<Entidade> buscar(Entidade entidade) {
        String sql = "SELECT * FROM cliente";
        return (List) jdbcTemplate.query(sql, clienteMapper);
    }

    @Override
    public void atualizar(Entidade entidade) {
        Cliente cliente = (Cliente) entidade;
        String sql = "UPDATE clientes SET nome = ?, email = ? WHERE id = ?";
        jdbcTemplate.update(sql, cliente.getNome(), cliente.getEmail(), cliente.getId());
    }

    @Override
    public void deletar(Entidade entidade) {
        Cliente cliente = (Cliente) entidade;
        String sql = "DELETE FROM clientes WHERE id = ?";
        jdbcTemplate.update(sql, cliente.getId());
    }

    public void deletarEndereco(Entidade entidade) {
        Cliente cliente = (Cliente) entidade;
        String sql = "UPDATE clientes SET endereco = NULL WHERE id = ?";
        jdbcTemplate.update(sql, cliente.getId());
    }
}
