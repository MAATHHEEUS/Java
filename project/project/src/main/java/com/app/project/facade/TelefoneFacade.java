// facade/TelefoneFacade.java
package com.app.project.facade;

import com.app.project.dao.TelefoneDAO;
import com.app.project.model.Telefone;
import com.app.project.model.Entidade;
import com.app.project.strategy.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TelefoneFacade {
    @Autowired
    private TelefoneDAO telefoneDAO;

    private final List<IValidador> validadores = new ArrayList<>();

    @Autowired
    public TelefoneFacade() {
        /*
         * validadores.add(new ValidacaoEmail());
         * validadores.add(new ValidacaoTelefone());
         * validadores.add(new ValidacaoCPF());
         */
    }

    public String cadastrarTelefone(Telefone telefone) {
        for (IValidador validador : validadores) {
            if (validador.validar(telefone) != "") {
                return "Falha na validação: " + validador.validar(telefone);
            }
        }
        try {
            telefoneDAO.salvar(telefone);

            return "Telefone cadastrado com sucesso!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao cadastrar telefone: " + e.getMessage();
        }
    }

    public List<Entidade> listarTelefones(Telefone telefone) {
        return telefoneDAO.buscar(telefone);
    }

    public String atualizarTelefone(Telefone telefone) {
        for (IValidador validador : validadores) {
            if (validador.validar(telefone) != "") {
                return "Falha na validação: " + validador.validar(telefone);
            }
        }
        telefoneDAO.atualizar(telefone);
        return "Telefone atualizado.";
    }

    public String removerTelefone(Telefone telefone) {
        try {
            telefoneDAO.deletar(telefone);
            return "Telefone inativado com sucesso!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao inativar telefone: " + e.getMessage();
        }
    }
}
