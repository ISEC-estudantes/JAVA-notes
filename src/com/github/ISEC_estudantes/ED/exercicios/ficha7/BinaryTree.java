package com.github.ISEC_estudantes.ED.exercicios.ficha7;

import java.util.*;

public class BinaryTree<T extends Comparable<? super T>> implements Comparable<Node<T>>, Iterable {
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
        if (root.getLeft() == nullnode) return root.getRight();// so tem filho direito
        if (root.getRight() == nullnode) return root.getLeft();// so tem filho esquerdo
        System.out.println("Tem 2 filhos, o" + root.get());//sechegamso aqui tem 2 filhos
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


    //metodos da segunda parte da aula
    public void removeNodosComUmDescendente() {
        root = removeNodosComUmDescendente(root);
    }

    private Node<T> removeNodosComUmDescendente(Node<T> n) {
        if (n == nullnode) return nullnode;
        if (n.getLeft() == nullnode && n.getRight() == nullnode) return n;//arvore com 0 descendentes
        if (n.getLeft() != nullnode && n.getRight() != nullnode) {//arvore com 2 descendentes
            n.setRight(removeNodosComUmDescendente(n.getRight()));
            n.setLeft(removeNodosComUmDescendente(n.getLeft()));
            return n;
        }
        if (n.getLeft() == nullnode) return removeNodosComUmDescendente(n.getRight()); //só desc direito
        return removeNodosComUmDescendente(n.getLeft());// só desc esquerdo
    }

    public void removeFolhas() {
        root = removeFolhas(root);
    }

    private Node removeFolhas(Node<T> n) {
        if (n == nullnode) return nullnode;
        if (n.getLeft() == nullnode && n.getRight() == nullnode) return nullnode;//arvore com 0 descendentes
        if (n.getRight() != nullnode)
            n.setRight(removeFolhas(n.getRight()));
        if (n.getLeft() != nullnode)
            n.setLeft(removeFolhas(n.getLeft()));
        return n;
    }

    public T sucessor(T valor) {
        return ((Node<T>) sucessor(root, valor)).get();
    }

    private Node<T> sucessor(Node<T> n, T valor) {
        T sucessor, aux;
        if (n == nullnode) return null;
        int cmp = valor.compareTo(n.getData());
        if (cmp < 0) {//se valor < raiz a raiz pode ser, ou sucessor pode estar à esquerda
            //Se não existir sucessor à esquerda, então é a raiz o sucessor
            Node<T> sucessorMenor = sucessor(n.getLeft(), valor);
            if (sucessorMenor == nullnode) return n;
            else return sucessorMenor;
        }
        //if (cmp>=0) -- se valor >= raiz então o sucessor, a existir, encontra-se na sub-àrvore direita
        return sucessor(n.getRight(), valor);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BinaryTree<T> that = (BinaryTree<T>) o;
        if (root.equals(that.root) && nullnode.equals(that.nullnode) && comp.equals(that.comp)) {
            return equals(this.root, that.root);
        }
        return false;
    }

    private boolean equals(Node<T> n, Node<T> thatN) {
        if (n.equals(thatN)) return false;
        if (n.getLeft() != nullnode) {
            if (!equals(n.getLeft(), thatN.getLeft()))
                return false;
        }
        if (n.getRight() != nullnode) {
            if (!equals(n.getRight(), thatN.getRight()))
                return false;
        }
        return true;
    }


    @Override
    public int hashCode() {
        return Objects.hash(root, nullnode, comp);
    }

    public static void main(String[] args) {
        BinaryTree<Integer> it = new BinaryTree<>();
        ArrayList<Integer> al = new ArrayList<>();
        int i, a, b, len = 10000, hl;
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

    @Override
    public int compareTo(Node<T> o) {
        if (compareTo(root, o))
            return 0;
        return 1;
    }

    boolean compareTo(Node<T> n1, Node<T> n2) {
        if (n1 == nullnode && n2 == nullnode) return true;//ambas vazias
        if (n1 == nullnode || n2 == nullnode) return false;//umavazia outra não
        int cmp = n1.getData().compareTo(n2.get());
        //Devolve true se valores sào iguais e sub-árvores esq e dir também
        return cmp == 0 && compareTo(n1.getLeft(), n2.getLeft()) &&
                compareTo(n1.getRight(), n2.getRight());
    }

    @Override
    public Iterator<Node<T>> iterator() {
        return new Iterator<Node<T>>() {//TreeIterator
            List<Node> pathToRoot = definePathToRoot();

            private List definePathToRoot() {
                pathToRoot = new ArrayList<Node>();
                addPathToMinFrom(root);
                return pathToRoot;
            }



            public void addPathToMinFrom(Node tmp) {
                while (tmp != null) {
                    pathToRoot.add(tmp);
                    tmp = tmp.left;
                }
            }

            public void TreeIterator(Node root) {
                addPathToMinFrom(root);
            }

            private void advanceIterator() {
                Node current = pathToRoot.remove(pathToRoot.size() - 1);
                if (current.right != null) addPathToMinFrom(current.right);
            }

            ;

            @Override
            public boolean hasNext() {
                return pathToRoot.isEmpty();
            }

            private Node getNext() {
                return pathToRoot.get(pathToRoot.size() - 1);
            }


            @Override
            public Node next() {
                Node ret = getNext();
                advanceIterator();
                return ret;
            }
        };
    }
}


