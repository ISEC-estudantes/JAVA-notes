package com.github.ISEC_estudantes.ED.exercicios.aula3.ex3;

public class Triangulo extends Figura {
    private int altura;
    private int largura;

    public Triangulo(int altura, int largura) {
        this.altura = altura;
        this.largura = largura;
    }

    @Override
    public int area() {
        return altura*largura/2;
    }
}
