
package com.app.project.model;

import java.util.List;

public class Cliente extends Entidade {
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private List<Endereco> enderecos;

    // Construtor, Getters e Setters
    public Cliente(String id, String nome, String cpf, String email, String telefone, List<Endereco> enderecos) {
        super.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.enderecos = enderecos;
    }

    // Getters e Setters
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public void setTelefone(String tel) {
        this.telefone = tel;
    }

    public List<Endereco> getEnderecos() {
        return this.enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }
}
