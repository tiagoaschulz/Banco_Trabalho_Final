package Models;

public class Conta_Poupanca extends Conta {

    // Atributo específico da ContaPoupanca
    private double taxaRendimento;

    // Construtor
    public Conta_Poupanca(int numero, String agencia, Cliente cliente, double taxaRendimento, TipoConta tipoConta) {
        super(numero, agencia, cliente, tipoConta); // Chama o construtor da classe base Conta
        this.taxaRendimento = taxaRendimento;
    }

    // Método para calcular o rendimento da conta
    public double calcularRendimento() {
        // O rendimento é calculado multiplicando o saldo pela taxa de rendimento
        return getSaldo() * (taxaRendimento / 100);
    }

    // Getter e Setter para taxaRendimento
    public double getTaxaRendimento() {
        return taxaRendimento;
    }

    public void setTaxaRendimento(double taxaRendimento) {
        this.taxaRendimento = taxaRendimento;
    }

    // Sobrescreve o método consultarSaldo para incluir o rendimento
    @Override
    public double consultarSaldo() {
        double rendimento = calcularRendimento();
        System.out.println("Rendimento da conta poupança: R$ " + rendimento);
        return super.consultarSaldo() + rendimento; // Retorna saldo + rendimento
    }

    @Override
    public void consultarDados() {
        consultarSaldo();
    }


}


