package Codigo;

import java.util.*;

public abstract class Cliente {
    private String nome;
    private String cpf;
    private String senha;
    private List<Conta> contas;

     public Cliente(String nome, String cpf, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.contas = new ArrayList<>();
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setContas(List<Conta> contas) {
        this.contas = contas;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getSenha() {
        return senha;
    }

    public List<Conta> getContas() {
        return contas;
    }

    public void adicionarConta(Conta conta) {
        this.contas.add(conta);
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
}

  class ClienteReal extends Cliente {
        public ClienteReal(String nome, String cpf, String senha) {
            super(nome, cpf, senha);
        }
    }
