package com.github.ISEC_estudantes.ED.exercicios.aula4;

import java.util.NoSuchElementException;

public class DezReaisMutavel extends DezReais {
    DezReaisMutavel(){
        super();
    }


    public void remove(int pos){
//        if (pos > CAPACITY || pos< 0)
//            throw new NoSuchElementException("You are trying to access an element that does not exist.");
//        if(pos > size-1 && !(pos == 0 && size() == 1))
//            throw new IllegalStateException("You are trying to remove an element that does not exist.");
        while (pos < size-1)
            numeros[pos]= numeros[pos++];
        --size;
    }
}
