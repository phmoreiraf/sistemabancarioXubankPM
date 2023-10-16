package codigo.test.java.codigo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class XuBankTest {
    private Cliente cliente;
    
    @BeforeEach
    public void setUp() {
        // Configurar um cliente de exemplo para testes
        cliente = new Cliente("Pedro", "phm0011", "1234567", TipoCliente.REGULAR);
    }

    @Test
    public void testDepositar() {
        double saldoInicial = cliente.getConta().getSaldo();
        double valorDeposito = 100.0;
        cliente.depositar(valorDeposito);
        assertEquals(saldoInicial + valorDeposito, cliente.getConta().getSaldo(), 0.01); // Usando delta para comparar valores de ponto flutuante
    }

    @Test
    public void testSaque() {
        double saldoInicial = cliente.getConta().getSaldo();
        double valorSaque = 50.0;
        cliente.sacar(valorSaque, cliente.getCpf());
        assertEquals(saldoInicial - valorSaque, cliente.getConta().getSaldo(), 0.01);
    }

    @Test
    public void testTransferencia() {
        double saldoInicialOrigem = cliente.getConta().getSaldo();
        double valorTransferencia = 30.0;
        Cliente destinatario = new Cliente("Maria", "987654321", "senha", TipoCliente.REGULAR);
        cliente.transferir(valorTransferencia, cliente.getCpf(), destinatario.getCpf());
        assertEquals(saldoInicialOrigem - valorTransferencia, cliente.getConta().getSaldo(), 0.01);
        assertEquals(valorTransferencia, destinatario.getConta().getSaldo(), 0.01);
    }
}
