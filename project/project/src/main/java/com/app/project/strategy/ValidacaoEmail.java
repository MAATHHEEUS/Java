// strategy/ValidacaoEmail.java
package com.app.project.strategy;

import com.app.project.model.Cliente;

public class ValidacaoEmail implements IValidador {
    @Override
    public String validar(Cliente cliente) {
        return cliente.getEmail() != null && cliente.getEmail().contains("@") ? "" : "E-mail inválido.";
    }
}
