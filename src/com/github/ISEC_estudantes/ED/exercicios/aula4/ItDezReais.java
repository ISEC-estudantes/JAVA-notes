package com.github.ISEC_estudantes.ED.exercicios.aula4;

import java.util.Iterator;
import java.util.MissingResourceException;
import java.util.NoSuchElementException;

public class ItDezReais implements Iterator<Double> {
    protected int posUltimo = -1;
    protected DezReais dezReais = null;
    protected final int modificacoes = 0;

    public ItDezReais(DezReais dezReais) throws RuntimeException{
        this.dezReais = dezReais;

    }


    public Double next() /*throws NoSuchElementException*/{
        if (!hasNext())
            throw new NoSuchElementException("You are in the end of the line.");
        return dezReais.get(++posUltimo);
    }

    public boolean hasNext() {
        return  posUltimo+ 1 < dezReais.size();
    }


}
