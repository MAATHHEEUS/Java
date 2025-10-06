package com.app.project.model;

public class Cartao extends Entidade{
    private String numero;
    private String nome;
    private String tipo;
    private String cvv;
    private Status status;
    private Bandeira bandeira;
    private int clienteId;

    // Construtor
    public Cartao(String numero, String nome, String tipo,  String cvv, Bandeira bandeira, Status status, int clienteId) {
        super.id = "0";
        super.dataCad = "";
        this.numero = numero;
        this.nome = nome;
        this.tipo = tipo;
        this.cvv = cvv;
        this.bandeira = bandeira;
        this.status = status;
        this.clienteId = clienteId;
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

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCVV() {
        return cvv;
    }

    public void setCVV(String cvv) {
        this.cvv = cvv;
    }

    public Bandeira getBandeira() {
        return this.bandeira;
    }

    public void setBandeira(Bandeira bandeira) {
        this.bandeira = bandeira;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getClienteId() {
        return this.clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }
}