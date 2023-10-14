package Codigo;

public class ClienteGold extends Cliente {
    private int pontosFidelidade;

    public ClienteGold(String nome, String cpf, String senha) {
        super(nome, cpf, senha, "Gold");
        this.pontosFidelidade = 0;
    }

    public int getPontosFidelidade() {
        return pontosFidelidade;
    }

    public void setPontosFidelidade(int pontosFidelidade) {
        this.pontosFidelidade = pontosFidelidade;
    }


}
