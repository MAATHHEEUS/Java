package com.pizzaria.app.service;

import com.pizzaria.app.entity.Cliente;
import com.pizzaria.app.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente novoCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public Cliente atualizarCliente(String id, Cliente clienteAtualizado) {
        return clienteAtualizado;
        // return clienteRepository.findById(id)
        //         .map(cliente -> {
        //             cliente.setNome(clienteAtualizado.getNome());
        //             cliente.setEndereco(clienteAtualizado.getEndereco());
        //             cliente.setTelefone(clienteAtualizado.getTelefone());
        //             return clienteRepository.save(chamado);
        //         })
        //         .orElseThrow(() -> new RuntimeException("Chamado não encontrado com id: " + id));
    }

    public String deletarCliente(String id){
        return "Cliente deletado";
    }
}
