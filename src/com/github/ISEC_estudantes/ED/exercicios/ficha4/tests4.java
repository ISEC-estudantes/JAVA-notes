package com.github.ISEC_estudantes.ED.exercicios.ficha4;

/*
    UnsupportedOperationException – A operação (p.ex:
remoção) não é suportada.
– NoSuchElementException – Tentativa de acesso a um
elemento que não existe
– IllegalStateException – Tentativa de remoção sem avançar
para primeiro elemento ou tentativa de remover o mesmo
elemento mais do que uma vez.
– ConcurrentModificationException – Quando se tenta usar um
iterador inválido. Um iterador é inválido quando a colecção foi
alterada externamente (através de um outro iterador, por
exemplo).


 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

class ittest implements Iterator<Number> , Comparable<Number>{
    private boolean used = false;
    private Number Int = 0;

    @Override
    public boolean hasNext() {
        return !used;
    }

    @Override
    public Number next(){
        if(!hasNext()) throw new NoSuchElementException();
        used = !used;
        return Int;
    }

    @Override
    public int compareTo(Number o) {
        return Int - o;
    }
}

public class tests4 {
    public static void main(String[] args) {
//        var reais = new DezReais();
//        System.out.println(reais.toString());
//        System.out.println(reais.size());
//        System.out.println(reais.add((double) 2));
//        System.out.println(reais.size());
//        System.out.println(reais.get(0));
        var lol = new ittest();
        lol.remove();
        var dr = new DezReaisMutavel();
        dr.add(1);
        System.out.println(dr);
        dr.remove(0);
        System.out.println(dr);
//        for (var i : dr){
//            for each not emplemented yet
//        }

        //a complexidade é N



        LinkedList<Integer> ll = new LinkedList();
        var ill = ll.iterator();
        ill.next();
        ArrayList<Integer> ar = new ArrayList<>();
    }

}
