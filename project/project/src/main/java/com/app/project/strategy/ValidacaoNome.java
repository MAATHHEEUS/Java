// strategy/ValidacaoNome.java
package com.app.project.strategy;

import com.app.project.model.Cliente;
import com.app.project.model.Entidade;

public class ValidacaoNome implements IValidador {
    @Override
    public String validar(Entidade entidade) {
        Cliente cliente = (Cliente) entidade;

        return cliente.getNome() != null && !cliente.getNome().isEmpty() ? "" : "Nome inválido.";
    }
}
