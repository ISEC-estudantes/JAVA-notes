package com.github.ISEC_estudantes.ED.exercicios.ficha5;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class tests6 {
    public static void main(String[] args) {


    }

    public static double testa_ex3(Pilha p, long sz) {
        long startTime = System.nanoTime();
        for (int j = 0; j < sz; j++) p.push(j);
        for (int j = 0; j < sz; j++) p.pop();
        return (System.nanoTime() - startTime) / 1000000.0;
    }


    public static double testa_ex4(Fila p, long sz){
        long startTime = System.nanoTime();
        for (int j = 0; j < sz; j++) p.add(j);
        for (int j = 0; j < sz; j++) p.remove(); // rete remove é à cabeça
        return (System.nanoTime() - startTime) / 1000000.0;
    }


    //teste da Fila , aqui pode se concluir que um array list demora exponencialmente a adicionar elementos enquanto que o linked list demora o mesmo tempo sempre
    public static void test_ex4() {
        List alist = new ArrayList();
        List llist = new LinkedList();
        long n, sz = 5000, nruns = 20;
        double tm;
        var pll = new Fila(llist);
        var pal = new Fila(alist);
        System.out.println("Tempo de execução médio " + nruns + "execuções(ms)");
        System.out.println("N | Arraylist | Linked list");

        for (int i = 1; i <= 30; i++) {
            n = sz * i;
            tm = 0;
            for (int j = 0; j < nruns; j++)
                tm += testa_ex4(pal, n);
            System.out.printf("%d | %9.2f | ", i, (float) (tm / nruns));
            tm = 0;
            for (int j = 0; j < nruns; j++)
                tm += testa_ex4(pll, n);
            System.out.printf(" %.2f/n", (float) (tm / nruns));
        }
    }

    //ambos demoram o mesmo tempo a adicionar
    public static void test_ex3() {
        List alist = new ArrayList();
        List llist = new LinkedList();
        long n, sz = 5000, nruns = 20;
        double tm;
        Pilha pll = new Pilha(llist);
        var pal = new Pilha(alist);
        System.out.println("Tempo de execução médio " + nruns + "execuções(ms)");
        System.out.println("N | Arraylist | Linked list");

        for (int i = 1; i <= 30; i++) {
            n = sz * i;
            tm = 0;
            for (int j = 0; j < nruns; j++)
                tm += testa_ex3(pal, n);
            System.out.printf("%d | %9.2f | ", i, (float) (tm / nruns));
            tm = 0;
            for (int j = 0; j < nruns; j++)
                tm += testa_ex3(pll, n);
            System.out.printf(" %.2f/n", (float) (tm / nruns));

        }
    }
}
