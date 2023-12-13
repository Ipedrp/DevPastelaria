package com.example.devpastelaria;

public class Bebidas {

    /*int imgBebida;*/
    String nomeBebida;
    Double precoBebida;

    public Bebidas(String nomeBebida, Double precoBebida) {
        this.nomeBebida = nomeBebida;
        this.precoBebida = precoBebida;
    }

    /*public int getImgBebida() {
        return imgBebida;
    }

    public void setImgBebida(int imgBebida) {
        this.imgBebida = imgBebida;
    }*/

    public String getNomeBebida() {
        return nomeBebida;
    }

    public void setNomeBebida(String nomeBebida) {
        this.nomeBebida = nomeBebida;
    }

    public Double getPrecoBebida() {
        return precoBebida;
    }

    public void setPrecoBebida(Double precoBebida) {
        this.precoBebida = precoBebida;
    }
}
