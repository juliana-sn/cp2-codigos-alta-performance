package aplicacao;

import modelos.Encomenda;
import modelos.Produto;
import filas.FilaEncomendas;
import filas.FilaProdutos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    static FilaEncomendas filaEncomendas = new FilaEncomendas();
    static FilaProdutos filaProdutos = new FilaProdutos();
    static Scanner sc = new Scanner(System.in);

    public static void main(String args[]) throws IOException {

        filaEncomendas.init();
        int op = 0;
        do {
            System.out.println("0 - Encerra o atendimento\n1 - Insere nova encomenda na fila de encomendas\n2 - Atende uma encomenda");
            op = sc.nextInt();

            if (op < 0 || op > 2) {
                System.out.println("Opção inválida!");
            }

            switch (op) {
                case 1:
                    inserirEncomenda();
                    break;
                case 2:
                    if (filaEncomendas.isEmpty()) {
                        System.out.println("A fila está vazia!");
                    } else {
                        atenderEncomenda();
                    }
                    break;
            }
        } while (op != 0);
    }

    private static void inserirEncomenda() {
        int id;
        String nomeArquivo;
        System.out.print("Informe o ID do cliente: ");
        id = sc.nextInt();
        System.out.print("Nome do arquivo de produtos encomendados: ");
        nomeArquivo = sc.next();
        Encomenda encomenda = new Encomenda(id, nomeArquivo);
        filaEncomendas.enqueue(encomenda);
    }
    private static void atenderEncomenda() throws IOException {
        filaProdutos.init();
        Encomenda temp = filaEncomendas.dequeue();
        System.out.println("Atendimento do cliente " + temp.getClienteID() + " está iniciando");

        String arquivo = temp.getNomeArquivo();

        geraEncomenda(arquivo);

        int cont = filaProdutos.cont;

        double precoTotal = 0;
        for (int i = 0; i < cont; i++) {
            int op;
            Produto aux = filaProdutos.dequeue();
            System.out.println("Produto [codigo= " + aux.getCodigo() + ", descricao= " + aux.getDescricao() + ", preco= " + aux.getPreco() + ", localizacao= " + aux.getLocalizacao() + "]");
            System.out.println("O produto foi encontrado na prateira? (1-sim):");
            op = sc.nextInt();
            if (op != 1) {
                filaProdutos.enqueue(aux);
                cont++;
                //System.out.println("Voltar depois para colocar no carrinho");//
            } else {
                    cont--;
                    precoTotal += aux.getPreco();
            }
        }

            System.out.println("Atendimento da encomenda foi finalizada com sucesso");
            System.out.println("Valor total da compra: R$" + String.format("%.2f", precoTotal));
        }

    private static void geraEncomenda(String arquivo) throws IOException {
        String path = "C:\\Users\\Juliana\\OneDrive\\Área de Trabalho\\cp_codigos\\src\\arquivos\\" + arquivo;
        BufferedReader buffRead = new BufferedReader(new FileReader(path));
        String linha = "";
        linha = buffRead.readLine();
        String texto = "";

        while (true) {
            if (linha != null) {
                texto = linha;
                String[] campos = texto.split(",");

                String codigo = campos[0];
                String descricao = campos[1];
                double preco = Double.parseDouble(campos[2]);
                String localizacao = campos[3];

                Produto produto = new Produto(codigo, descricao, preco, localizacao);
                filaProdutos.enqueue(produto);
            } else
                break;
            linha = buffRead.readLine();
        }
        buffRead.close();
    }
}