package com.example.devpastelaria;

import java.util.Objects;

public class Produto {

    String nome;
    double preco;
    int qtd;

    public Produto(){}

    public Produto(String nome, double preco, int qtd) {
        this.nome = nome;
        this.preco = preco;
        this.qtd = qtd;
    }

    public String getNome() {

        return nome;
    }

    public void setNome(String nome) {

        this.nome = nome;
    }

    public double getPreco() {

        return preco;
    }

    public void setPreco(double preco) {

        this.preco = preco;
    }

    public int getQtd() {

        return qtd;
    }

    public void setQtd(int qtd) {

        this.qtd = qtd;
    }

   /*
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return nome.equals(produto.nome);
    }

    @Override
    public int hashCode() {

        return Objects.hash(nome);
    }
    */

}
