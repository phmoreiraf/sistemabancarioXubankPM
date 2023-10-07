package Codigo;

import java.util.*;

abstract class Conta {
    protected double saldo;
    protected Cliente cliente;
    private List<String> extrato;

    public Conta(Cliente cliente) {
        this.cliente = cliente;
        this.saldo = 0.0;
        this.extrato = new ArrayList<>();
    }

    public double getSaldo() {
        return saldo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<String> getExtrato() {
        return extrato;
    }

    public void registrarTransacao(String transacao) {
        extrato.add(transacao);
    }

    public abstract void atualizarSaldo();

    public boolean sacar(double valor) {
        if (saldo >= valor) {
            saldo -= valor;
            registrarTransacao("Saque: " + valor);
            return true;
        }
        return false; // Saldo insuficiente para saque.
    }

    public void depositar(double valor) {
        saldo += valor;
        registrarTransacao("Depósito: " + valor);
    }
}

class ContaCorrente extends Conta {
    private static final double TAXA_MENSAL = 20.0;
    private static final double LIMITE_SAQUE = 200.0;

    public ContaCorrente(Cliente cliente) {
        super(cliente);
    }

    @Override
    public void atualizarSaldo() {
        saldo -= TAXA_MENSAL;
        registrarTransacao("Taxa mensal: " + TAXA_MENSAL);
    }

    public void descontarTaxaMensal() {
        saldo -= TAXA_MENSAL;
        registrarTransacao("Taxa mensal: " + TAXA_MENSAL);
    }

    public boolean sacar(double valor) {
        if (saldo - valor >= -LIMITE_SAQUE) {
            saldo -= valor;
            registrarTransacao("Saque: " + valor);
            return true;
        }
        return false; // Saldo insuficiente ou excedeu o limite de saque
    }
}

class ContaPoupanca extends Conta {
    private static final double RENDIMENTO_MENSAL = 0.005;

    public ContaPoupanca(Cliente cliente) {
        super(cliente);
    }

    @Override
    public void atualizarSaldo() {
        double rendimento = saldo * RENDIMENTO_MENSAL;
        saldo += rendimento;
        registrarTransacao("Rendimento mensal: " + rendimento);
    }

    public void aplicarRendimentoMensal() {
        double rendimento = saldo * RENDIMENTO_MENSAL;
        saldo += rendimento;
        registrarTransacao("Rendimento mensal: " + rendimento);
    }
}

