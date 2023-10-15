package Codigo;

public class ClienteVIP extends Cliente {
    private int pontosFidelidade;

    public ClienteVIP(String nome, String cpf, String senha, TipoConta tipo) {
        super(nome, cpf, senha, tipo);
        this.pontosFidelidade = 0;
    }

    public int getPontosFidelidade() {
        return pontosFidelidade;
    }

    public void setPontosFidelidade(int pontosFidelidade) {
        this.pontosFidelidade = pontosFidelidade;
    }
}
