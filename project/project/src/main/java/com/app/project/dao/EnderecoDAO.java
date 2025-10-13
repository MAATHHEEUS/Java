package com.app.project.dao;

import com.app.project.model.Cidade;
import com.app.project.model.Endereco;
import com.app.project.model.Entidade;
import com.app.project.model.Estado;
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
public class EnderecoDAO implements IDAO {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Mapeia o resultado do banco para objeto Endereco
    private RowMapper<Endereco> enderecoMapper = new RowMapper<>() {
        @Override
        public Endereco mapRow(ResultSet rs, int rowNum) throws SQLException {
            Status status = new Status(rs.getString("nome_status"), null);
            Cidade cidade = new Cidade(rs.getString("nome_cidade"), null, new Estado(null, rs.getString("estado_cidade"), null, null));
            Endereco endereco = new Endereco(rs.getString("logradouro_endereco"), rs.getString("nome_endereco"), rs.getString("numero_endereco"), rs.getString("bairro_endereco"), rs.getString("cep_endereco"), cidade, rs.getString("tipo_endereco"), status, rs.getInt("cliente_endereco"));
            endereco.setId(rs.getString("id_endereco"));

            return endereco;
        }
    };

    @Override
    public void salvar(Entidade entidade) {
        Endereco endereco = (Endereco) entidade;

        // Armazena o ID gerado no objeto cidade
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

    // Método usado para salvar mais de um endereço no cliente
    public void salvarEnderecos(List<Endereco> enderecos, String idCliente) {
        for (Endereco e : enderecos) {
            e.setClienteId(Integer.parseInt(idCliente));
            salvar(e);
        }
    }

    @Override
    public List<Entidade> buscar(Entidade entidade) {
        Endereco endereco = (Endereco) entidade;

        List<Endereco> enderecos;

        // Se o endereco tem ID, busca por ID
        if (endereco.getId() != null && !endereco.getId().equals("0") && !endereco.getId().isEmpty()) {
            enderecos = jdbcTemplate.query(
                    "SELECT e.id_endereco, e.logradouro_endereco, e.nome_endereco, e.numero_endereco, e.cep_endereco, e.bairro_endereco, e.tipo_endereco, c.nome_cidade, c.estado_cidade, s.nome_status, e.cliente_endereco FROM `Endereco` e JOIN `Status` s ON e.status_endereco = s.id_status JOIN `Cidade` c ON c.id_cidade = e.cidade_endereco WHERE e.id_endereco = ?",
                    enderecoMapper,
                    endereco.getId());
        } else {
            // Caso contrário, retorna todos do cliente
            enderecos = jdbcTemplate.query(
                "SELECT e.id_endereco, e.logradouro_endereco, e.nome_endereco, e.numero_endereco, e.cep_endereco, e.bairro_endereco, e.tipo_endereco, c.nome_cidade, c.estado_cidade, s.nome_status, e.cliente_endereco FROM `Endereco` e JOIN `Status` s ON e.status_endereco = s.id_status JOIN `Cidade` c ON c.id_cidade = e.cidade_endereco WHERE e.cliente_endereco = ?",
                enderecoMapper, endereco.getClienteId());
        }

        return (List<Entidade>) (List<?>) enderecos;
    }

    @Override
    public void atualizar(Entidade entidade) {
        Endereco endereco = (Endereco) entidade;

        // Armazena o ID gerado no objeto cidade
        String idCidade = String.valueOf(salvarOuBuscarCidade(endereco.getCidade().getNome(),  endereco.getCidade().getEstado().getNome()));

        String sql = "UPDATE `Endereco` SET `logradouro_endereco` = ?, `nome_endereco` = ?, `numero_endereco` = ?, `cep_endereco` = ?, `bairro_endereco` = ?, `tipo_endereco` = ?, `cidade_endereco` = ? WHERE id_endereco = ?";
        jdbcTemplate.update(sql, endereco.getLogradouro(), endereco.getNome(), endereco.getNumero(), endereco.getCep(), endereco.getBairro(), endereco.getTipo(), idCidade, endereco.getId());
    }

    @Override
    public void deletar(Entidade entidade) {
        Endereco endereco = (Endereco) entidade;

        // Busca o id do status
        String selectSql = "SELECT status_endereco FROM `Endereco` WHERE id_endereco = ?";
        List<Integer> ids = jdbcTemplate.query(selectSql, new Object[] { endereco.getId() },
                (rs, rowNum) -> rs.getInt("status_endereco"));

        int idStatus = 0;
        if (!ids.isEmpty()) {
            idStatus = ids.get(0);
        }

        String motivo = "Inativação adm";
        String nome = "Inativo";

        String sql = "UPDATE `Status` SET `nome_status` = ?, `motivo_status` = ? WHERE id_status = ?";
        jdbcTemplate.update(sql, nome, motivo, idStatus);
    }

    // Se não houver a cidade no estado salva uma nova
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