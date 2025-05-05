package entidades;

public class Encomenda {
    private int clienteID;
    private String nomeArquivo;

    public Encomenda(int clienteID, String nomeArquivo) {
        this.clienteID = clienteID;
        this.nomeArquivo = nomeArquivo;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public int getClienteID() {
        return clienteID;
    }
}
