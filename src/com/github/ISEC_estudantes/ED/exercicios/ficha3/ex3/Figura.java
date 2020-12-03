package com.github.ISEC_estudantes.ED.exercicios.ficha3.ex3;

public abstract class Figura implements Comparable<Figura> {
    public abstract int area();

    @Override
    public int compareTo(Figura figura) {
        int dif = area() - figura.area();
        if (dif>0) return 1;
        if(dif<0) return -1;
        return 0;
    }
}
abstract class X implements Comparable<X>{
    public abstract int cona();
    @Override
    public int compareTo(X o) {
        return Integer.compare(cona(), o.cona());
    }
}