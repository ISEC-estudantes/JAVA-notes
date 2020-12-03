package com.github.ISEC_estudantes.ED.exercicios.ficha2;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;


/// Aula 2, sobre pesquisa binaria
public class PesquisaBinaria {


    static int[] criaArrayCom(
            int valor, //numero que vai existir na tabela
            int dimensao, // dimensao da tabela
            boolean diferentes) { // se for true , a tabela nao tera repetidos
        int[] m = new int[dimensao];
        if (diferentes) { // preencher de forma sistematica p/ garantir diferentes
            for (int i = 0; i < dimensao; i++)
                m[i] = i * 10;
            if ((valor % 10 != 0) || (0 > valor) || (valor > (dimensao - 1) * 10))
                m[0] = valor; // garantir que valor existe na tabela
        } else {
            Random r = new Random();
            int gama = (Math.abs(valor) < 10) ? 10 : Math.abs(valor);
            for (int i = 0; i < dimensao; i++)
                m[i] = r.nextInt(gama * 4) - gama * 2;
            m[0] = valor;
        }
        Arrays.sort(m);
        return m;
    }

    //ex1
    public static boolean binRecursivo(int[] tab, int chave, int start, int end) {
        int meio = (start + end) / 2;
        System.out.println("[binRecursivo]: meio = " + meio);

        if (tab[meio] > chave)
            end = meio - 1;
        else
            start = meio + 1;
        if (end < start) return false;
        // int [] newt = Arrays.copyOfRange(tab, start , end); // com copia de dados
        boolean result = binRecursivo(tab, chave, start, end);
        System.out.println("[binRecursivo]: Terminou meio= " + meio);
        return result;
    }

    public static void test_binRecursivo(int[] tab, int chave){
        System.out.println("vai se procurar no "+ Arrays.toString(tab) + " a chave "+ chave );
        System.out.println("Resultado: "+binRecursivo(tab, chave, 0, tab.length-1));
    }

    //ex2
    static boolean binIterativo(int[] tab, int chave) {
        int linf = 0, lsup = tab.length - 1;
        int meio = lsup / 2;
        do {
            if (tab[meio] == chave) {
                return true;
            }
            if (tab[meio] < chave)
                linf = meio + 1;
            else
                lsup = meio - 1;
            meio = (linf + lsup) / 2;

        } while (linf < lsup);

        return tab[meio] == chave;
    }

    //ex4 pesquisa binaria que returna o index em que o chave esta, se nao existir vai dizer com um index negativo onde deve estar, sendo que se tiver de estar no index 0 returna -1 e continua assim
    static int binCmp(int[] tab, int chave) {
        int linf = 0, lsup = tab.length - 1;
        int meio = lsup / 2;
        do {
            if (tab[meio] == chave) {
                return meio;
            }
            if (tab[meio] < chave)
                linf = meio + 1;
            else
                lsup = meio - 1;
            meio = (linf + lsup) / 2;

        } while (linf < lsup);

        if (tab[meio] == chave) return meio;

        if (tab[meio] < chave) return -meio - 2; //chave deve ir para direita do meio
        return -meio - 1; //chave de ve ir para a esquerda do meio
    }


    // exercicio 5 b
    //Construa um método que recebe um array ordenado de inteiros
    public static double percentagem(int[] m, int valor) {
        int pos = binCmp(m, valor);
        //se o valor existe
        if (pos >= 0) return (double) pos / m.length;

        //se o valor n�o existe
        int posInsert = -pos - 1;
        //o valor na pos de inserç�o � menor e deve contar
        return posInsert / (double) m.length;
    }

    //ex6
    public static int contaIntervalo(int[] tab, int min, int max) {
        int j, k, cont = 0;
        // for (j = 0 ; j< tab.length && tab[j] <= max; j++) //solucao linear
        //      if (tab[j]>= min && tab[j] <=max) count++;
        //return count;
        j = binCmp(tab, min); // Solucao logaritmica
        k = binCmp(tab, max); // O(2.log(n)) continua a ser logaritmico
        if (j < 0) j = -j - 1;
        if (k < 0) k = -k - 1;
        else k++;
        return k - j;

    }


    //ex7
    //construa um metodo que recebe um array ordenado de inteiros, e um valor. O metodo deve indicar se este vai se enconra repetido no array.
    //Exemplo:
    //Array: {3,3,7,12,12,15}
    // Valor = 15 resultado = false
    // VAlor = 14 resultado = false
    // valor = 12 resultado = true
    //valor = 3 resultado true

    public static boolean testaRepetidos(int[] tab, int chave) {
        int j =  binCmp(tab, chave); //pesquisaBinaria
        if (j < 0) return false; // Numero nao existe

        if (j == 0) // limite inferior da tabela, so se pode comparar o seguinte
            if (tab[1] != chave)
                return false;
            else return true;
        if (j == tab.length - 1) // limite superior da tabela, s� se pode comparar o seguinte
            if (tab[j - 1] != chave)
                return false;
            else return true;

        if (tab[j] == tab[j - 1] || (tab[j] == tab[j + 1])) return true; // ver vizinhos
        return false;
    }


    //ex8
    // Contrua um metodo que recebe por parametro um array ordenado de inteiros, nao repetidos, bem como um inteiro z, e devolve o maior elemento do array menor do que z (ou z se esse elemento nao existir)
    // exemplo:
    //array: {3,7,12,15}
    //valor = 15 resultado = 12
    //val= 14 res = 12
    //val 3 ; res 3
    //val 1 ; res 1
    // val 100 ; res 15

    //complexidade logaritima, pois o binCmp e' logaritima e a procuraMenor e' pois
    static public int procuraMenor(int[] tab, int chave) {
        int i, pos = binCmp(tab, chave); // vai buscar o index do valor
        if (pos == -1 || pos == 0) return chave;// se estiver no inicio returna o mesmo que recebeu
        if (pos < 0) pos = -pos - 1; //faz setup da posicao se for negativo
        return tab[pos - 1];// returna o anterior
    }

    //ex9
    //considere um metodo que recebe um array de inteiros no qual os numeros estao dispostos da seguinte forma: todos os nmeros negativos se encontram em posicoes maioores do que os numeros positivos e todos os numero positivos e negativos se encontram ordenados entre si.
    //Exemplo: {}

    public static int pesquisaBinariaNeg(int[] tab, int chave) {
        int linf = 0, lsup = tab.length - 1, meio = tab.length / 2;
        do {
            if (tab[meio] == chave) return meio;
            if (chave * tab[meio] >= 0) //meio e chave tem mesmo sinal
                if (tab[meio]< chave) linf = meio + 1;
                else lsup = meio - 1;
            else//meio e chave tem sinais contrairios
                if (tab[meio] < chave) lsup = meio - 1;
                else linf = meio + 1;
            meio = (linf + lsup) / 2;

        } while (linf < lsup);
        if (tab[meio] == chave) return meio;
        return -1;
    }


    static public void main(String[] args) {
//        var valores = criaArrayCom(10, 10, true);
//        Arrays.stream(valores).forEach(System.out::println);
//        System.out.println(binCmp(valores, 2));

        test_binRecursivo(criaArrayCom(2,10,true), 20);
    }
}
