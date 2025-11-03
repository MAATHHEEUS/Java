package com.app.project.model;

public class Estoque extends Entidade {
    private int quantidade;
    private int produtoId;

    // Construtor
    public Estoque(int quantidade, int produtoId) {
        super.id = "0";
        super.dataCad = "";
        this.quantidade = quantidade;
        this.produtoId = produtoId;
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
}
