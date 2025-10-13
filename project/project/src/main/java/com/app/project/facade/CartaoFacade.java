// facade/CartaoFacade.java
package com.app.project.facade;

import com.app.project.dao.CartaoDAO;
import com.app.project.model.Cartao;
import com.app.project.model.Entidade;
import com.app.project.strategy.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartaoFacade {
    @Autowired
    private CartaoDAO cartaoDAO;

    private final List<IValidador> validadores = new ArrayList<>();

    @Autowired
    public CartaoFacade() {
        /*
         * validadores.add(new ValidacaoEmail());
         * validadores.add(new ValidacaoTelefone());
         * validadores.add(new ValidacaoCPF());
         */
    }

    public String cadastrarCartao(Cartao cartao) {
        for (IValidador validador : validadores) {
            if (validador.validar(cartao) != "") {
                return "Falha na validação: " + validador.validar(cartao);
            }
        }
        try {
            cartaoDAO.salvar(cartao);

            return "Cartao cadastrado com sucesso!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao cadastrar cartao: " + e.getMessage();
        }
    }

    public List<Entidade> listarCartoes(Cartao cartao) {
        return cartaoDAO.buscar(cartao);
    }

    public String atualizarCartao(Cartao cartao) {
        for (IValidador validador : validadores) {
            if (validador.validar(cartao) != "") {
                return "Falha na validação: " + validador.validar(cartao);
            }
        }
        cartaoDAO.atualizar(cartao);
        return "Cartao atualizado.";
    }

    public String removerCartao(Cartao cartao) {
        try {
            cartaoDAO.deletar(cartao);
            return "Cartao inativado com sucesso!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao inativar cartao: " + e.getMessage();
        }
    }
}
