// strategy/ValidacaoEmail.java
package com.app.project.strategy;

import com.app.project.model.Cliente;
import com.app.project.model.Entidade;

public class ValidacaoEmail implements IValidador {
    @Override
    public String validar(Entidade entidade) {
        Cliente cliente = (Cliente) entidade;

        return cliente.getEmail() != null && cliente.getEmail().contains("@") ? "" : "E-mail inválido.";
    }
}
