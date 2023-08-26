package com.microservices.Models;

public class Cartoes {
    
    private Long id;
    private String nome;
    private String numero;
    private String validade;
    private String codigoSeguranca;

    // Construtor que você precisa
    public Cartoes(Long id, String nome, String numero, String validade, String codigoSeguranca) {
        this.id = id;
        this.nome = nome;
        this.numero = numero;
        this.validade = validade;
        this.codigoSeguranca = codigoSeguranca;
    }

    // Getters e setters para cada campo
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    public String getCodigoSeguranca() {
        return codigoSeguranca;
    }

    public void setCodigoSeguranca(String codigoSeguranca) {
        this.codigoSeguranca = codigoSeguranca;
    }


    @Override
    public String toString() {
        return "Cartoes [nome=" + nome + "]";
    }

    
}
