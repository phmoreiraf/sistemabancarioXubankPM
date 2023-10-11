package Codigo;

import java.util.*;

public class XuBank {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        
        Conta contaCorrente;
        Cliente cliente;

        // Criação de um cliente
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

        switch (tipoCliente) {
            case 1:
                String regular = "Conta regular";
                cliente = new Regular(nome, cpf, senha, regular);
                break;
            case 2:
                String gold = "Conta gold";
                cliente = new Gold(nome, cpf, senha, gold);
                break;
            case 3:
                String vip = "Conta vip";
                cliente = new VIP(nome, cpf, senha, vip);
                break;
            default:
                System.out.println("Tipo de cliente inválido!");
                scanner.close();
                return;
        }

        contaCorrente = new ContaCorrente(cliente);

        boolean sair = false;

        while (!sair) {
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Criar conta");
            System.out.println("2 - Depositar");
            System.out.println("3 - Sacar");
            System.out.println("4 - Transferir");
            System.out.println("5 - Consultar saldo");
            System.out.println("6 - Atualizar saldo");
            System.out.println("0 - Sair");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    // Criação de uma conta
                    System.out.println(
                            "Digite o tipo de conta a ser criada (1 - Corrente, 2 - Poupança, 3 - Renda Fixa, 4 - Investimento):");
                    int tipoConta = scanner.nextInt();

                    if (tipoConta == 1) {
                        cliente.adicionarConta(new ContaCorrente(cliente));
                        System.out.println("Conta corrente criada com sucesso!");
                    } else if (tipoConta == 2) {
                        cliente.adicionarConta(new Poupanca(cliente));
                        System.out.println("Conta poupança criada com sucesso!");
                    } else if (tipoConta == 3) {
                        System.out.println("Digite o rendimento contratado para a conta de renda fixa:");
                        double rendimentoContratado = scanner.nextDouble();
                        cliente.adicionarConta(new RendaFixa(cliente, rendimentoContratado));
                        System.out.println("Conta de renda fixa criada com sucesso!");
                    } else if (tipoConta == 4) {
                        cliente.adicionarConta(new Investimento(cliente));
                        System.out.println("Conta de investimento criada com sucesso!");
                    } else {
                        System.out.println("Tipo de conta inválido!");
                    }
                    break;
                case 2:
                    // Depósito
                    System.out.println("Digite o valor a ser depositado:");
                    double valorDeposito = scanner.nextDouble();
                    System.out.println("Digite o índice da conta para depósito:");
                    int indiceContaDeposito = scanner.nextInt();
                    cliente.depositar(valorDeposito, indiceContaDeposito);
                    break;
                case 3:
                    // Saque
                    System.out.println("Digite o valor a ser sacado:");
                    double valorSaque = scanner.nextDouble();
                    System.out.println("Digite o índice da conta para saque:");
                    int indiceContaSaque = scanner.nextInt();
                    cliente.sacar(valorSaque, indiceContaSaque);
                    break;
                case 4:
                    // Transferência
                    System.out.println("Digite o valor a ser transferido:");
                    double valorTransferencia = scanner.nextDouble();
                    System.out.println("Digite o índice da conta de origem para transferência:");
                    int indiceContaOrigem = scanner.nextInt();
                    System.out.println("Digite o índice da conta de destino para transferência:");
                    int indiceContaDestino = scanner.nextInt();
                    cliente.transferir(valorTransferencia, indiceContaOrigem, indiceContaDestino);
                    break;
                case 5:
                    // Consulta de saldo
                    cliente.consultarSaldo();
                    break;
                case 6:
                    // Atualização de saldo
                    for (Conta conta : cliente.getContas()) {
                        conta.atualizarSaldo();
                    }
                    break;
                case 0:
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }

        scanner.close();
    }
}
