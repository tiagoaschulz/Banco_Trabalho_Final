package Models;

public abstract class Conta {

    public enum TipoConta {
        POUPANCA, CORRENTE
    }

    // Atributos comuns para todas as contas
    private int numero;
    private String agencia;
    private double saldo;
    private Cliente cliente;  // Cliente que possui a conta
    private TipoConta tipoConta; // Tipo da conta (Poupança ou Corrente)

    // Construtor
    public Conta(int numero, String agencia, Cliente cliente, TipoConta tipoConta) {
        this.numero = numero;
        this.agencia = agencia;
        this.cliente = cliente;
        this.saldo = 0.0;
        this.tipoConta = tipoConta;
    }

    // Método para depositar valores
    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
            System.out.println("Depósito de " + valor + " realizado com sucesso.");
        } else {
            System.out.println("Valor de depósito inválido.");
        }
    }

    // Método para sacar valores
    public boolean sacar(double valor) {
        if (valor > 0 && valor <= saldo) {
            saldo -= valor;
            System.out.println("Saque de " + valor + " realizado com sucesso.");
            return true;
        } else {
            System.out.println("Saldo insuficiente ou valor inválido.");
            return false;
        }
    }

    // Método para consultar o saldo
    public double consultarSaldo() {
        return saldo;
    }

    // Método para consultar dados da conta
    public void consultarDadosConta() {
        System.out.println("Dados da Conta:");
        System.out.println("Número da conta: " + numero);
        System.out.println("Agência: " + agencia);
        System.out.println("Cliente: " + cliente.getNome());
        System.out.println("Tipo da conta: " + tipoConta);
        System.out.println("Saldo: " + saldo);
    }

    // Método abstrato para ser implementado pelas subclasses
    public abstract void consultarDados();

    // Getters e Setters
    public int getNumero() {
        return numero;
    }

    public String getAgencia() {
        return agencia;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public double getSaldo() {
        return saldo;
    }

    public TipoConta getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(TipoConta tipoConta) {
        this.tipoConta = tipoConta;
    }

    public void setAgencia(String Agencia) {
        this.agencia = agencia;
    }
}
