package com.app.project.strategy;

import com.app.project.model.Cliente;

public class ValidacaoCPF implements IValidador {
    @Override
    public String validar(Cliente cliente) {
        return cliente.getCpf() != null && cliente.getCpf().length() >= 11 ? "" : "CPF inválido.";
    }
}
