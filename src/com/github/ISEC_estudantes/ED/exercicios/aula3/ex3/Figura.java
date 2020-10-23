package com.github.ISEC_estudantes.ED.exercicios.aula3.ex3;

public abstract class Figura implements Comparable<Figura> {
    public abstract int area();

    @Override
    public int compareTo(Figura figura) {
        double dif = area() - figura.area();
        if (dif>0) return 1;
        if(dif<0) return -1;
        return 0;
    }
}
