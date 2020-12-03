package com.github.ISEC_estudantes.ED.exercicios.ficha4;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ItDezReaisMutavel implements Iterator<Double> {
    protected boolean podeRemover = false;
    protected DezReaisMutavel dezReaisMutavel;
    protected int modcounter = 0;

    int posUltimo = -1; //pos do ultimo valor que foi devolvido
    public ItDezReaisMutavel(DezReaisMutavel dezReaisMutavel) throws RuntimeException {
        this.dezReaisMutavel = dezReaisMutavel;
        modcounter = dezReaisMutavel.getModcounter();
    }

    @Override
    public Double next() {
        verificaModificacao();
        if (!hasNext())
            throw new NoSuchElementException("You are in the end of the line.");
        podeRemover = true;
        return dezReaisMutavel.get(++posUltimo);
    }
    @Override
    public boolean hasNext() {
        verificaModificacao();
        return posUltimo + 1 < dezReaisMutavel.size();
    }

    public void remove() {
        verificaModificacao();
        if (!podeRemover)
            throw new IllegalStateException(); // o elemento ja foi apagado
        podeRemover = false;//apos remover o valor, não é possivel remover outra vez
        dezReaisMutavel.remove(posUltimo--);
        ++modcounter;
    }

    public void verificaModificacao(){
        if(modcounter != dezReaisMutavel.getModcounter())
            throw new ConcurrentModificationException();
    }
}