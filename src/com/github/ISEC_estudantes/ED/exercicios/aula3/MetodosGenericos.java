package com.github.ISEC_estudantes.ED.exercicios.aula3;

import com.github.ISEC_estudantes.ED.exercicios.aula3.ex3.Figura;
import com.github.ISEC_estudantes.ED.exercicios.aula3.ex3.Quadrado;
import com.github.ISEC_estudantes.ED.exercicios.aula3.ex3.Retangulo;
import com.github.ISEC_estudantes.ED.exercicios.aula3.ex5.Ponto;

import java.util.ArrayList;

public class MetodosGenericos {

    //demo de print de objetos genericos
    public static <E> void printArray(E[] tab) {
        for (E element : tab) System.out.println(element);
    }

    public static void demoPrintArray() {
        Integer[] tabi = {1, 2, 3, 4, 5};//tabela de inteiros
        Double[] tabd = {1.1, 2.2, 3.3, 4.4};//tabela de reais
        printArray(tabi);
        printArray(tabd);
    }

    //demo of comparations
    public static <T extends Comparable<T>> T maxim(T x, T y, T z) {
        T max = x;
        if (y.compareTo(max) > 0) max = y;
        if (z.compareTo(max) > 0) max = z;
        return max;
    }

    public static void demoMaxim() {
        System.out.println(3 + " " + 4 + " " + 5 + ":" + maxim(3, 4, 5));
        System.out.println(6.6 + " " + 8.8 + " " + 7.7 + ":" + maxim(6.6, 7.7, 8.8));
        System.out.println("Joana , Ze, Joao :" + maxim("Joana ", "Ze", "Joao"));
    }


    //ex1
    //pesquisa de um elemento num array generico(my try)
    public static <T> boolean findInArrayRepeatedWithEquals(T[] array, T findThis) {
        var count = 0;
        for (T i : array)
            if (i == findThis)
                count++;
        return count > 1;
    }

    // procura por um comparavel
    public static <T extends Comparable<T>> boolean findInArrayRepeatedCompareTo(T[] array, T findThis) {
        int count = 0;
        for (T i : array)
            if (i.compareTo(findThis) == 0)
                count++;
        return count > 1;
    }


    public static void demoFindInArray() {
        Integer chave1 = 2;
        Integer[] tab1 = {2, 2, 3, 4, 5};
        String chave2 = "Jose";
        String[] tab2 = {"Jose", "Jose", "Tina"};
        ArrayList<String> list = new ArrayList<String>();
        list.add("Jose");
        list.add("Jose");
        list.add("Tina");
        System.out.println(chave1 + " encontra-se " + findInArrayRepeatedCompareTo(tab1, chave1));

    }

    //ex3
    public static <Figura extends Comparable<Figura>> boolean compareArea(Figura fig1, Figura fig2) {
        return (fig1.compareTo(fig2) == 0);
    }


    public static void demoCompareArea() {
        var quadrado = new Quadrado(4);
        var rec = new Retangulo(2, 5);
        System.out.println(compareArea(quadrado, rec));

    }

    public static int ex3a(Figura f1, Figura f2) {
        return f2.compareTo(f1);
    }

    public static int ex3b(Retangulo r, Comparable<? super Retangulo> c) {
        return c.compareTo(r);
    }

    public static <T> int ex3c(T t, Comparable<? super T> cmp) {
        return cmp.compareTo(t);
    }


    //ex4
    public static <T extends Comparable<? super T>> boolean isThereABiggerThan(T[] array, T toComparable) {
        for (var i : array)
            if (i.compareTo(toComparable) > 0)
                return true;
        return false;
    }

    public static void demo_isThereABiggerThan() {
        Integer m[] = {3, 2, 5, 3};
        String n[] = {"Ada", "Albino"};
        System.out.println(isThereABiggerThan(m, 2));// deve returnar true
        System.out.println(isThereABiggerThan(n, "Francisco")); // deve returnar fase
    }

    //ex5
    public static void demo_utilizarPontoComCoordenadasGenericas() {
        var ponto1 = new Ponto<>(2, 3);
        var ponto2 = new Ponto<>(0, 0);
        System.out.println(ponto1);
        System.out.println(ponto2);
        ponto1.copy(ponto2);
        System.out.println(ponto1);
    }

    ///MAIN
    public static void main(String args[]) {
        demo_utilizarPontoComCoordenadasGenericas();
    }
}
