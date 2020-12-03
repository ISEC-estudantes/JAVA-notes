package com.github.ISEC_estudantes.ED.testes;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class modelo1a {
    public static void main(String[] args) {
        ex1();
    }

    public static void ex1() {
        int www1 = 0, www2 = 0, www3 = 0;
        int N = 100;
        for (int i = 0; i < N; i++) { // -> Complexidade N
            www1++;
            for (int j = 0; j < i; j++) { //  -> Complexidade N/2
                www2++;
                for (int k = 0; k < j; k++) { //  -> Complexidade N/3
                    www3++;
                }
            }
        }
        System.out.println("w1: " + www1 + "; w2: " + www2 + "; w3: " + www3 + "; resultado:" + N * (N / 2) * (N / 3));
    }

    //ex4
    class ex4 {
public class Inteiro implements Iterable<Integer> {
    private final Integer value;

    public Inteiro(int value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            boolean used = false;

            @Override
            public boolean hasNext() {
                return !used;
            }

            @Override
            public Integer next() {
                if (used)
                    throw new NoSuchElementException();
                return value;
            }
        };
    }
}


}
}