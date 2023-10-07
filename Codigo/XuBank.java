package Codigo;

import java.util.*;

public class XuBank {
    private List<Cliente> clientes = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        XuBank xubank = new XuBank();

        while (true) {
            System.out.println("Bem vindo ao XuBank!");
            System.out.println("1 - Cadastrar Cliente");
            System.out.println("2 - Consultar Saldo");
            System.out.println("3 - Depositar");
            System.out.println("4 - Sacar");
            System.out.println("5 - Transferir");
            System.out.println("6 - Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    cadastrarCliente(xubank);
                    break;
                case 2:
                    consultarSaldo(xubank);
                    break;
                case 3:
                    depositar(xubank);
                    break;
                case 4:
                    sacar(xubank);
                    break;
                case 5:
                    transferir(xubank);
                    break;
                case 6:
                    System.out.println("Obrigado por usar o XuBank.");
                    System.exit(0);
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static void cadastrarCliente(XuBank xubank) {
        System.out.print("Informe o nome do cliente: ");
        String nome = scanner.next();
        System.out.print("Informe o CPF do cliente: ");
        String cpf = scanner.next();
        System.out.print("Informe a senha do cliente: ");
        String senha = scanner.next();

        Cliente novoCliente = new Cliente(nome, cpf, senha);
        xubank.clientes.add(novoCliente);

        System.out.println("Cliente cadastrado com sucesso!");
    }

    private static void consultarSaldo(XuBank xubank) {
        System.out.print("Informe o CPF do cliente: ");
        String cpf = scanner.next();
        System.out.print("Informe a senha do cliente: ");
        String senha = scanner.next();

        Cliente cliente = encontrarClientePorCpf(xubank, cpf);
        if (cliente != null && cliente.getSenha().equals(senha)) {
            System.out.print("Escolha a conta (1 - Conta Corrente, 2 - Conta Poupança): ");
            int escolhaConta = scanner.nextInt();
            List<Conta> contasCliente = cliente.getContas();

            if (escolhaConta == 1 && contasCliente.size() >= 1) {
                Conta conta = contasCliente.get(0); // Assumindo que o cliente tem apenas uma Conta Corrente
                conta.atualizarSaldo();
                double saldo = conta.getSaldo();
                System.out.println("Saldo atual: R$" + saldo);
            } else if (escolhaConta == 2 && contasCliente.size() >= 2) {
                Conta conta = contasCliente.get(1); // Assumindo que o cliente tem apenas uma Conta Poupança
                conta.atualizarSaldo();
                double saldo = conta.getSaldo();
                System.out.println("Saldo atual: R$" + saldo);
            } else {
                System.out.println("Conta não encontrada.");
            }
        } else {
            System.out.println("Cliente não encontrado ou senha incorreta.");
        }
    }

    private static void depositar(XuBank xubank) {
        System.out.print("Informe o CPF do cliente: ");
        String cpf = scanner.next();
        System.out.print("Informe a senha do cliente: ");
        String senha = scanner.next();

        Cliente cliente = encontrarClientePorCpf(xubank, cpf);
        if (cliente != null && cliente.getSenha().equals(senha)) {
            System.out.print("Escolha a conta (1 - Conta Corrente, 2 - Conta Poupança): ");
            int escolhaConta = scanner.nextInt();
            List<Conta> contasCliente = cliente.getContas();

            if (escolhaConta == 1 && contasCliente.size() >= 1) {
                ContaCorrente conta = (ContaCorrente) contasCliente.get(0); // Assumindo que o cliente tem apenas uma Conta Corrente
                System.out.print("Informe o valor do depósito: R$");
                double valorDeposito = scanner.nextDouble();
                conta.depositar(valorDeposito);
                System.out.println("Depósito realizado com sucesso.");
            } else if (escolhaConta == 2 && contasCliente.size() >= 2) {
                ContaPoupanca conta = (ContaPoupanca) contasCliente.get(1); // Assumindo que o cliente tem apenas uma Conta Poupança
                System.out.print("Informe o valor do depósito: R$");
                double valorDeposito = scanner.nextDouble();
                conta.depositar(valorDeposito);
                System.out.println("Depósito realizado com sucesso.");
            } else {
                System.out.println("Conta não encontrada.");
            }
        } else {
            System.out.println("Cliente não encontrado ou senha incorreta.");
        }
    }

    private static void sacar(XuBank xubank) {
        System.out.print("Informe o CPF do cliente: ");
        String cpf = scanner.next();
        System.out.print("Informe a senha do cliente: ");
        String senha = scanner.next();

        Cliente cliente = encontrarClientePorCpf(xubank, cpf);
        if (cliente != null && cliente.getSenha().equals(senha)) {
            System.out.print("Escolha a conta (1 - Conta Corrente, 2 - Conta Poupança): ");
            int escolhaConta = scanner.nextInt();
            List<Conta> contasCliente = cliente.getContas();

            if (escolhaConta == 1 && contasCliente.size() >= 1) {
                ContaCorrente conta = (ContaCorrente) contasCliente.get(0); // Assumindo que o cliente tem apenas uma Conta Corrente
                System.out.print("Informe o valor do saque: R$");
                double valorSaque = scanner.nextDouble();
                if (conta.sacar(valorSaque)) {
                    System.out.println("Saque realizado com sucesso.");
                } else {
                    System.out.println("Saldo insuficiente ou excedeu o limite de saque.");
                }
            } else if (escolhaConta == 2 && contasCliente.size() >= 2) {
                ContaPoupanca conta = (ContaPoupanca) contasCliente.get(1); // Assumindo que o cliente tem apenas uma Conta Poupança
                System.out.print("Informe o valor do saque: R$");
                double valorSaque = scanner.nextDouble();
                if (conta.sacar(valorSaque)) {
                    System.out.println("Saque realizado com sucesso.");
                } else {
                    System.out.println("Saldo insuficiente ou excedeu o limite de saque.");
                }
            } else {
                System.out.println("Conta não encontrada.");
            }
        } else {
            System.out.println("Cliente não encontrado ou senha incorreta.");
        }
    }

    private static void transferir(XuBank xubank) {
        System.out.print("Informe o CPF do cliente: ");
        String cpf = scanner.next();
        System.out.print("Informe a senha do cliente: ");
        String senha = scanner.next();

        Cliente cliente = encontrarClientePorCpf(xubank, cpf);
        if (cliente != null && cliente.getSenha().equals(senha)) {
            System.out.print("Escolha a conta de origem (1 - Conta Corrente, 2 - Conta Poupança): ");
            int escolhaContaOrigem = scanner.nextInt();
            List<Conta> contasCliente = cliente.getContas();

            if (escolhaContaOrigem == 1 && contasCliente.size() >= 1) {
                ContaCorrente contaOrigem = (ContaCorrente) contasCliente.get(0); // Assumindo que o cliente tem apenas uma Conta Corrente
                System.out.print("Informe o valor da transferência: R$");
                double valorTransferencia = scanner.nextDouble();
                System.out.print("Informe o CPF do destinatário: ");
                String cpfDestinatario = scanner.next();

                Cliente destinatario = encontrarClientePorCpf(xubank, cpfDestinatario);
                if (destinatario != null) {
                    List<Conta> contasDestinatario = destinatario.getContas();
                    if (!contasDestinatario.isEmpty()) {
                        Conta contaDestinatario = contasDestinatario.get(0); // Assumindo que o destinatário tem apenas uma conta
                        if (contaOrigem.sacar(valorTransferencia)) {
                            contaDestinatario.depositar(valorTransferencia);
                            System.out.println("Transferência realizada com sucesso.");
                        } else {
                            System.out.println("Saldo insuficiente ou excedeu o limite de saque.");
                        }
                    } else {
                        System.out.println("O destinatário não possui contas.");
                    }
                } else {
                    System.out.println("Destinatário não encontrado.");
                }
            } else if (escolhaContaOrigem == 2 && contasCliente.size() >= 2) {
                ContaPoupanca contaOrigem = (ContaPoupanca) contasCliente.get(1); // Assumindo que o cliente tem apenas uma Conta Poupança
                System.out.print("Informe o valor da transferência: R$");
                double valorTransferencia = scanner.nextDouble();
                System.out.print("Informe o CPF do destinatário: ");
                String cpfDestinatario = scanner.next();

                Cliente destinatario = encontrarClientePorCpf(xubank, cpfDestinatario);
                if (destinatario != null) {
                    List<Conta> contasDestinatario = destinatario.getContas();
                    if (!contasDestinatario.isEmpty()) {
                        Conta contaDestinatario = contasDestinatario.get(0); // Assumindo que o destinatário tem apenas uma conta
                        if (contaOrigem.sacar(valorTransferencia)) {
                            contaDestinatario.depositar(valorTransferencia);
                            System.out.println("Transferência realizada com sucesso.");
                        } else {
                            System.out.println("Saldo insuficiente ou excedeu o limite de saque.");
                        }
                    } else {
                        System.out.println("O destinatário não possui contas.");
                    }
                } else {
                    System.out.println("Destinatário não encontrado.");
                }
            } else {
                System.out.println("Conta de origem não encontrada.");
            }
        } else {
            System.out.println("Cliente não encontrado ou senha incorreta.");
        }
    }

    private static Cliente encontrarClientePorCpf(XuBank xubank, String cpf) {
        for (Cliente cliente : xubank.clientes) {
            if (cliente.getCpf().equals(cpf)) {
                return cliente;
            }
        }
        return null;
    }
}