package aplicacao;

import entidades.Encomenda;
import entidades.Produto;
import filas.FilaEncomendas;
import filas.FilaProdutos;

import java.io.*;
import java.util.Scanner;

public class Distribuidor {
    static FilaEncomendas filaEncomendas = new FilaEncomendas();
    static FilaProdutos filaProdutos = new FilaProdutos();
    static Scanner sc = new Scanner(System.in);

    public static void main(String args[]) throws IOException {
        filaEncomendas.init();
        geraFilaEncomendas(filaEncomendas);

        int op = 0;
        do {
            System.out.println("0 - Encerra o atendimento\n1 - Insere nova encomenda na fila de encomendas\n2 - Atende uma encomenda");
            op = sc.nextInt();

            switch (op) {
                case 0:
                    break;
                case 1:
                    inserirEncomenda();
                    break;
                case 2:
                    if (filaEncomendas.isEmpty()) {
                        System.out.println("Não há encomendas para serem atendidas");
                    } else {
                        atenderEncomenda();
                    }
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (op != 0);
        System.out.println("Atendimento finalizado");
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

        String arquivo = temp.getNomeArquivo();
        boolean check = geraFilaProdutos(filaProdutos, arquivo);

        if (check) {
            System.out.println("Atendimento do cliente " + temp.getClienteID() + " está iniciando");

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
                } else {
                    cont--;
                    i--;
                    precoTotal += aux.getPreco();
                }
            }
            System.out.println("Atendimento da encomenda foi finalizada com sucesso");
            System.out.println("Valor total da compra: R$" + String.format("%.2f", precoTotal) + "\n");
        }else{
            System.out.println("Arquivo de encomenda não presente");
        }
    }

    private static boolean geraFilaProdutos(FilaProdutos filaProd, String arquivo) throws IOException {
        boolean check = true;
        try{
            String caminhoArquivo = "C:\\Users\\Juliana\\OneDrive\\Área de Trabalho\\cp_codigos\\src\\arquivos\\" + arquivo;
            BufferedReader buffRead = new BufferedReader(new FileReader(caminhoArquivo));
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
                    filaProd.enqueue(produto);
                } else
                    break;
                linha = buffRead.readLine();
            }
            buffRead.close();
        }catch (FileNotFoundException e){
            System.out.println("Arquivo não encontrado: " + e.getMessage());
            check = false;
        }

        return check;
    }

    private static void geraFilaEncomendas(FilaEncomendas fila) {
        String caminhoDoArquivo = "C:\\Users\\Juliana\\OneDrive\\Área de Trabalho\\cp_codigos\\src\\arquivos\\ListaEncomendas.txt";
        try {
            File arquivo = new File(caminhoDoArquivo);

            Scanner leArq = new Scanner(arquivo);

            while (leArq.hasNextLine()) {
                String linha = leArq.nextLine();
                String[] partes = linha.split(",");
                Encomenda obj = new Encomenda(Integer.parseInt(partes[0]), partes[1]);
                fila.enqueue(obj);
            }
            leArq.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + e.getMessage());
        }
    }
}