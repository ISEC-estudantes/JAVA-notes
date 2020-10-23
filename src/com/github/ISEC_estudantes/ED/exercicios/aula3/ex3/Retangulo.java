package com.github.ISEC_estudantes.ED.exercicios.aula3.ex3;

public class Retangulo extends Figura {
    private int altura;
    private int largura;

    public Retangulo (int altura, int largura) {
        this.altura = altura;
        this.largura = largura;
    }


    @Override
    public int area() {
        return altura*largura/2;
    }
}
