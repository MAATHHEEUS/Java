package com.app.project.model;

public class Estado extends Entidade{
    private String uf;
    private String nome;
    private Pais pais;
    private Status status;

    // Construtor
    public Estado(String uf, String nome, Status status, Pais pais){
        super.id = "0";
        super.dataCad = "";
        this.uf = uf;
        this.nome = nome;
        this.status = status;
        this.pais = pais;
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

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
    
    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }
}
