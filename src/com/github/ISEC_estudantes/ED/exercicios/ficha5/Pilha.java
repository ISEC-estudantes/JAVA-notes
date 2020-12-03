package com.github.ISEC_estudantes.ED.exercicios.ficha5;

import java.util.List;
import java.util.ListIterator;

public class Pilha<T> {
    List<? super T> l;

    public Pilha(List<? super T> li) {
        l = li;
        l.clear();
    }

    public boolean empty() {
        return l.size() == 0;
    }

    public T peek() {
        //devolve ultimo
        ListIterator<? super T> li = l.listIterator(l.size());
        return (T) li.previous();
    }

    public T pop() {
        //Remove no fim
        return (T) l.remove(l.size() - 1);
    }

    public void push(T n) { //adiciona no fim
        l.add(n);
    }



}

