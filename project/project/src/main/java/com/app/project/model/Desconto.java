package com.app.project.model;

public class Desconto extends Entidade {
    private int porcentagem;
    private Status status;

    // Construtor
    public Desconto(int porcentagem, Status status) {
        super.id = "0";
        super.dataCad = "";
        this.porcentagem = porcentagem;
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

    public int getPorcentagem() {
        return porcentagem;
    }

    public void setPorcentagem(int porcentagem) {
        this.porcentagem = porcentagem;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
