// facade/EstoqueFacade.java
package com.app.project.facade;

import com.app.project.dao.EstoqueDAO;
import com.app.project.model.Estoque;
import com.app.project.model.Entidade;
import com.app.project.strategy.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstoqueFacade {
    @Autowired
    private EstoqueDAO estoqueDAO;

    private final List<IValidador> validadores = new ArrayList<>();

    @Autowired
    public EstoqueFacade() {
        /*
         * validadores.add(new ValidacaoEmail());
         * validadores.add(new Validacaoestoque());
         * validadores.add(new ValidacaoCPF());
         */
    }

    public String cadastrarEstoque(Estoque estoque) {
        for (IValidador validador : validadores) {
            if (validador.validar(estoque) != "") {
                return "Falha na validação: " + validador.validar(estoque);
            }
        }
        try {
            estoqueDAO.salvar(estoque);

            return "Estoque cadastrado com sucesso!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao cadastrar estoque: " + e.getMessage();
        }
    }

    public List<Entidade> listarEstoque(Estoque estoque) {
        return estoqueDAO.buscar(estoque);
    }

    public String atualizarEstoque(Estoque estoque) {
        for (IValidador validador : validadores) {
            if (validador.validar(estoque) != "") {
                return "Falha na validação: " + validador.validar(estoque);
            }
        }
        estoqueDAO.atualizar(estoque);
        return "Estoque atualizado.";
    }

    public String removerEstoque(Estoque estoque) {
        return "";
    }
}
