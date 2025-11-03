package com.app.project.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.app.project.model.Entidade;
import com.app.project.model.Estoque;

@Repository
public class EstoqueDAO implements IDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Mapeia o resultado do banco para objeto Estoque
    private RowMapper<Estoque> estoqueMapper = new RowMapper<>() {
        @Override
        public Estoque mapRow(ResultSet rs, int rowNum) throws SQLException {
            Estoque estoque = new Estoque(rs.getInt("quantidade_estoque"), rs.getInt("produto_estoque"));
            estoque.setId(rs.getString("id_estoque"));

            return estoque;
        }
    };

    @Override
    public void salvar(Entidade entidade) {
        Estoque estoque = (Estoque) entidade;

        // Salva o estoque
        String sql = "INSERT INTO `Estoque` (`produto_estoque`, `quantidade_estoque`) VALUES (?, ?)";

        jdbcTemplate.update(sql, estoque.getProdutoId(), estoque.getQuantidade());

        String descricao = "SALVAR;"+estoque.getClass()+";";
        log(descricao, jdbcTemplate);
    }

    @Override
    public List<Entidade> buscar(Entidade entidade) {
        Estoque estoque = (Estoque) entidade;

        List<Estoque> e;

        // Se tem ID do produto, busca por ID do produto
        if (estoque.getProdutoId() != 0) {
            e = jdbcTemplate.query(
                    "SELECT * FROM `Estoque` WHERE produto_estoque = ?",
                    estoqueMapper,
                    estoque.getProdutoId());
        } else {
            // Caso contrário, retorna todos
            e = jdbcTemplate.query(
                    "SELECT * FROM `Estoque`",
                    estoqueMapper);
        }

        return (List<Entidade>) (List<?>) e;
    }

    @Override
    public void atualizar(Entidade entidade) {
        Estoque estoque = (Estoque) entidade;

        String sql = "UPDATE `Estoque` SET `quantidade_estoque` = `quantidade_estoque` + ? WHERE id_estoque = ?";
        jdbcTemplate.update(sql, estoque.getQuantidade(), estoque.getId());

        String descricao = "ATUALIZAR;"+estoque.getClass()+";ID:"+estoque.getId();
        log(descricao, jdbcTemplate);
    }

    @Override
    public void deletar(Entidade entidade) {
    }
}
