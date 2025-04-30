package modelos;

public class Produto {
    private String codigo;
    private String descricao;
    private double preco;
    private String localizacao;

    public Produto(String codigo, String descricao, double preco, String localizacao) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.preco = preco;
        this.localizacao = localizacao;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }
}
