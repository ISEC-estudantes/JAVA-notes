package com.github.ISEC_estudantes.ED.exercicios.ficha6;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Set;

public class GestorImpressoras {
    HashMap<String, Impressora> mp = new HashMap<>();

    PriorityQueue<Impressora> impressoras = new PriorityQueue<>((Impressora a, Impressora b)-> {

        int diff = a.getNumeroTrabalhos()-b.getNumeroTrabalhos(), diff2;
        if(diff == 0) {
            diff2 = a.peekProximoTrabalho().getPaginas() - b.peekProximoTrabalho().getPaginas();
            if(diff2 == 0){
                int rand = new Random().nextInt(2);
                if(rand == 0)return -1;
                return 1;
            }
            return diff2;
        }
        return diff;
    });

    public Impressora getPrinter(String nome) {
        return mp.get(nome);
    }




    public Set<String> getNomes() {
        return mp.keySet();
    }//O(1)

    public void addPrinter(Impressora i) {
        mp.put(i.getNome(),i);
        impressoras.add(i);
    }

    public void removePrinter(String nome){
        mp.remove(nome);
    }

    public boolean existe(String nome){
        return mp.containsKey(nome);
    }

    public static void main(String[] args) {
        var gestor = new GestorImpressoras();
        gestor.addPrinter(new Impressora("Lab1", "HP", "MOD 82", "drv 1", "v1", 1));
        gestor.addPrinter(new Impressora("Lab2", "HP 2", "MOD 83", "drv 2", "v1", 1));
        gestor.addPrinter(new Impressora("nao", "mishmosh", "k47", "kernel-based", "69", 23));
        gestor.addPrinter(new Impressora("yay", "mishmosh", "k47", "kernel-based", "69", 23));
        gestor.addPrinter(new Impressora("boorru", "mishmosh", "k47", "kernel-based", "69", 23));
        gestor.addPrinter(new Impressora("xixicoco", "mishmosh", "k47", "kernel-based", "69", 23));
        var n = gestor.getPrinter("Lab2");
    n.show();
        System.out.println(gestor.getNomes());
        gestor.removePrinter("Lab1");
        System.out.println(gestor.getNomes());

    }
}
