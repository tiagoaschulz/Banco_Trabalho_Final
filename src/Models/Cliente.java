package Models;

import java.time.LocalDate;

public class Cliente extends Usuario {

    private String senha;

    // Construtor
    public Cliente(int id, String nome, String cpf, LocalDate dataNascimento, String telefone, Endereco endereco, String senha) {
        super(id, nome, cpf, dataNascimento, telefone, endereco); // Chama o construtor da classe pai
        this.senha = senha;
    }

    // Implementação do método de login
    @Override
    public boolean login(String senha) {
        return this.senha.equals(senha);
    }

    // Implementação do método logout
    @Override
    public void logout() {
        System.out.println("Cliente " + getNome() + " fez logout.");
    }

    // Métodos relacionados à Conta (simulando interações)

    // Consultar saldo (Simula o acesso à conta do cliente)
    public double consultarSaldo(Conta conta) {
        if (conta != null) {
            return conta.consultarSaldo();
        } else {
            System.out.println("Conta não encontrada.");
            return 0.0;
        }
    }

    // Depositar em uma conta
    public void depositar(Conta conta, double valor) {
        if (conta != null) {
            conta.depositar(valor);
        } else {
            System.out.println("Conta não encontrada.");
        }
    }

    // Sacar de uma conta
    public boolean sacar(Conta conta, double valor) {
        if (conta != null) {
            return conta.sacar(valor);
        } else {
            System.out.println("Conta não encontrada.");
            return false;
        }
    }

    // Consultar extrato (Simula o retorno de um extrato)
    public String consultarExtrato() {
        return "Extrato da conta do cliente " + getNome() + ":\n- Simulação de transações.";
    }

    // Consultar limite da conta (Se o cliente possuir uma ContaCorrente)
    public double consultarLimite(Conta_Corrente contaCorrente) {
        if (contaCorrente != null) {
            return contaCorrente.getLimite();
        } else {
            System.out.println("Conta corrente não encontrada.");
            return 0.0;
        }
    }

    // Getters e Setters
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    // Sobrescreve o método consultarDados para retornar informações do cliente
    @Override
    public String consultarDados() {
        return "Dados do Cliente:\n" +
                "Nome: " + getNome() + "\n" +
                "CPF: " + getCpf() + "\n" +
                "Data de Nascimento: " + getDataNascimento() + "\n" +
                "Telefone: " + getTelefone() + "\n" +
                "Endereço: " + (getEndereco() != null ? getEndereco().toString() : "Não cadastrado");
    }
}
