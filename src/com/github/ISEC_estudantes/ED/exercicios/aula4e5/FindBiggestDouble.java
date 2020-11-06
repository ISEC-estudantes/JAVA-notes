package com.github.ISEC_estudantes.ED.exercicios.aula4e5;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Iterator;

public class FindBiggestDouble {
    public static Double find(Iterator<Double> it) {
        if (it.hasNext()) {
            var max = it.next();
            var temp = max;
            while (it.hasNext()) {
                temp = it.next();
                if (temp > max)
                    max = temp;
            }
            return max;
        }
        throw new EmptyStackException();
    }

    public static void test() {
        var array = new ArrayList<Double>();

        double d = 1;
        while (d != 11)
            array.add(d++);

    }
    public static <T extends Comparable<? super T> > T findGeneric(Iterable<T> d){
        var it = d.iterator();
        T m,j;
        m= it.next();
        while(it.hasNext()){
            j=it.next();
            if(j.compareTo(m)> 0)m=j;
        }
        return m;
    }
}
