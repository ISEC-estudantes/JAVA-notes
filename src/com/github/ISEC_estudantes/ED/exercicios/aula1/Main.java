package com.github.ISEC_estudantes.ED.exercicios.aula1;

public class Main {
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
