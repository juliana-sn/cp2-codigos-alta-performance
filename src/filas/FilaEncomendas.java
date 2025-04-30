package filas;

import modelos.Encomenda;

import static javax.swing.JOptionPane.showMessageDialog;

public class FilaEncomendas {
    static final int N = 4;
    public Encomenda[] encomendas = new Encomenda[N];
    int ini, fim, cont;

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

    public void enqueue(Encomenda encomenda) {
        if (isFull()) {
            System.out.println("A fila está cheia!");
        } else {
            encomendas[fim] = encomenda;
            fim = (fim + 1) % N;
            cont++;
        }
    }


    public Encomenda dequeue() {
        Encomenda aux = encomendas[ini];
        ini = (ini + 1) % N;
        cont--;
        return aux;
    }

}
