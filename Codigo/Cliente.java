package Codigo;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String nome;
    private String cpf;
    private String senha;
    private String tipo;
    private List<Conta> contas;
    private int pontosFidelidade;

    public Cliente(String nome, String cpf, String senha, String tipo) {
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
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

    public String getTipo() {
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

    // Método para atualizar os pontos de fidelidade com base no tipo de cliente e saldo total
    public void atualizarPontosFidelidade() {
        for (Conta conta : contas) {
            double saldo = conta.getSaldo();
            if ("Gold".equals(tipo) && saldo >= 1000) {
                pontosFidelidade += 10;
            } else if ("VIP".equals(tipo) && saldo >= 2000) {
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
