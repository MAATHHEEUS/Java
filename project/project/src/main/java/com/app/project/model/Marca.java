package com.app.project.model;

public class Marca extends Entidade {
    private String nome;
    private String descricao;
    private Status status;

    // Construtor
    public Marca(String nome, String descricao, Status status) {
        super.id = "0";
        super.dataCad = "";
        this.nome = nome;
        this.descricao = descricao;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
