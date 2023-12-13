package com.example.devpastelaria;

public class Fornecedores {

    String id, nome;

    public Fornecedores(){}

    public Fornecedores(String id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
