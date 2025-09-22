package com.app.project.model;

public class Log extends Entidade{
    private String descricao;

    // Construtor
    public Log(String descricao) {
        super.id = "0";
        super.dataCad = "";
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}