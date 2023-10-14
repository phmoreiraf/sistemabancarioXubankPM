package Codigo;

import java.util.Scanner;
import java.util.List;

public class XuBank {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Cliente cliente = null;

        boolean sair = false;

        while (!sair) {
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Criar cliente");
            System.out.println("2 - Acessar conta de cliente existente");
            System.out.println("0 - Sair");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (opcao) {
                case 1:
                    // Criação de um novo cliente
                    System.out.println("Digite o nome do cliente:");
                    String nome = scanner.nextLine();

                    System.out.println("Digite o CPF do cliente:");
                    String cpf = scanner.nextLine();

                    System.out.println("Digite a senha do cliente:");
                    String senha = scanner.nextLine();

                    System.out.println("Escolha o tipo de cliente:");
                    System.out.println("1. Regular");
                    System.out.println("2. Gold");
                    System.out.println("3. VIP");

                    int tipoCliente = scanner.nextInt();
                    scanner.nextLine(); // Consumir a quebra de linha

                    switch (tipoCliente) {
                        case 1:
                            cliente = new Regular(nome, cpf, senha, "Regular");
                            break;
                        case 2:
                            cliente = new Gold(nome, cpf, senha, "Gold");
                            break;
                        case 3:
                            cliente = new VIP(nome, cpf, senha, "VIP");
                            break;
                        default:
                            System.out.println("Tipo de cliente inválido!");
                            break;
                    }
                    break;
                case 2:
                    // Acesso à conta de cliente existente
                    if (cliente != null) {
                        System.out.println("Bem-vindo, " + cliente.getNome() + " (" + cliente.getCpf() + ")");
                        boolean clienteLogado = true;

                        while (clienteLogado) {
                            System.out.println("Escolha uma opção:");
                            System.out.println("1 - Criar conta");
                            System.out.println("2 - Depositar");
                            System.out.println("3 - Sacar");
                            System.out.println("4 - Transferir");
                            System.out.println("5 - Consultar saldo");
                            System.out.println("6 - Atualizar saldo");
                            System.out.println("7 - Trocar pontos de fidelidade por recompensas");
                            System.out.println("0 - Sair da conta");

                            int opcaoConta = scanner.nextInt();
                            scanner.nextLine(); // Consumir a quebra de linha

                            switch (opcaoConta) {
                                case 1:
                                    // Criação de uma conta
                                    System.out.println("Digite o tipo de conta a ser criada:");
                                    System.out.println("1 - Conta Corrente");
                                    System.out.println("2 - Poupança");
                                    System.out.println("3 - Renda Fixa");
                                    System.out.println("4 - Investimento");

                                    int tipoConta = scanner.nextInt();
                                    scanner.nextLine(); // Consumir a quebra de linha

                                    switch (tipoConta) {
                                        case 1:
                                            cliente.adicionarConta(new ContaCorrente(cliente));
                                            System.out.println("Conta corrente criada com sucesso!");
                                            break;
                                        case 2:
                                            cliente.adicionarConta(new Poupanca(cliente));
                                            System.out.println("Conta poupança criada com sucesso!");
                                            break;
                                        case 3:
                                            System.out.println("Digite o rendimento contratado para a conta de renda fixa:");
                                            double rendimentoContratado = scanner.nextDouble();
                                            scanner.nextLine(); // Consumir a quebra de linha
                                            cliente.adicionarConta(new RendaFixa(cliente, rendimentoContratado));
                                            System.out.println("Conta de renda fixa criada com sucesso!");
                                            break;
                                        case 4:
                                            cliente.adicionarConta(new Investimento(cliente));
                                            System.out.println("Conta de investimento criada com sucesso!");
                                            break;
                                        default:
                                            System.out.println("Tipo de conta inválido!");
                                    }
                                    break;
                                case 2:
                                    // Depósito
                                    System.out.println("Digite o valor a ser depositado:");
                                    double valorDeposito = scanner.nextDouble();
                                    scanner.nextLine(); // Consumir a quebra de linha
                                    System.out.println("Digite o índice da conta para depósito:");
                                    int indiceContaDeposito = scanner.nextInt();
                                    scanner.nextLine(); // Consumir a quebra de linha
                                    cliente.depositar(valorDeposito, indiceContaDeposito);
                                    break;
                                case 3:
                                    // Saque
                                    System.out.println("Digite o valor a ser sacado:");
                                    double valorSaque = scanner.nextDouble();
                                    scanner.nextLine(); // Consumir a quebra de linha
                                    System.out.println("Digite o índice da conta para saque:");
                                    int indiceContaSaque = scanner.nextInt();
                                    scanner.nextLine(); // Consumir a quebra de linha
                                    cliente.sacar(valorSaque, indiceContaSaque);
                                    break;
                                case 4:
                                    // Transferência
                                    System.out.println("Digite o valor a ser transferido:");
                                    double valorTransferencia = scanner.nextDouble();
                                    scanner.nextLine(); // Consumir a quebra de linha
                                    System.out.println("Digite o índice da conta de origem para transferência:");
                                    int indiceContaOrigem = scanner.nextInt();
                                    scanner.nextLine(); // Consumir a quebra de linha
                                    System.out.println("Digite o índice da conta de destino para transferência:");
                                    int indiceContaDestino
