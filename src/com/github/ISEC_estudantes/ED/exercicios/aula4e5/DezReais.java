package com.github.ISEC_estudantes.ED.exercicios.aula4e5;

public class DezReais /*implements Iterable<Double>*/{
    protected final int CAPACITY = 10;
    protected Double[] numeros = new Double[CAPACITY];
    protected int size = 0;
    protected int modcounter = 0;




    ;

    public boolean add(double numero) {
        if (size >= CAPACITY) {
            return false;
        }
        numeros[size++] = numero;
        ++modcounter;
        return true;
    }

    public double get(int pos) throws ArrayIndexOutOfBoundsException {
        if (pos >= size || pos < 0) {
            throw new ArrayIndexOutOfBoundsException("The index is out of boundaries.");
        }
        return numeros[pos];
    }

    public int size() {
        return size;
    }
    private String elementsToString(){
        String elements = "[ ";
        for(int i = 0 ; i < size(); i++)
            elements+=i+", ";
        StringBuffer sb= new StringBuffer(elements);
        sb.deleteCharAt(sb.length()-2);

        elements = sb.toString();
        elements += "]";
        return elements;

    }
    @Override
    public String toString() {
        return "DezReais{" +
                "numeros=" + elementsToString() +
                ", size=" + size +
                '}';
    }

    public int getModcounter() {
        return modcounter;
    }

//    @Override
//    public Iterator<Double> iterator() {
//
//    }
//
//    @Override
//    public void forEach(Consumer<? super Double> action) {
//
//    }
//
//    @Override
//    public Spliterator<Double> spliterator() {
//        return null;
//    }
}
