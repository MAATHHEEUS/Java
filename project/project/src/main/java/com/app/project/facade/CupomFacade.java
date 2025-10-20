// facade/CupomFacade.java
package com.app.project.facade;

import com.app.project.dao.CupomDAO;
import com.app.project.model.Cupom;
import com.app.project.model.Entidade;
import com.app.project.strategy.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CupomFacade {
    @Autowired
    private CupomDAO cupomDAO;

    private final List<IValidador> validadores = new ArrayList<>();

    @Autowired
    public CupomFacade() {
        /*
         * validadores.add(new ValidacaoEmail());
         * validadores.add(new ValidacaoTelefone());
         * validadores.add(new ValidacaoCPF());
         */
    }

    public String cadastrarCupom(Cupom cupom) {
        for (IValidador validador : validadores) {
            if (validador.validar(cupom) != "") {
                return "Falha na validação: " + validador.validar(cupom);
            }
        }
        try {
            cupomDAO.salvar(cupom);

            return "Cupom cadastrado com sucesso!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao cadastrar cupom: " + e.getMessage();
        }
    }

    public List<Entidade> listarCupons(Cupom cupom) {
        return cupomDAO.buscar(cupom);
    }

    public String atualizarCupom(Cupom cupom) {
        for (IValidador validador : validadores) {
            if (validador.validar(cupom) != "") {
                return "Falha na validação: " + validador.validar(cupom);
            }
        }
        cupomDAO.atualizar(cupom);
        return "Cupom atualizado.";
    }

    public String removerCupom(Cupom cupom) {
        try {
            cupomDAO.deletar(cupom);
            return "Cupom inativado com sucesso!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao inativar cupom: " + e.getMessage();
        }
    }
}
