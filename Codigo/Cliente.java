package Codigo;

import java.util.*;

public abstract class Cliente {
    private String nome;
    private String cpf;
    private TipoConta tipo;
    private int pontosFidelidade;
    private List<Conta> contas;

    public Cliente(String nome, String cpf, String senha, TipoConta tipo) {
        this.nome = nome;
        this.cpf = cpf;
        this.tipo = tipo;
        this.contas = new ArrayList<>();
        this.pontosFidelidade = 0;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public TipoConta getTipo() {
        return tipo;
    }

    public int getPontosFidelidade() {
        return pontosFidelidade;
    }

    public List<Conta> getContas() {
        return contas;
    }

    public void adicionarConta(Conta conta) {
        contas.add(conta);
    }

    public void consultarSaldo() {
        for (Conta conta : contas) {
            System.out.println("Saldo da conta: " + conta.getSaldo());
        }
    }

    // TIPOS CLIENTES

    class ClienteGold extends Cliente {
        private int pontosFidelidade;

        public ClienteGold(String nome, String cpf, String senha, TipoConta tipo) {
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

    class ClienteRegular extends Cliente {
        public ClienteRegular(String nome, String cpf, String senha, TipoConta tipo) {
            super(nome, cpf, senha, tipo);
        }
    }

    class ClienteVIP extends Cliente {
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

    public void depositar(double valor, int indiceConta) {
        if (indiceConta >= 0 && indiceConta < contas.size()) {
            contas.get(indiceConta).depositar(valor);
        } else {
            System.out.println("Índice de conta inválido!");
        }
    }

    public void sacar(double valor, int indiceConta) {
        if (indiceConta >= 0 && indiceConta < contas.size()) {
            contas.get(indiceConta).sacar(valor);
        } else {
            System.out.println("Índice de conta inválido!");
        }
    }

    public void transferir(double valor, int indiceContaOrigem, int indiceContaDestino) {
        if (indiceContaOrigem >= 0 && indiceContaOrigem < contas.size() &&
                indiceContaDestino >= 0 && indiceContaDestino < contas.size()) {
            Conta contaOrigem = contas.get(indiceContaOrigem);
            Conta contaDestino = contas.get(indiceContaDestino);
            if (contaOrigem.getSaldo() >= valor) {
                contaOrigem.sacar(valor);
                contaDestino.depositar(valor);
            } else {
                System.out.println("Saldo insuficiente para transferência!");
            }
        } else {
            System.out.println("Índice de conta inválido!");
        }
    }

    // Método para atualizar os pontos de fidelidade com base no tipo de cliente e
    // saldo total
    public void atualizarPontosFidelidade() {
        for (Conta conta : contas) {
            double saldo = conta.getSaldo();
            if (tipo == TipoConta.GOLD && saldo >= 1000) {
                pontosFidelidade += 10;
            } else if (tipo == TipoConta.VIP && saldo >= 2000) {
                pontosFidelidade += 30;
            }
        }
    }

    // Método para trocar pontos de fidelidade por recompensas
    public void trocarPontosPorRecompensas() {
        int pontosNecessarios = 100; // Exemplo de pontos necessários para trocar por uma recompensa
        if (pontosFidelidade >= pontosNecessarios) {
            // Lógica para oferecer recompensas
            // Reduza os pontos de fidelidade após a troca
            pontosFidelidade -= pontosNecessarios;
            System.out.println("Recompensa concedida!");
        } else {
            System.out.println("Pontos de fidelidade insuficientes para trocar por recompensas.");
        }
    }
}