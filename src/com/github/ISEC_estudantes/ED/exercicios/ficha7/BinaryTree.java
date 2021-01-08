package com.github.ISEC_estudantes.ED.exercicios.ficha7;

import java.util.*;

public class BinaryTree<T extends Comparable<? super T>> {
    private Node root, nullnode; //nullnode e o no sentinela
    private Comparator<T> comp;

    BinaryTree() {
        comp = (o1, o2) -> o1.compareTo(o2);
    }

    BinaryTree(Comparator<T> comp) {
        this.comp = comp;
        nullnode = new Node<>();
        nullnode.setLeft(nullnode);
        nullnode.setRight(nullnode);
        root = nullnode;
    }

    public void insere(T t) {
        Node n = new Node(t);
        root = insere(root, n);
    }

    private Node<T> insere(Node<T> root, Node<T> newNode) {
        if (root == nullnode) return newNode;
        int cmp = comp.compare(root.get(), newNode.get());
        if (cmp == 0) throw new RuntimeException("Valor duplicado.");
        if (cmp < 0) root.setRight(newNode);
        else root.setLeft(newNode);
        return root;
    }

    public boolean contem(T d) {
        return contemDado(root, d);
    }

    private boolean contemDado(Node<T> n, T data) {
        if (n == nullnode) return false;
        int c = comp.compare(data, n.get());
        if (c == 0)
            return true;
        if (c < 0)
            return contemDado(n.getLeft(), data);
        return contemDado(n.getRight(), data);
    }

    public int profundidadeDe(T d) {
        return profundidadeDe(root, d);
    }

    private int profundidadeDe(Node<T> n, T data) {
        int d;
        if (n == nullnode) return 0;
        if (data.compareTo(n.get()) == 0) return 1;
        d = profundidadeDe(n.getLeft(), data);
        if (d > 0) return d + 1;
        d = profundidadeDe(n.getRight(), data);
        if (d > 0) return d + 1;
        return 0;
    }

    public void imprimeOrdem() {
        List<T> inOrderList = new ArrayList<>();
        ordem(root, inOrderList);
        mostraLista(inOrderList);
    }

    private void ordem(Node<T> root, List<T> storageList) {
        if (root == nullnode) return;
        ordem(root.getLeft(), storageList);
        storageList.add(root.get());
        ordem(root.getRight(), storageList);
    }

    public void mostraLista(List<T> list) {
        for (T item : list)
            System.out.println(item);
    }

    public int profundidade() {
        return profundidade(root);
    }

    private int profundidade(Node<T> n) {
        if (n == nullnode) return 0;
        int dl = profundidade(n.getLeft());
        int dr = profundidade(n.getRight());
        return dl > dr ? dl + 1 : dr + 1;
    }

    public int tamanho() {
        return tamanho(root);
    }

    private int tamanho(Node<T> root) {
        if (root == nullnode) return 0;
        return 1 + tamanho(root.getLeft()) + tamanho(root.getRight());
    }

    public void imprimeNiveis() {
        List<Node<T>> lst = new LinkedList<>();

        lst.add(root);//acrescenta nodo a lista ... a partir de agora terminamos qd lista estiver vazia
        System.out.println("=====conteudo da arvore======");
        while (!lst.isEmpty()) {
            Node<T> n = lst.remove(0);
            System.out.println(n.get() + " ");//mostra o valor
            if (n.getLeft() == nullnode) lst.add(n.getLeft());//acresenta descendentes a lista
            if (n.getRight() != nullnode) lst.add(n.getRight());
        }
    }

    public void imprimeNivel(Node t, List lst, int l) {
        T item;
        System.out.println("Nivel " + l);
        var li = lst.listIterator();
        while (li.hasNext()) {
            item = (T) li.next();
            if (profundidadeDe(root, item) == l) System.out.println("[" + l + "] " + item);
        }
    }

    public void imprimePorNiveis() {
        int j, l = profundidade(root);
        List<T> lst = new ArrayList<T>();
        ordem(root, lst);
        System.out.println("=====conteudo da arvore=====");
        for (j = 1; j <= l; j++)
            imprimeNivel(root, lst, j);
        System.out.println("================");
    }

    public void remove(T value) {
        root = remove(root, value);
    }

    private Node<T> remove(Node<T> root, T value) {
        if (root == nullnode)
            return nullnode;
        int cmp = comp.compare(value, root.get());
        if (cmp > 0) {
            root.setLeft(remove(root.getLeft(), value));
            return root;
        }
        if (cmp < 0) {
            root.setRight(remove(root.getRight(), value));
            return root;
        }

        //cmp == 0 apagar aqui
        if (root.getLeft() == nullnode && root.getRight() == nullnode)
            return nullnode;//nao tem filhos
        if(root.getLeft() == nullnode)return root.getRight();// so tem filho direito
        if(root.getRight() == nullnode) return root.getLeft();// so tem filho esquerdo
        System.out.println("Tem 2 filhos, o"+root.get());//sechegamso aqui tem 2 filhos
        Node<T> min = minimumElement(root.getRight());//encontrar minimo na subarvore direita para substituir pai
        root.set(min.get());
        root.setRight(remove(root.getRight(), min.get()));
        return root;
    }

    public Node<T> minimumElement(Node<T> root) {
        if (root.getLeft() == nullnode)
            return root;
        return minimumElement(root.getLeft());
    }


    public static void main(String[] args) {
        BinaryTree<Integer> it = new BinaryTree<>();
        ArrayList<Integer> al = new ArrayList<>();
        int i, a, b, len = 1000000, hl;
        Integer temp;
        Random r = new Random();
        for (i = 0; i < len; ++i)
            al.add(r.nextInt(len));
        hl = len / 2;

        for (i = 0; i < hl; i++) {
            a = r.nextInt(len);
            b = r.nextInt(len);
            temp = al.get(a);
            al.set(a, al.get(b));
            al.set(b, temp);
        }
        for (Integer integer : al) it.insere(integer);
        System.out.println("A arvore tem " + it.tamanho() + " elementos, em " + it.profundidade() + " niveis.");
        System.out.println("Devia ter estes niveis: " + Math.log(len) / Math.log(2));
    }
}


