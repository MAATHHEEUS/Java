package com.app.project.model;

import java.util.List;

public class Produto extends Entidade {
    private String nome;
    private String descricao;
    private String preco;
    private String peso;
    private String tamanho;
    private String referencia;
    private Status status;
    private Categoria categoria;
    private Marca marca;
    private Linha linha;
    private Genero genero;
    private Embalagem embalagem;
    private Desconto desconto;
    private List<Imagem> imagens;
    private List<Cor> cores;
    private List<Material> materiais;
    private List<Promocao> promocoes;

    // Construtor
    public Produto(String nome, String descricao, String preco, String peso, String tamanho, String referencia,
            Status status, Categoria categoria, Marca marca, Linha linha, Genero genero, Embalagem embalagem,
            Desconto desconto, List<Imagem> imagens, List<Cor> cores, List<Material> materiais,
            List<Promocao> promocoes) {
        super.id = "0";
        super.dataCad = "";
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.peso = peso;
        this.tamanho = tamanho;
        this.referencia = referencia;
        this.status = status;
        this.categoria = categoria;
        this.marca = marca;
        this.linha = linha;
        this.genero = genero;
        this.embalagem = embalagem;
        this.desconto = desconto;
        this.imagens = imagens;
        this.cores = cores;
        this.materiais = materiais;
        this.promocoes = promocoes;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Linha getLinha() {
        return linha;
    }

    public void setLinha(Linha linha) {
        this.linha = linha;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Embalagem getEmbalagem() {
        return embalagem;
    }

    public void setEmbalagem(Embalagem embalagem) {
        this.embalagem = embalagem;
    }

    public Desconto getDesconto() {
        return desconto;
    }

    public void setDesconto(Desconto desconto) {
        this.desconto = desconto;
    }

    public List<Imagem> getImagens() {
        return imagens;
    }

    public void setImagens(List<Imagem> imagens) {
        this.imagens = imagens;
    }

    public List<Cor> getCores() {
        return cores;
    }

    public void setCores(List<Cor> cores) {
        this.cores = cores;
    }

    public List<Material> getMateriais() {
        return materiais;
    }

    public void setMateriais(List<Material> materiais) {
        this.materiais = materiais;
    }

    public List<Promocao> getPromocoes() {
        return promocoes;
    }

    public void setPromocoes(List<Promocao> promocoes) {
        this.promocoes = promocoes;
    }
}
