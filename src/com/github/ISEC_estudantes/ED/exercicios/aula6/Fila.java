package com.github.ISEC_estudantes.ED.exercicios.aula6;

import java.util.List;
import java.util.ListIterator;

//ex 4
public class Fila<T> {
    List<? super T> l;

    public Fila(List<? super T> li) {
        l = (li);
        l.clear();
    }

    public boolean empty() {
        return l.size() == 0;
    }

    //adiciona no fim
    public void add(T n) {
        l.add(n);
    }

    //remove primeiro da fila
    public T remove() {
        return (T) l.remove(0);
    }

    //devolve primeiro
    public T element() {
        ListIterator<? super T> li = l.listIterator();
        return (T) li.next();
    }
}
