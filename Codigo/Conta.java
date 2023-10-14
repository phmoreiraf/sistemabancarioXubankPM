package Codigo;

import java.util.ArrayList;
import java.util.List;

public abstract class Conta implements ContaInterface {
    private double saldo;
    private Cliente cliente;
    private List<String> extrato;

    public Conta(Cliente cliente) {
        this.cliente = cliente;
        this.saldo = 0.0;
        this.extrato = new ArrayList<>();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<String> getExtrato() {
        return extrato;
    }

    @Override
    public double getSaldo() {
        return saldo;
    }

    @Override
    public void depositar(double valor) {
        saldo += valor;
        registrarTransacao("Depósito: +" + valor);
    }

    @Override
    public void sacar(double valor) {
        if (saldo >= valor) {
            saldo -= valor;
            registrarTransacao("Saque: -" + valor);
        } else {
            System.out.println("Saldo insuficiente!");
        }
    }

    @Override
    public void registrarTransacao(String transacao) {
        extrato.add(transacao);
    }

    public abstract void atualizarSaldo();
}
