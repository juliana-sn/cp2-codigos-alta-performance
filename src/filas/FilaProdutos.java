package filas;

import entidades.Produto;

public class FilaProdutos {

    static final int N = 4;
    public Produto[] produtos = new Produto[N];
    int ini;
    int fim;
    public int cont;

    public void init() {
        ini = 0;
        fim = 0;
        cont = 0;
    }

    public boolean isFull() {
        return (cont == N);
    }

    public boolean isEmpty() {
        return (cont == 0);
    }

    public void enqueue(Produto produto) {
        if (isFull()) {
            System.out.println("A fila está cheia!");
        } else {
            produtos[fim] = produto;
            fim = (fim + 1) % N;
            cont++;
        }
    }

    public Produto dequeue() {
        Produto aux = produtos[ini];
        ini = (ini + 1) % N;
        cont--;
        return aux;
    }

}
