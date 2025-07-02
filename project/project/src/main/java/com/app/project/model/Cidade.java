package com.app.project.model;

public class Cidade extends Entidade{
    private String nome;
    private Estado estado;

    // Construtor
    public Cidade(String nome, Estado estado) {
        this.id = "0";
        this.nome = nome;
        this.estado = estado;
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

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
