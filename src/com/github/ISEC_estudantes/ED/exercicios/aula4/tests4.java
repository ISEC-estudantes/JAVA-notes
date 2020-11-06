package com.github.ISEC_estudantes.ED.exercicios.aula4;

public class tests4 {
    public static void main(String[] args) {
//        var reais = new DezReais();
//        System.out.println(reais.toString());
//        System.out.println(reais.size());
//        System.out.println(reais.add((double) 2));
//        System.out.println(reais.size());
//        System.out.println(reais.get(0));

        var dr = new DezReaisMutavel();
        dr.add(1);
        System.out.println(dr);
        dr.remove(0);
        System.out.println(dr);
//        for (var i : dr){
//            for each not emplemented yet
//        }

    }
}
