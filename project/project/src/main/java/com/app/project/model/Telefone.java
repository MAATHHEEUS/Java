package com.app.project.model;

public class Telefone extends Entidade{
    private String numero;
    private String ddd;
    private String tipo;
    private Status status;
    private int clienteId;

    // Construtor
    public Telefone(String numero, String ddd, String tipo, Status status, int clienteId) {
        super.id = "0";
        super.dataCad = "";
        this.numero = numero;
        this.ddd = ddd;
        this.tipo = tipo;
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

    public String getDDD() {
        return ddd;
    }

    public void setDDD(String ddd) {
        this.ddd = ddd;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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