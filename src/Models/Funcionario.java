package Models;

import java.time.LocalDate;

public class Funcionario extends Usuario {

    // Atributos específicos da classe Funcionario
    private String codigoFuncionario;
    private String cargo;
    private String senha;

    // Construtor
    public Funcionario(int id, String nome, String cpf, LocalDate dataNascimento, String telefone, Endereco endereco,
                       String codigoFuncionario, String cargo, String senha) {
        super(id, nome, cpf, dataNascimento, telefone, endereco); // Chama o construtor da classe base Usuario
        this.codigoFuncionario = codigoFuncionario;
        this.cargo = cargo;
        this.senha = senha;
    }

    // Implementação do método de login (Exemplo)
    @Override
    public boolean login(String senha) {
        // Verifica se a senha informada é a mesma do funcionário
        return this.senha.equals(senha);
    }

    // Implementação do método logout (que estava faltando)
    @Override
    public void logout() {
        System.out.println("Funcionário " + this.getNome() + " fez logout.");
    }

    // Métodos específicos da classe Funcionario

    // Método para abrir uma conta
    public void abrirConta(Conta conta) {
        // Lógica para abrir uma conta (aqui apenas simula a operação)
        System.out.println("Conta número " + conta.getNumero() + " aberta com sucesso para o cliente.");
    }

    // Método para encerrar uma conta
    public void encerrarConta(Conta conta) {
        // Lógica para encerrar uma conta (simulação)
        System.out.println("Conta número " + conta.getNumero() + " encerrada.");
    }

    // Método para consultar dados de uma conta (simula o acesso a uma conta)
    public Conta consultarDadosConta(int numeroConta, String agencia, Cliente cliente, Conta.TipoConta tipoConta) {
        // Simula a busca no banco de dados, retornando uma conta fictícia
        System.out.println("Consultando dados da conta número " + numeroConta);
        return new Conta(numeroConta, agencia, cliente, tipoConta) {
            @Override
            public void consultarDados() {
                System.out.println("Dados da Conta:");
                System.out.println("Número da conta: " + numeroConta);
                System.out.println("Agência: " + agencia);
                System.out.println("Cliente: " + cliente.getNome());
                System.out.printf("Tipo da Conta: "+ getTipoConta());

            }
        };

    }

    // Método para consultar dados de um cliente (simula o acesso ao cliente)
    public Cliente consultarDadosCliente(int idCliente, String nome, String cpf, LocalDate dataNascimento, String telefone, Endereco endereco, String senha) {
        // Simula a busca de um cliente
        System.out.println("Consultando dados do cliente com ID: " + idCliente);
        return new Cliente(idCliente, nome, cpf, dataNascimento, telefone, endereco, senha);  // Simulando o retorno de um cliente com o ID fornecido
    }

    // Método para alterar os dados de uma conta
    public void alterarDadosConta(Conta conta) {
        System.out.println("Alterando dados da conta número " + conta.getNumero());
    }

    // Método para alterar os dados de um cliente
    public void alterarDadosCliente(Cliente cliente) {
        System.out.println("Alterando dados do cliente ID " + cliente.getId());
    }

    // Método para cadastrar um novo funcionário
    public void cadastrarFuncionario(Funcionario funcionario) {
        System.out.println("Funcionário " + funcionario.getNome() + " cadastrado com sucesso.");
    }

    // Método para gerar relatório de movimentação
    public void gerarRelatorioMovimentacao() {
        System.out.println("Gerando relatório de movimentação...");
        // Aqui você pode adicionar a lógica para gerar um relatório real
    }

    // Getters e Setters

    public String getCodigoFuncionario() {
        return codigoFuncionario;
    }

    public void setCodigoFuncionario(String codigoFuncionario) {
        this.codigoFuncionario = codigoFuncionario;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    // Sobrescreve o método consultarDados da classe Usuario para incluir dados específicos do funcionário
    @Override
    public String consultarDados() {
        return super.consultarDados() + "\nCódigo do Funcionário: " + codigoFuncionario + "\nCargo: " + cargo;
    }
}