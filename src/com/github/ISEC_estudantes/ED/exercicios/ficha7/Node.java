package com.github.ISEC_estudantes.ED.exercicios.ficha7;

public class Node<T extends Comparable<? super T>> {
    T data;
    Node<T> left, right;

    Node() {
    }

    Node(T data) {
        this.data = data;
    }

    public Node<T> getLeft() {
        return left;
    }

    public Node<T> getRight() {
        return right;
    }


    public T get() {
        return data;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

    public void addElement(T data) {
        if (this.data.compareTo(data) > 0)
            this.setRight(new Node<T>(data));
        else setRight(new Node<T>(data));
    }

    private void setData(T data) {
        this.data = data;
    }

    public void set(T data) {
        this.data = data;
    }
}
