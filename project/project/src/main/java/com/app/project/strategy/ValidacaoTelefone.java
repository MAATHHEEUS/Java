// strategy/ValidacaoTelefone.java
package com.app.project.strategy;

import com.app.project.model.Cliente;

public class ValidacaoTelefone implements IValidador {
    @Override
    public String validar(Cliente cliente) {
        return cliente.getTelefones() != null ? "" : "Telefone inválido.";
    }
}
