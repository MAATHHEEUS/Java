// facade/ClienteFacade.java
package com.app.project.facade;

import com.app.project.dao.CartaoDAO;
import com.app.project.dao.ClienteDAO;
import com.app.project.dao.EnderecoDAO;
import com.app.project.dao.StatusDAO;
import com.app.project.dao.TelefoneDAO;
import com.app.project.model.Cliente;
import com.app.project.model.Entidade;
import com.app.project.strategy.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteFacade {
    @Autowired
    private ClienteDAO clienteDAO;

    @Autowired
    private EnderecoDAO enderecoDAO;

    @Autowired
    private StatusDAO statusDAO;

    @Autowired
    private CartaoDAO cartaoDAO;

    @Autowired
    private TelefoneDAO telefoneDAO;

    private final List<IValidador> validadores = new ArrayList<>();

    @Autowired
    public ClienteFacade() {
        validadores.add(new ValidacaoNome());
        /*
         * validadores.add(new ValidacaoEmail());
         * validadores.add(new ValidacaoTelefone());
         * validadores.add(new ValidacaoCPF());
         */
    }

    public String cadastrarCliente(Cliente cliente) {
        for (IValidador validador : validadores) {
            if (validador.validar(cliente) != "") {
                return "Falha na validação: " + validador.validar(cliente);
            }
        }
        try {
            // Salva o status do cliente para ter o id
            statusDAO.salvar(cliente.getStatus());

            // Salva o cliente
            cliente.getStatus().setId(cliente.getStatus().getId());
            clienteDAO.salvar(cliente);

            // Se tiver endereços, salvar
            if (cliente.getEnderecos() != null && !cliente.getEnderecos().isEmpty()) {
                enderecoDAO.salvarEnderecos(cliente.getEnderecos(), cliente.getId());
            }

            // Se tiver cartão, salvar
            if (cliente.getCartoes() != null && !cliente.getCartoes().isEmpty()) {
                cliente.getCartoes().get(0).setClienteId(Integer.parseInt(cliente.getId()));
                cartaoDAO.salvar(cliente.getCartoes().get(0));
            }

            // Se tiver telefone, salvar
            if (cliente.getTelefones() != null && !cliente.getTelefones().isEmpty()) {
                cliente.getTelefones().get(0).setClienteId(Integer.parseInt(cliente.getId()));
                telefoneDAO.salvar(cliente.getTelefones().get(0));
            }

            return "Cliente cadastrado com sucesso!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao cadastrar cliente: " + e.getMessage();
        }
    }

    public List<Entidade> listarClientes(Cliente cliente) {
        return clienteDAO.buscar(cliente);
    }

    public String atualizarCliente(Cliente cliente) {
        for (IValidador validador : validadores) {
            if (validador.validar(cliente) != "") {
                return "Falha na validação: " + validador.validar(cliente);
            }
        }
        clienteDAO.atualizar(cliente);
        return "Cliente atualizado.";
    }

    public String atualizarSenha(Cliente cliente) {
        clienteDAO.atualizarSenha(cliente);
        return "Senha atualizada";
    }

    public String removerCliente(Cliente cliente) {
        try {
            clienteDAO.deletar(cliente);
            return "Cliente inativado com sucesso!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao inativar cliente: " + e.getMessage();
        }
    }
}
