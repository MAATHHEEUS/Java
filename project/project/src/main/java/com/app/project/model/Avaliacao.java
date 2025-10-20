package com.app.project.model;

public class Avaliacao extends Entidade {
    private int estrelas;
    private String descricao;
    private int produtoId;
    private int clienteId;
    private Status status;

    // Contrutor
    public Avaliacao(int estrelas, String descricao, int produtoId, int clienteId, Status status) {
        super.id = "0";
        super.dataCad = "";
        this.estrelas = estrelas;
        this.descricao = descricao;
        this.produtoId = produtoId;
        this.clienteId = clienteId;
        this.status = status;
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

    public int getEstrelas() {
        return estrelas;
    }

    public void setEstrelas(int estrelas) {
        this.estrelas = estrelas;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(int produtoId) {
        this.produtoId = produtoId;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }
}
