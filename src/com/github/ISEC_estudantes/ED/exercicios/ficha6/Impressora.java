package com.github.ISEC_estudantes.ED.exercicios.ficha6;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Impressora {
    String nome, marca, modelo, driver, versao;
    Integer porto;



    PriorityQueue<Trabalho> pq = new PriorityQueue<Trabalho>(Comparator.comparingInt(Trabalho::getPaginas));

    public boolean temProximoTrabalho() {
        return !pq.isEmpty();
    }

    public Trabalho proximoTrabalho() {
        return pq.poll();
    }
    public Trabalho peekProximoTrabalho(){
        return pq.peek();
    }

    public void enviaTrabalho(Trabalho t) {
        pq.add(t);
    }

    public int getNumeroTrabalhos() {
        return pq.size();
    }

    public Impressora(String nome, String marca, String modelo, String driver, String versao, Integer porto) {
        this.nome = nome;
        this.marca = marca;
        this.modelo = modelo;
        this.driver = driver;
        this.versao = versao;
        this.porto = porto;
    }

    public String getNome() {
        return nome;
    }

    public String getMarca() {
        return marca;
    }

    public void show() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return "Impressora{" +
                "nome='" + nome + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", driver='" + driver + '\'' +
                ", versao='" + versao + '\'' +
                ", porto=" + porto +
                '}';
    }
}
