// facade/ClienteFacade.java
package com.app.project.facade;

import com.app.project.dao.ClienteDAO;
import com.app.project.model.Cliente;
import com.app.project.model.Entidade;
import com.app.project.strategy.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteFacade {
    private final ClienteDAO clienteDAO = new ClienteDAO();
    private final List<IValidador> validadores = new ArrayList<>();

    @Autowired
    public ClienteFacade() {
        validadores.add(new ValidacaoNome());
        validadores.add(new ValidacaoEmail());
        validadores.add(new ValidacaoTelefone());
        validadores.add(new ValidacaoCPF());
    }

    public String cadastrarCliente(Cliente cliente) {
        for (IValidador validador : validadores) {
            if (validador.validar(cliente) != "") {
                return "Falha na validação: "+validador.validar(cliente);
            }
        }
        clienteDAO.salvar(cliente);
        return "Cliente salvo.";
    }

    public List<Entidade> listarClientes(Cliente cliente) {
        return clienteDAO.buscar(cliente);
    }

    public String atualizarCliente(Cliente cliente) {
        for (IValidador validador : validadores) {
            if (validador.validar(cliente) != "") {
                return "Falha na validação: "+validador.validar(cliente);
            }
        }
        clienteDAO.atualizar(cliente);
        return "Cliente atualizado.";
    }

    public void removerCliente(Cliente cliente) {
        clienteDAO.deletar(cliente);
    }

    public void removerEndereco(Cliente cliente) {
        clienteDAO.deletarEndereco(cliente);
    }
}
