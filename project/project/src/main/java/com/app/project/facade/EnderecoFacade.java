// facade/EnderecoFacade.java
package com.app.project.facade;

import com.app.project.dao.EnderecoDAO;
import com.app.project.model.Endereco;
import com.app.project.model.Entidade;
import com.app.project.strategy.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoFacade {
    @Autowired
    private EnderecoDAO enderecoDAO;

    private final List<IValidador> validadores = new ArrayList<>();

    @Autowired
    public EnderecoFacade() {
        /*
         * validadores.add(new ValidacaoEmail());
         * validadores.add(new ValidacaoTelefone());
         * validadores.add(new ValidacaoCPF());
         */
    }

    public String cadastrarEndereco(Endereco endereco) {
        for (IValidador validador : validadores) {
            if (validador.validar(endereco) != "") {
                return "Falha na validação: " + validador.validar(endereco);
            }
        }
        try {
            enderecoDAO.salvar(endereco);

            return "Endereco cadastrado com sucesso!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao cadastrar endereco: " + e.getMessage();
        }
    }

    public List<Entidade> listarEnderecos(Endereco endereco) {
        return enderecoDAO.buscar(endereco);
    }

    public String atualizarEndereco(Endereco endereco) {
        for (IValidador validador : validadores) {
            if (validador.validar(endereco) != "") {
                return "Falha na validação: " + validador.validar(endereco);
            }
        }
        enderecoDAO.atualizar(endereco);
        return "Endereco atualizado.";
    }

    public String removerEndereco(Endereco endereco) {
        try {
            enderecoDAO.deletar(endereco);
            return "Endereco inativado com sucesso!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao inativar endereco: " + e.getMessage();
        }
    }
}
