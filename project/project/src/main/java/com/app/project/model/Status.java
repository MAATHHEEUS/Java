package com.app.project.model;

public class Status extends Entidade{
    private String nome;
    private String motivo;

    // Construtor
    public Status(String nome, String motivo) {
        super.id = "0";
        super.dataCad = "";
        this.nome = nome;
        this.motivo = motivo;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
}