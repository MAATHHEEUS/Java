package com.app.project.dao;

import com.app.project.model.Endereco;
import com.app.project.model.Entidade;
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
public class EnderecoDAO implements IDAO {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void salvar(Entidade entidade) {
        Endereco endereco = (Endereco) entidade;

        // Armazena o ID gerado no objeto
        String idCidade = String.valueOf(salvarOuBuscarCidade(endereco.getCidade().getNome(),  endereco.getCidade().getEstado().getNome()));

        // Salva um status para o endereço
        String sqlStatus = "INSERT INTO `Status`(`nome_status`) VALUES (?)";

        // Usa KeyHolder para capturar o ID gerado
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sqlStatus, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, endereco.getStatus().getNome());
            return ps;
        }, keyHolder);

        // Armazena o ID gerado no objeto
        String idStatus = String.valueOf(keyHolder.getKey().intValue());

        // Salva o endereço
        String sql = "INSERT INTO `Endereco` (`logradouro_endereco`, `nome_endereco`, `numero_endereco`, `cep_endereco`, `bairro_endereco`, `tipo_endereco`, `cidade_endereco`, `cliente_endereco`, `status_endereco`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql, endereco.getLogradouro(), endereco.getNome(), endereco.getNumero(), endereco.getCep(), endereco.getBairro(), endereco.getTipo(), idCidade, endereco.getClienteId(), idStatus);
    }

    public void salvarEnderecos(List<Endereco> enderecos, String idCliente) {
        for (Endereco e : enderecos) {
            e.setClienteId(Integer.parseInt(idCliente));
            salvar(e);
        }
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

    private int salvarOuBuscarCidade(String nome, String estado) {
        // Verifica se já existe
        String selectSql = "SELECT id_cidade FROM `Cidade` WHERE nome_cidade = ? AND estado_cidade = ?";
        List<Integer> ids = jdbcTemplate.query(selectSql, new Object[]{nome, estado}, (rs, rowNum) -> rs.getInt("id_cidade"));

        if (!ids.isEmpty()) {
            // Se já existir, retorna o id existente
            return ids.get(0);
        }

        /* Se não existir, insere e retorna o novo id */
        String sqlCidade = "INSERT INTO `Cidade`(`nome_cidade`, `estado_cidade`) VALUES (?, ?)";

        // Usa KeyHolder para capturar o ID gerado
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sqlCidade, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, nome);
            ps.setString(2, estado);
            return ps;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }
}