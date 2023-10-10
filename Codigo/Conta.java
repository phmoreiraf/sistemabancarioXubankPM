package Codigo;

import java.util.*;

public abstract class Conta {
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

    public double getSaldo() {
        return saldo;
    }

    public void registrarTransacao(String transacao) {
        extrato.add(transacao);
    }

    public void depositar(double valor) {
        saldo += valor;
    }

    public void sacar(double valor) {
        if (saldo >= valor) {
            saldo -= valor;
        } else {
            System.out.println("Saldo insuficiente!");
        }
    }    

    public abstract void atualizarSaldo();
}



class ContaCorrente extends Conta {
    private static final double TAXA_MENSAL = 20.0;
    private static final double LIMITE_SAQUE_ESPECIAL = 200.0;

    public ContaCorrente(Cliente cliente) {
        super(cliente);
    }

    @Override
    public void sacar(double valor) {
        if (getSaldo() + LIMITE_SAQUE_ESPECIAL >= valor) {
            super.sacar(valor);
        } else {
            System.out.println("Saldo insuficiente!");
        }
    }

    @Override
     public void atualizarSaldo() {
         sacar(TAXA_MENSAL);
     }
}

class Poupanca extends Conta {
    private static final double RENDIMENTO_FIXO = 0.005;

    public Poupanca(Cliente cliente) {
        super(cliente);
    }

    public void renderJuros() {
        depositar(getSaldo() * RENDIMENTO_FIXO);
    }

      @Override
     public void atualizarSaldo() {
         depositar(getSaldo() * RENDIMENTO_FIXO);
     }
}

class RendaFixa extends Conta {
    private double rendimentoContratado;

    public RendaFixa(Cliente cliente, double rendimentoContratado) {
        super(cliente);
        this.rendimentoContratado = rendimentoContratado;
    }

    public void renderJuros() {
        depositar(getSaldo() * rendimentoContratado);
    }

       @Override
     public void atualizarSaldo() {
         depositar(getSaldo() * rendimentoContratado);
     }
}

class Investimento extends Conta {

    private Random random;

    public Investimento(Cliente cliente) {
        super(cliente);
        this.random = new Random();
    }

    // Implementação do método de rendimento diário
    public void renderRendimentoDiario() {
        // Gera um rendimento diário aleatório entre -0.5% e +0.5%
        double rendimentoDiario = (random.nextDouble() - 0.5) / 100.0;
        depositar(getSaldo() * rendimentoDiario);
    }

      public void atualizarSaldo() {
         // Gera um rendimento diário aleatório entre -0.5% e +0.5%
         double rendimentoDiario = (random.nextDouble() - 0.5) / 100.0;
         depositar(getSaldo() * rendimentoDiario);
     }
}