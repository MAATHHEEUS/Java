package com.app.project.model;

public class Endereco extends Entidade {
    private String logradouro;
    private String cep;
    private String tipo;
    private Cidade cidade;

    // Construtor
    public Endereco(String logradouro, String cep, Cidade cidade, String tipo) {
        this.id = "0";
        this.logradouro = logradouro;
        this.cep = cep;
        this.cidade = cidade;
        this.tipo = tipo;
    }

    // Getters e Setters
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
