
package com.app.project.model;

import java.util.List;

public class Cliente extends Entidade {
    private String nome;
    private String cpf;
    private String nascimento;
    private String email;
    private Genero genero;
    private String senha;
    private Status status;
    private List<Endereco> enderecos;
    private List<Cartao> cartoes;
    private List<Telefone> telefones;
    private List<Cupom> cupons;

    // Construtor, Getters e Setters
    public Cliente(String id, String dtCad, String nome, String cpf, String nascimento, String email, Genero genero, Status status, String senha, List<Endereco> enderecos, List<Cartao> cartoes, List<Telefone> telefones, List<Cupom> cupons) {
        super.id = id;
        super.dataCad = dtCad;
        this.nome = nome;
        this.cpf = cpf;
        this.nascimento = nascimento;
        this.email = email;
        this.genero = genero;
        this.status = status;
        this.senha = senha;
        this.enderecos = enderecos;
        this.cartoes = cartoes;
        this.telefones = telefones;
        this.cupons = cupons;
    }

    // Getters e Setters
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDataCad() {
        return this.dataCad;
    }

    public void setDataCad(String dtCad) {
        this.dataCad = dtCad;
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

    public String getNascimento() {
        return this.nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Genero getGenero() {
        return this.genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Endereco> getEnderecos() {
        return this.enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public List<Telefone> getTelefones() {
        return this.telefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }

    public List<Cartao> getcartoes() {
        return this.cartoes;
    }

    public void setCartoes(List<Cartao> cartoes) {
        this.cartoes = cartoes;
    }

    public List<Cupom> getCupons() {
        return this.cupons;
    }

    public void setCupons(List<Cupom> cupons) {
        this.cupons = cupons;
    }
}
