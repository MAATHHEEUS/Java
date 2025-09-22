package com.app.project.model;

public class Cupom extends Entidade{
    private String codigo;
    private String validade;
    private String valor;
    private Status status;
    private int clienteId;

    // Construtor
    public Cupom(String codigo, String validade, String valor, Status status, int clienteId) {
        super.id = "0";
        super.dataCad = "";
        this.codigo = codigo;
        this.validade = validade;
        this.valor = valor;
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
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