package Codigo;

import java.util.ArrayList;
import java.util.List;

enum TipoConta {
    REGULAR,
    GOLD,
    VIP
}

interface ContaInterface {
    void depositar(double valor);

    void sacar(double valor);

    void registrarTransacao(String transacao);

    void atualizarSaldo();

    double getSaldo();

    void setSaldo(double saldo);
}

public abstract class Conta implements ContaInterface {
    public static RendaFixa RendaFixa;
    private double saldo;
    private Cliente cliente;
    private List<String> extrato;

    public Conta(Cliente cliente) {
        this.cliente = cliente;
        this.saldo = 0.0;
        this.extrato = new ArrayList<>();
    }

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
=======
>>>>>>> parent of 363e7f4 (refatoração do código)
=======
>>>>>>> parent of 363e7f4 (refatoração do código)
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

<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> parent of 363e7f4 (refatoração do código)
=======
>>>>>>> parent of 363e7f4 (refatoração do código)
=======
>>>>>>> parent of 363e7f4 (refatoração do código)
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
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD

    public abstract void atualizarSaldo();

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
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    class ContaCorrente extends Conta {

        private static final double TAXA_MENSAL = 20.0;
        private static final double LIMITE_ESPECIAL = 200.0;

        public ContaCorrente(Cliente cliente) {
            super(cliente);
        }

        @Override
        public void atualizarSaldo() {
            double saldoAnterior = getSaldo();
            double novoSaldo = saldoAnterior - TAXA_MENSAL;
            if (novoSaldo < -LIMITE_ESPECIAL) {
                novoSaldo = -LIMITE_ESPECIAL;
            }
            setSaldo(novoSaldo);
            registrarTransacao("Taxa mensal: -" + TAXA_MENSAL);
        }
    }

 class Investimento extends Conta {

        private static final double TAXA_IMPOSTO = 0.15;

        public Investimento(Cliente cliente) {
            super(cliente);
        }

        @Override
        public void atualizarSaldo() {
            // Implementação do cálculo do saldo com rendimento diário (positivo ou
            // negativo)
            double saldoAnterior = getSaldo();
            double rendimentoDiario = Math.random() * 0.02 - 0.01; // Rendimento aleatório entre -1% e +1%
            double rendimento = saldoAnterior * rendimentoDiario;
            double novoSaldo = saldoAnterior + rendimento;
            setSaldo(novoSaldo);
            registrarTransacao("Rendimento diário: +" + rendimento);
        }

        @Override
        public void sacar(double valor) {
            double saldoAnterior = getSaldo();
            if (valor > saldoAnterior) {
                System.out.println("Saldo insuficiente para saque!");
            } else {
                double imposto = valor * TAXA_IMPOSTO;
                double novoSaldo = saldoAnterior - valor - imposto;
                setSaldo(novoSaldo);
                registrarTransacao("Saque: -" + valor + ", Imposto: -" + imposto);
            }
        }
    }

    class Poupanca extends Conta {

        private static final double RENDIMENTO_MENSAL = 0.005;
    
        public Poupanca(Cliente cliente) {
            super(cliente);
        }
    
        @Override
        public void atualizarSaldo() {
            double saldoAnterior = getSaldo();
            double rendimento = saldoAnterior * RENDIMENTO_MENSAL;
            double novoSaldo = saldoAnterior + rendimento;
            setSaldo(novoSaldo);
            registrarTransacao("Rendimento mensal: +" + rendimento);
        }
    }

    class RendaFixa extends Conta {

        private double taxaRendimento;
        private static final double IMPOSTO_SAQUE = 0.15;
    
        public RendaFixa(Cliente cliente, double taxaRendimento) {
            super(cliente);
            this.taxaRendimento = taxaRendimento;
        }
    
        @Override
        public void atualizarSaldo() {
            double saldoAnterior = getSaldo();
            double rendimento = saldoAnterior * taxaRendimento;
            double novoSaldo = saldoAnterior + rendimento;
            setSaldo(novoSaldo);
            registrarTransacao("Rendimento mensal: +" + rendimento);
        }
    
        @Override
        public void sacar(double valor) {
            double saldoAnterior = getSaldo();
            if (valor > saldoAnterior) {
                System.out.println("Saldo insuficiente para saque!");
            } else {
                double imposto = valor * IMPOSTO_SAQUE;
                double novoSaldo = saldoAnterior - valor - imposto;
                setSaldo(novoSaldo);
                registrarTransacao("Saque: -" + valor + ", Imposto: -" + imposto);
            }
        }
    }

    // public abstract class RendaFixa extends Conta {

    //     private double taxaRendimento;
    //     private static final double IMPOSTO_SAQUE = 0.15;

    //     public RendaFixa(Cliente cliente, double taxaRendimento) {
    //         super(cliente);
    //         this.taxaRendimento = taxaRendimento;
    //     }

    //     @Override
    //     public void atualizarSaldo() {
    //         // Implementação do cálculo do saldo com rendimento mensal
    //         double saldoAnterior = getSaldo();
    //         double rendimento = saldoAnterior * taxaRendimento;
    //         double novoSaldo = saldoAnterior + rendimento;
    //         setSaldo(novoSaldo);
    //         registrarTransacao("Rendimento mensal: +" + rendimento);
    //     }

    //     @Override
    //     public void sacar(double valor) {
    //         double saldoAnterior = getSaldo();
    //         if (valor > saldoAnterior) {
    //             System.out.println("Saldo insuficiente para saque!");
    //         } else {
    //             double imposto = valor * IMPOSTO_SAQUE;
    //             double novoSaldo = saldoAnterior - valor - imposto;
    //             setSaldo(novoSaldo);
    //             registrarTransacao("Saque: -" + valor + ", Imposto: -" + imposto);
    //         }
    //     }
    // }
=======

    public abstract void atualizarSaldo();
>>>>>>> parent of 363e7f4 (refatoração do código)
=======

    public abstract void atualizarSaldo();
>>>>>>> parent of 363e7f4 (refatoração do código)
=======

    public abstract void atualizarSaldo();
>>>>>>> parent of 363e7f4 (refatoração do código)
}
