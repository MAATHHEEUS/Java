// strategy/ValidacaoNome.java
package com.app.project.strategy;

import com.app.project.model.Cliente;

public class ValidacaoNome implements IValidador {
    @Override
    public String validar(Cliente cliente) {
        return cliente.getNome() != null && !cliente.getNome().isEmpty() ? "" : "Nome inválido.";
    }
}
