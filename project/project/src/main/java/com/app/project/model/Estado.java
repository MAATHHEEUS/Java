package com.app.project.model;

public class Estado extends Entidade{
    private String uf;
    private String nome;

    // Construtor
    public Estado(String uf, String nome){
        this.id = "0";
        this.uf = uf;
        this.nome = nome;
    }

    // Getters e Setters
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
}
