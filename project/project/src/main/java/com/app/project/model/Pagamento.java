package com.app.project.model;

public class Pagamento extends Entidade {
    private String valor;
    private String dados;
    private Status status;
    private TipoPagto tipo;
    private int vendaId;

    // Construtor
    public Pagamento(String valor, String dados, Status status, TipoPagto tipo, int vendaId) {
        super.id = "0";
        super.dataCad = "";
        this.valor = valor;
        this.dados = dados;
        this.status = status;
        this.tipo = tipo;
        this.vendaId = vendaId;
    }

    // Getters e Setters
    public int getVendaId() {
        return vendaId;
    }

    public void setVendaId(int vendaId) {
        this.vendaId = vendaId;
    }

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

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getDados() {
        return dados;
    }

    public void setDados(String dados) {
        this.dados = dados;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public TipoPagto getTipo() {
        return tipo;
    }

    public void setTipo(TipoPagto tipo) {
        this.tipo = tipo;
    }
}
