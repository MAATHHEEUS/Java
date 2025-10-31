package com.app.project.model;

import java.util.List;

public class Venda extends Entidade {
    private int clienteId;
    private String valor;
    private Status status;
    private int enderecoEntrega;
    private int enderecoFaturamento;
    private List<Pagamento> pagamentos;
    private String dataEntrega;
    private String frete;
    private String desconto;
    private List<ProdutoVenda> produtos;

    // Construtor
    public Venda(int clienteId, String valor, Status status, int enderecoEntrega, int enderecoFaturamento,
            List<Pagamento> pagamentos, List<ProdutoVenda> produtos, String dataEntrega, String frete,
            String desconto) {
        super.id = "0";
        super.dataCad = "";
        this.clienteId = clienteId;
        this.valor = valor;
        this.status = status;
        this.produtos = produtos;
        this.enderecoEntrega = enderecoEntrega;
        this.enderecoFaturamento = enderecoFaturamento;
        this.pagamentos = pagamentos;
        this.dataEntrega = dataEntrega;
        this.frete = frete;
        this.desconto = desconto;
    }

    // Getters e Setters

    public List<ProdutoVenda> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ProdutoVenda> produtos) {
        this.produtos = produtos;
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

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public void setEnderecoEntrega(int enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }

    public int getEnderecoFaturamento() {
        return enderecoFaturamento;
    }

    public void setEnderecoFaturamento(int enderecoFaturamento) {
        this.enderecoFaturamento = enderecoFaturamento;
    }

    public List<Pagamento> getPagamentos() {
        return pagamentos;
    }

    public void setPagamento(List<Pagamento> pagamentos) {
        this.pagamentos = pagamentos;
    }

    public String getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(String dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public String getFrete() {
        return frete;
    }

    public void setFrete(String frete) {
        this.frete = frete;
    }

    public String getDesconto() {
        return desconto;
    }

    public void setDesconto(String desconto) {
        this.desconto = desconto;
    }
}
