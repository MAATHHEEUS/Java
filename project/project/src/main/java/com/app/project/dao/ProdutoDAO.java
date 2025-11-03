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

import com.app.project.model.Categoria;
import com.app.project.model.Desconto;
import com.app.project.model.Embalagem;
import com.app.project.model.Entidade;
import com.app.project.model.Genero;
import com.app.project.model.Linha;
import com.app.project.model.Marca;
import com.app.project.model.Produto;
import com.app.project.model.Status;

@Repository
public class ProdutoDAO implements IDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Mapeia o resultado do banco para objeto Produto
    private RowMapper<Produto> produtoMapper = new RowMapper<>() {
        @Override
        public Produto mapRow(ResultSet rs, int rowNum) throws SQLException {
            Status status = new Status(rs.getString("nome_status"), null);
            Genero genero = new Genero(rs.getString("genero_produto"), null);
            Categoria categoria = new Categoria(rs.getString("categoria_produto"), null, null);
            Marca marca = new Marca(rs.getString("marca_produto"), null, null);
            Linha linha = new Linha(rs.getString("linha_produto"), null, null);
            Embalagem embalagem = new Embalagem(rs.getString("embalagem_produto"), null, null);
            Desconto desconto = new Desconto(rs.getInt("desconto_produto"), null);

            Produto produto = new Produto(rs.getString("nome_produto"), rs.getString("desc_produto"),
                    rs.getString("preco_produto"), rs.getString("peso_produto"), rs.getString("tamanho_produto"),
                    rs.getString("referencia_produto"), status, categoria, marca, linha, genero, embalagem, desconto,
                    null, null, null, null);

            return produto;
        }
    };

    @Override
    public void salvar(Entidade entidade) {
        Produto produto = (Produto) entidade;

        // Usa KeyHolder para capturar o ID gerado
        KeyHolder keyHolder = new GeneratedKeyHolder();

        String sqlProduto = "INSERT INTO `Produto`(`nome_produto`, `desc_produto`, `preco_produto`, `categoria_produto`, `marca_produto`, `peso_produto`, `tamanho_produto`, `linha_produto`, `genero_produto`, `referencia_produto`, `embalagem_produto`, `desconto_produto`, `status_produto`) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sqlProduto, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, produto.getNome());
            ps.setString(2, produto.getDescricao());
            ps.setString(3, produto.getPreco());
            ps.setString(4, produto.getCategoria().getNome());
            ps.setString(5, produto.getMarca().getNome());
            ps.setString(6, produto.getPeso());
            ps.setString(7, produto.getTamanho());
            ps.setString(8, produto.getLinha().getNome());
            ps.setString(9, produto.getGenero().getNome());
            ps.setString(10, produto.getReferencia());
            ps.setString(11, produto.getEmbalagem().getNome());
            ps.setInt(12, produto.getDesconto().getPorcentagem());
            ps.setString(13, produto.getStatus().getId());
            return ps;
        }, keyHolder);

        // Armazena o ID gerado no objeto
        produto.setId(String.valueOf(keyHolder.getKey().intValue()));

        String descricao = "SALVAR;" + produto.getClass() + ";";
        log(descricao, jdbcTemplate);
    }

    @Override
    public List<Entidade> buscar(Entidade entidade) {
        Produto produto = (Produto) entidade;

        List<Produto> produtos;
        // Se o produto tem ID, busca por ID
        if (produto.getId() != null && !produto.getId().equals("0") && !produto.getId().isEmpty()) {
            produtos = jdbcTemplate.query(
                    "SELECT id_produto, nome_produto, preco_produto, desc_produto, marca_produto, peso_produto, tamanho_produto, genero_produto, linha_produto, categoria_produto, referencia_produto, embalagem_produto, desconto_produto, s.nome_status FROM `Produto` p JOIN `Status` s ON p.status_produto = s.id_status WHERE id_produto = ?",
                    produtoMapper,
                    produto.getId());
        } else {
            // Caso contrário, retorna todos
            produtos = jdbcTemplate.query(
                    "SELECT id_produto, nome_produto, preco_produto, desc_produto, marca_produto, peso_produto, tamanho_produto, genero_produto, linha_produto, categoria_produto, referencia_produto, embalagem_produto, desconto_produto, s.nome_status FROM `Produto` p JOIN `Status` s ON p.status_produto = s.id_status",
                    produtoMapper);
        }

        return (List<Entidade>) (List<?>) produtos;
    }

    @Override
    public void atualizar(Entidade entidade) {
        Produto produto = (Produto) entidade;

        String sql = "UPDATE `Produto` SET `nome_produto` = ?, `desc_produto` = ?, `preco_produto` = ?, `categoria_produto` = ?, `marca_produto` = ?, `peso_produto` = ?, `tamanho_produto` = ?, `linha_produto` = ?, `genero_produto` = ?, `referencia_produto` = ?, `embalagem_produto` = ?, `desconto_produto` = ? WHERE id_produto = ?";
        jdbcTemplate.update(sql,
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco(),
                produto.getCategoria().getNome(),
                produto.getMarca().getNome(),
                produto.getPeso(),
                produto.getTamanho(),
                produto.getLinha().getNome(),
                produto.getGenero().getNome(),
                produto.getReferencia(),
                produto.getEmbalagem().getNome(),
                Integer.toString(produto.getDesconto().getPorcentagem()),
                produto.getId());

        String descricao = "ATUALIZAR;" + produto.getClass() + ";ID:" + produto.getId();
        log(descricao, jdbcTemplate);
    }

    @Override
    public void deletar(Entidade entidade) {
        Produto produto = (Produto) entidade;

        // Busca o id do status
        String selectSql = "SELECT status_produto FROM `Produto` WHERE id_produto = ?";
        List<Integer> ids = jdbcTemplate.query(selectSql, new Object[] { produto.getId() },
                (rs, rowNum) -> rs.getInt("status_produto"));

        int idStatus = 0;
        if (!ids.isEmpty()) {
            idStatus = ids.get(0);
        }

        String motivo = "Inativação adm";
        String nome = "Inativo";

        String sql = "UPDATE `Status` SET `nome_status` = ?, `motivo_status` = ? WHERE id_status = ?";
        jdbcTemplate.update(sql, nome, motivo, idStatus);

        String descricao = "INATIVAR;"+produto.getClass()+";ID:"+produto.getId();
        log(descricao, jdbcTemplate);
    }
}
