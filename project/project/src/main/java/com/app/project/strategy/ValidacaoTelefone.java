// strategy/ValidacaoTelefone.java
package com.app.project.strategy;

import com.app.project.model.Cliente;
import com.app.project.model.Entidade;

public class ValidacaoTelefone implements IValidador {
    @Override
    public String validar(Entidade entidade) {
        Cliente cliente = (Cliente) entidade;

        return cliente.getTelefones() != null ? "" : "Telefone inválido.";
    }
}
