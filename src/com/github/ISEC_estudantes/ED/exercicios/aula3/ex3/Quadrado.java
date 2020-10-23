package com.github.ISEC_estudantes.ED.exercicios.aula3.ex3;

public class Quadrado extends Figura{
    private int lado;

    public Quadrado (int lado) {
        this.lado  = lado;
    }

    @Override
    public int area() {
        return lado*lado;
    }
}
