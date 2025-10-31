package com.app.project.model;

public class ProdutoVenda extends Entidade {
    private int quantidade;
    private int produtoId;
    private int vendaId;
    private Status status;

    // Construtor
    public ProdutoVenda(int quantidade, int produtoId, int vendaId, Status status) {
        super.id = "0";
        super.dataCad = "";
        this.quantidade = quantidade;
        this.produtoId = produtoId;
        this.vendaId = vendaId;
        this.status = status;
    }

    // Getters and Setters
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

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(int produtoId) {
        this.produtoId = produtoId;
    }

    public int getVendaId() {
        return vendaId;
    }

    public void setVendaId(int vendaId) {
        this.vendaId = vendaId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
