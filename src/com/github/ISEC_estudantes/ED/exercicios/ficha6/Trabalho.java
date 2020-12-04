package com.github.ISEC_estudantes.ED.exercicios.ficha6;

public class Trabalho {
    String nome;
    int paginas;

    public void show() {
        System.out.println(toString());
    }

    public int getPaginas() {
        return paginas;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "Trabalho{" +
                "nome='" + nome + '\'' +
                ", paginas=" + paginas +
                '}';
    }

    public Trabalho(String nome, int paginas) {
        this.nome = nome;
        this.paginas = paginas;
    }
}
