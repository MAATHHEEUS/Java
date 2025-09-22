package com.app.project.model;

public class Genero extends Entidade{
    private String nome;
    private Status status;

    // Construtor
    public Genero(String nome, Status status) {
        super.id = "0";
        super.dataCad = "";
        this.nome = nome;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}