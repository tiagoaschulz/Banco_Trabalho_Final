package Models;

import java.time.LocalDate;

public class Conta_Corrente extends Conta {

    // Atributos específicos da classe ContaCorrente
    private double limite;  // Limite de crédito da conta corrente
    private LocalDate dataVencimento;  // Data de vencimento da fatura ou do limite de crédito

    // Construtor
    public Conta_Corrente(int numero, String agencia, Cliente cliente, double limite, LocalDate dataVencimento, TipoConta tipoConta) {
        super(numero, agencia, cliente, tipoConta);  // Chama o construtor da classe base Conta
        this.limite = limite;
        this.dataVencimento = dataVencimento;
    }

    // Método para consultar o limite da conta corrente
    public double consultarLimite() {
        return limite;
    }

    // Sobrescreve o método consultarDados da classe Conta
    @Override
    public void consultarDados() {
        super.consultarDadosConta();  // Chama o método da classe mãe para exibir dados básicos
        System.out.println("Limite de Crédito: " + limite);
        System.out.println("Data de Vencimento: " + dataVencimento);
    }

    // Getters e Setters
    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }
}

