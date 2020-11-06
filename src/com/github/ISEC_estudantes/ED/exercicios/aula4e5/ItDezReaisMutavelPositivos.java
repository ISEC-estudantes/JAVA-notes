package com.github.ISEC_estudantes.ED.exercicios.aula4e5;

import java.util.NoSuchElementException;

public class ItDezReaisMutavelPositivos extends ItDezReaisMutavel {

    public ItDezReaisMutavelPositivos(DezReaisMutavel dezReaisMutavel) throws RuntimeException {
        super(dezReaisMutavel);
    }

    @Override
    public Double next() {
        verificaModificacao();
        if (!hasNext())
            throw new NoSuchElementException("You are in the end of the line.");
        podeRemover = true;
        return dezReaisMutavel.get(hasNextPos());
    }

    private int hasNextPos() {
        int posPos = posUltimo + 1;
        while (super.hasNext())
            if (dezReaisMutavel.get(posPos++) >= 0)
                return --posPos;
        return -1;
    }

    @Override
    public boolean hasNext() {
        if (super.hasNext())
            return hasNextPos()!=-1;
        return false;
    }
}
