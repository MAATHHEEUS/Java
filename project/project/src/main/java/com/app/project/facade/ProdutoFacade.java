package com.app.project.facade;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.project.dao.ImagemDAO;
import com.app.project.dao.ProdutoDAO;
import com.app.project.dao.RelProdCorDAO;
import com.app.project.dao.RelProdMaterialDAO;
import com.app.project.dao.RelProdPromocaoDAO;
import com.app.project.dao.StatusDAO;
import com.app.project.model.Entidade;
import com.app.project.model.Produto;
import com.app.project.strategy.IValidador;
import com.app.project.strategy.ValidacaoNome;

@Service
public class ProdutoFacade {
    @Autowired
    private ProdutoDAO produtoDAO;

    @Autowired
    private StatusDAO statusDAO;

    @Autowired
    private ImagemDAO imagemDAO;

    @Autowired 
    private RelProdCorDAO relProdCorDAO;

    @Autowired 
    private RelProdMaterialDAO relProdMaterialDAO;

    @Autowired 
    private RelProdPromocaoDAO relProdPromocaoDAO;

    private final List<IValidador> validadores = new ArrayList<>();

    @Autowired
    public ProdutoFacade() {
        /*
         * validadores.add(new ValidacaoEmail());
         * validadores.add(new ValidacaoTelefone());
         * validadores.add(new ValidacaoCPF());
         */
    }

    public String salvarProduto(Produto produto) {
        for (IValidador validador : validadores) {
            if (validador.validar(produto) != "") {
                return "Falha na validação: " + validador.validar(produto);
            }
        }
        try {
            // Salva o status do produto para ter o id
            statusDAO.salvar(produto.getStatus());

            // Salva o produto
            produto.getStatus().setId(produto.getStatus().getId());
            produtoDAO.salvar(produto);

            // Se tiver imagens, salvar
            if (produto.getImagens() != null && !produto.getImagens().isEmpty()) {
                produto.getImagens().get(0).setProdutoId(Integer.parseInt(produto.getId()));
                imagemDAO.salvarImagens(produto.getImagens(), Integer.parseInt(produto.getId()));
            }

            // Se tiver cores, salvar
            if (produto.getCores() != null && !produto.getCores().isEmpty()) {
                relProdCorDAO.salvarCores(produto.getCores(), Integer.parseInt(produto.getId()));
            }

            // Se tiver materiais, salvar
            if (produto.getMateriais() != null && !produto.getMateriais().isEmpty()) {
                relProdMaterialDAO.salvarMateriais(produto.getMateriais(), Integer.parseInt(produto.getId()));
            }

            // Se tiver promocoes, salvar
            if (produto.getPromocoes() != null && !produto.getPromocoes().isEmpty()) {
                relProdPromocaoDAO.salvarPromocoes(produto.getPromocoes(), Integer.parseInt(produto.getId()));
            }

            return "Produto cadastrado com sucesso!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao cadastrar produto: " + e.getMessage();
        }
    }

    public List<Entidade> listarProdutos(Produto produto){
        return produtoDAO.buscar(produto);
    }

    public String atualizarProduto(Produto produto) {
        for (IValidador validador : validadores) {
            if (validador.validar(produto) != "") {
                return "Falha na validação: " + validador.validar(produto);
            }
        }
        produtoDAO.atualizar(produto);
        return "Produto atualizado.";
    }

    public String removerProduto(Produto produto){
        try {
            produtoDAO.deletar(produto);
            return "Produto inativado com sucesso!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao inativar Produto: " + e.getMessage();
        }
    }
}
