package com.github.ISEC_estudantes.ED.exercicios.aula3.ex5;

import com.github.ISEC_estudantes.ED.exercicios.aula3.ex3.Figura;

public class Ponto<T extends Number,F extends Number>  {
    private T x;
    private F y;
    public Ponto(T x, F y){
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" +
                 x +
                ", " + y +
                ')';
    }
    public void copy(Ponto<? extends T, ? extends F> p){
        x = p.x; y= p.y;
    }
}
