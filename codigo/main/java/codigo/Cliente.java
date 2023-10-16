package codigo.main.java.codigo;

import java.io.*;
import java.util.*;

public abstract class Cliente {
    private String nome;
    private static String cpf;
    private String senha;
    private TipoCliente tipo;
    private int pontosFidelidade;
    private List<Conta> contas;
    private double saldo;

    public Cliente(String nome, String cpf, String senha, TipoCliente tipo) {
        this.nome = nome;
        Cliente.cpf = cpf;
        this.senha = senha;
        this.tipo = tipo;
        this.contas = new ArrayList<>();
        this.pontosFidelidade = 0;
    }

    public void definirSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getNome() {
        return nome;
    }

    public static String getCpf() {
        return cpf;
    }

    public String getSenha() {
        return senha;
    }

    public TipoCliente getTipo() {
        return tipo;
    }

    public int getPontosFidelidade() {
        return pontosFidelidade;
    }

    public List<Conta> getContas() {
        return contas;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        Cliente.cpf = cpf;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setTipo(TipoCliente tipo) {
        this.tipo = tipo;
    }

    public void setPontosFidelidade(int pontosFidelidade) {
        this.pontosFidelidade = pontosFidelidade;
    }

    public void setContas(List<Conta> contas) {
        this.contas = contas;
    }

    public void adicionarConta(Conta conta) {
        contas.add(conta);
        Conta.adicionarContaAoArquivo(Cliente.getCpf(), conta);
    }

    public void consultarSaldo() {
        for (Conta conta : contas) {
            System.out.println("Saldo da conta: " + conta.getSaldo());
        }
    }

    public void depositar(double valor) {
        List<Conta> contas = XuBank.lerContasDoArquivo(); // Lê as contas do arquivo
        Scanner scanner = new Scanner(System.in);
    
        // Mostrar as contas disponíveis e pedir ao cliente para escolher uma
        System.out.println("Escolha uma conta para depositar:");
        for (int i = 0; i < contas.size(); i++) {
            System.out.println(i + ". " + contas.get(i).getTipoConta());
        }
    
        int indiceContaEscolhida = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha
    
        if (indiceContaEscolhida >= 0 && indiceContaEscolhida < contas.size()) {
            // Obter a conta escolhida
            Conta contaEscolhida = contas.get(indiceContaEscolhida);
    
            // Atualizar o saldo no arquivo
            contaEscolhida.depositar(valor);
    
            System.out.println("Depósito de " + valor + " realizado com sucesso na conta " + contaEscolhida.getTipoConta() + ".");
            Conta.atualizarSaldoNoArquivoDeposito(Cliente.getCpf(), contaEscolhida.getTipoConta(), valor);
        } else {
            System.out.println("Opção inválida. Depósito cancelado.");
        }
        scanner.close();
    }
    
    public void sacar(double valor, String cpfSaque) {
        List<Conta> contas = XuBank.lerContasDoArquivo();
        // Encontrar a conta do cliente com base no CPF
        Conta contaDoCliente = null;
        
        // Encontrar todas as contas associadas ao CPF
        List<Conta> contasAssociadas = new ArrayList<>();
        for (Conta conta : contas) {
            conta.getCliente();
            if (Cliente.getCpf().equals(cpfSaque)) {
                contasAssociadas.add(conta);
            }
        }
    
        // Verificar se há contas associadas ao CPF
        if (!contasAssociadas.isEmpty()) {
            // Listar as contas disponíveis para saque
            System.out.println("Contas disponíveis para saque:");
            for (int i = 0; i < contasAssociadas.size(); i++) {
                System.out.println(i + ". " + contasAssociadas.get(i).getTipoConta());
            }
            
            try (// Pedir ao cliente para escolher uma conta
            Scanner scanner = new Scanner(System.in)) {
                int indiceContaEscolhida = scanner.nextInt();
                scanner.nextLine(); // Consumir a quebra de linha
                
                if (indiceContaEscolhida >= 0 && indiceContaEscolhida < contasAssociadas.size()) {
                    // Obter a conta escolhida
                    contaDoCliente = contasAssociadas.get(indiceContaEscolhida);
                }
            }
        }
    
        // Verificar se a conta foi encontrada e realizar o saque
        if (contaDoCliente != null) {
            // Verificar se há saldo suficiente na conta
            if (contaDoCliente.getSaldo() >= valor) {
                // Realizar o saque na conta do cliente
                contaDoCliente.sacar(valor);
    
                // Atualizar o saldo no arquivo
                Conta.atualizarSaldoNoArquivoSaque(cpfSaque, contaDoCliente.getTipoConta(), valor);
    
                System.out.println("Saque de " + valor + " realizado com sucesso na conta " + contaDoCliente.getTipoConta() + ".");
            } else {
                System.out.println("Saldo insuficiente para saque na conta " + contaDoCliente.getTipoConta() + ".");
            }
        } else {
            System.out.println("Nenhuma conta encontrada para o CPF fornecido. Saque não realizado.");
        }
    } 
   
    public boolean transferir(double valor, String cpfRemetente, String cpfDestinatario) {
        List<Conta> contas = XuBank.lerContasDoArquivo();
    
        Conta contaRemetente = null;
        Conta contaDestinatario = null;
    
        // Encontre as contas do remetente e destinatário
        for (Conta conta : contas) {
            conta.getCliente();
            if (Cliente.getCpf().equals(cpfRemetente)) {
                contaRemetente = conta;
            }
            conta.getCliente();
            if (Cliente.getCpf().equals(cpfDestinatario)) {
                contaDestinatario = conta;
            }
            if (contaRemetente != null && contaDestinatario != null) {
                break; // Encontrou ambas as contas, pode parar de procurar
            }
        }
    
        if (contaRemetente != null && contaDestinatario != null) {
            if (contaRemetente.getSaldo() >= valor) {
                contaRemetente.sacar(valor); // Realiza o saque na conta do remetente
                contaDestinatario.depositar(valor); // Realiza o depósito na conta do destinatário
    
                // Atualize os saldos no arquivo para refletir a transferência
                Conta.atualizarSaldoNoArquivoSaque(cpfRemetente, contaRemetente.getTipoConta(), valor);
                Conta.atualizarSaldoNoArquivoDeposito(cpfDestinatario, contaDestinatario.getTipoConta(), valor);
    
                System.out.println("Transferência de " + valor + " realizada com sucesso da conta " + contaRemetente.getTipoConta() + " para a conta " + contaDestinatario.getTipoConta() + ".");
                return true; // Transferência bem-sucedida
            } else {
                System.out.println("Saldo insuficiente para a transferência. Transferência não realizada.");
            }
        } else {
            System.out.println("Conta do remetente e/ou destinatário não encontrada. Transferência não realizada.");
        }
        return false; // Transferência sem sucesso
    }
   
    // Método para atualizar os pontos de fidelidade com base no tipo de cliente e
    // saldo total
    public void atualizarPontosFidelidade() {
        for (Conta conta : contas) {
            double saldo = conta.getSaldo();
            if (tipo == TipoCliente.GOLD && saldo >= 1000) {
                pontosFidelidade += 10;
            } else if (tipo == TipoCliente.VIP && saldo >= 2000) {
                pontosFidelidade += 30;
            }
        }
    }
    
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

    public static void adicionarClienteAoArquivo(String nome, String cpf, String senha, TipoCliente tipo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("clientes.txt", true))) {
            writer.write(nome + "," + cpf + "," + senha + "," + tipo);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo: " + e.getMessage());
        }
    }

}