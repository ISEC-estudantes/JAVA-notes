package com.github.ISEC_estudantes.ED.exercicios.ficha1;

public class ficha1 {
    private static long stopTime;
    private static long startTime;
    static void ex1b(long n){
        long soma=0;
        startTimer();
        for(long i=0;i<n;i++)
            soma++;
        System.out.println("Soma="+soma);
        stopTimer();
        showTime();
    }
    static void ex1a(long n){
        long soma=0;
        startTimer();
        for(long i=0;i<n;i++)
            for(long j=0;j<n;j++)
                soma++;
        System.out.println("Soma="+soma);
        stopTimer();
        showTime();
    }
    private static void showTime() {
        long interval=stopTime-startTime;
        long secs=interval/1000000000L;
        long decs=interval-secs*1000000000L;
        decs/=100000000L;
        System.out.println("secs="+secs+"."+decs);
    }
    private static void stopTimer() {
        stopTime=System.nanoTime();
    }
    private static void startTimer() {
        startTime=System.nanoTime();
    }
    public static void main(String[] args) {
        int n = 12000;

        ex1a( n );
        ex1a( 4*n );
	/*	ex1b(2000000000L);
		ex1b(8000000000L);*/
    }
}
 /*
 Resuloção do exercicio

 1.
    a)
        O(N²)
        Caso a dimensão ser aumentada 4 vezes o tempo vai ter um aumento de (4N * 4N = 16N) 16 vezes
    b)
        O(N)
        Caso a dimensão ser aumentada 4 vezes o tempo vai se aumentar o mesmo, 4 vezes.
    c)
        O(N)
        Caso a dimensão ser aumentada 4 vezes o tempo vai ser aumentado 4 vezes pois é linear.
    d)
        O(N)
        Caso a dimensão ser aumentada 4 vezes o tempo vai aumentar linearmente 4 vezes.

    e)
        O(N+N=>2N=>N)
        A complexidade é N logo se aumentar se 4 vezes vai se ter um aumento de tempo linear de 4 vezes.

    f)
        O(20000)
        A complexidade é um valor constante logo vai ser sempre 20000.

    g)
        O(N³)
        A complexidade vai aumentar 4³ = 64

    h)
        O(N+N/2-1 -> N²)
        O tempo aumenta 16 vezes se os dados aumentarem 4 vezes

    i)
        O(N³)
        O tempo aumenta 64 vezes se os dados aumentarem 4 vezes

    j)
        O(?)
  */