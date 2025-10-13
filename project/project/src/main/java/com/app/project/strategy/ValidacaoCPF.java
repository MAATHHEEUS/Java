package com.app.project.strategy;

import com.app.project.model.Cliente;
import com.app.project.model.Entidade;

public class ValidacaoCPF implements IValidador {
    @Override
    public String validar(Entidade entidade) {
        Cliente cliente = (Cliente) entidade;
        
        return cliente.getCpf() != null && cliente.getCpf().length() >= 11 ? "" : "CPF inválido.";
    }
}
