package com.app.project.model;

public class Imagem extends Entidade {
    private String textoAlt;
    private String caminho;
    private int produtoId;
    private Status status;

    // Construtor
    public Imagem(String textoAlt, String caminho, Status status, int produtoId) {
        super.id = "0";
        super.dataCad = "";
        this.textoAlt = textoAlt;
        this.caminho = caminho;
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

    public String getTextoAlt() {
        return textoAlt;
    }

    public void setTextoAlt(String textoAlt) {
        this.textoAlt = textoAlt;
    }

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

    public int getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(int produtoId) {
        this.produtoId = produtoId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
