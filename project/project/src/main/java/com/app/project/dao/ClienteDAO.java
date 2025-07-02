// dao/ClienteDAOImpl.java
package com.app.project.dao;

import com.app.project.model.Cliente;
import com.app.project.model.Entidade;

import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class ClienteDAO implements IDAO {
    private final Map<String, Cliente> banco = new HashMap<>();

    @Override
    public void salvar(Entidade entidade) {
        Cliente cliente = (Cliente) entidade;
        banco.put(cliente.getId(), cliente);
    }


    @Override
    public List<Entidade> buscar(Entidade entidade) {
        return new ArrayList<>(banco.values());
    }

    @Override
    public void atualizar(Entidade entidade) {
        Cliente cliente = (Cliente) entidade;
        banco.put(cliente.getId(), cliente);
    }

    @Override
    public void deletar(Entidade entidade) {
        Cliente cliente = (Cliente) entidade;
        banco.remove(cliente.getId());
    }

    public void deletarEndereco(Entidade entidade){
        Cliente cliente = (Cliente) entidade;
        banco.get(cliente.getId()).setEnderecos(null);
    }
}
