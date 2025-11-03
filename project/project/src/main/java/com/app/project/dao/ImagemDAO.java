package com.app.project.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.app.project.model.Entidade;
import com.app.project.model.Imagem;

@Repository
public class ImagemDAO implements IDAO  {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Mapeia o resultado do banco para objeto Imagem
    private RowMapper<Imagem> imageMapper = new RowMapper<>() {
        @Override
        public Imagem mapRow(ResultSet rs, int rowNum) throws SQLException {
            Imagem imagem = new Imagem(rs.getString("textoAlt_imagem"), rs.getString("caminho_imagem"), null, Integer.parseInt(rs.getString("produto_imagem")));
            imagem.setId(rs.getString("id_imagem"));

            return imagem;
        }
    };
    
    @Override
    public void salvar(Entidade entidade) {
        Imagem imagem = (Imagem) entidade;

        // Salva a imagem
        String sql = "INSERT INTO `Imagem` (`textoAlt_imagem`, `caminho_imagem`, `produto_imagem`) VALUES (?, ?, ?)";

        jdbcTemplate.update(sql, imagem.getTextoAlt(), imagem.getCaminho(), imagem.getProdutoId());

        String descricao = "SALVAR;"+imagem.getClass()+";";
        log(descricao, jdbcTemplate);
    }

    public void salvarImagens(List<Imagem> imagens, int produtoId){
        for (Imagem i : imagens) {
            i.setProdutoId(produtoId);
            salvar(i);
        }
    }

    @Override
    public List<Entidade> buscar(Entidade entidade) {
        Imagem imagem = (Imagem) entidade;

        List<Imagem> imagens;

        // Se o telefone tem ID, busca por ID
        if (imagem.getId() != null && !imagem.getId().equals("0") && !imagem.getId().isEmpty()) {
            imagens = jdbcTemplate.query(
                    "SELECT * FROM `Imagem` WHERE id_imagem = ?",
                    imageMapper,
                    imagem.getId());
        } else {
            // Caso contrário, retorna todos do produto
            imagens = jdbcTemplate.query(
                "SELECT * FROM `Imagem` WHERE produto_imagem = ?",
                imageMapper, imagem.getProdutoId());
        }

        return (List<Entidade>) (List<?>) imagens;
    }

    @Override
    public void atualizar(Entidade entidade) {
        Imagem imagem = (Imagem) entidade;

        String sql = "UPDATE `Imagem` SET `textoAlt_imagem` = ?, `caminho_imagem` = ? WHERE id_imagem = ?";
        jdbcTemplate.update(sql, imagem.getTextoAlt(), imagem.getCaminho(), imagem.getId());

        String descricao = "ATUALIZAR;"+imagem.getClass()+";ID:"+imagem.getId();
        log(descricao, jdbcTemplate);
    }

    @Override
    public void deletar(Entidade entidade) {
        Imagem imagem = (Imagem) entidade;

        String sql = "DELETE FROM `Imagem` WHERE id_imagem = ?";
        jdbcTemplate.update(sql, imagem.getId());

        String descricao = "EXCLUSÃO;"+imagem.getClass()+";ID:"+imagem.getId();
        log(descricao, jdbcTemplate);
    }
}
