package com.github.ISEC_estudantes.ED.exercicios.aula4;

import java.util.NoSuchElementException;

public class ItDezReaisMutavel {
    protected boolean podeRemover = false;
    protected DezReaisMutavel dezReaisMutavel;
    int posUltimo = -1; //pos do ultimo valor que foi devolvido
    public ItDezReaisMutavel(DezReaisMutavel dezReaisMutavel) throws RuntimeException {
        this.dezReaisMutavel = dezReaisMutavel;
    }

    public Double next() {
        if (!hasNext())
            throw new NoSuchElementException("You are in the end of the line.");
        podeRemover = true;
        return dezReaisMutavel.get(++posUltimo);
    }

    public boolean hasNext() {
        return posUltimo + 1 < dezReaisMutavel.size();
    }

    public void remove() {
        if (!podeRemover)
            throw new IllegalStateException();
        podeRemover = false;//apos remover o valor, não é possivel remover outra vez
        dezReaisMutavel.remove(posUltimo--);
    }
}