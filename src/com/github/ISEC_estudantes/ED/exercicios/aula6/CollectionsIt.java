package com.github.ISEC_estudantes.ED.exercicios.aula6;

import java.util.*;

public class CollectionsIt {

//    ex1
    public static <T> void myFill(List<? super T> list, T item){
//        Collections.fill(list, item);
//        for (int j = 0; j<list.size(); ++j)
//            list.set(j, item);//O(N2) em linked list
        ListIterator it = list.listIterator();
        while(it.hasNext()){
            it.next();
            it.set(item);
        }


    }
    //ex1 test
    public static void test_myFill(){

        List alist = new ArrayList();
        alist.add("A"); alist.add("B"); alist.add("C");
        System.out.println("Lista original: "+alist);
        Collections.fill(alist,"ED");
        System.out.println("Lista preenchida com fill: "+ alist);
        myFill(alist, "F");
        System.out.println("Lista preenchida com myFill: "+ alist);
    }

    //ex 2
    public static void imprimeInverso(List<?> alist){
        ListIterator it = alist.listIterator(alist.size());
        int j= alist.size();
        while(it.hasPrevious())
            System.out.println("["+(--j)+"]"+it.previous());
    }
    //test ex2
    public static void test_imprimeInverso()
    {

        List alist = new ArrayList();
        alist.add("A"); alist.add("B"); alist.add("C");
        System.out.println("Lista original: "+alist);
        imprimeInverso(alist);
    }

    public static void main(String[] args) {

    }
}

