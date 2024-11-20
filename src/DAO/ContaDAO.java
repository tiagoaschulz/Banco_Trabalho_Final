package DAO;

import Models.Cliente;
import Models.Conta;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContaDAO {

    // Método para obter conexão com o banco de dados
    private Connection obterConexao() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/banco", "root", "dan-kuso");
    }

    // Inserir uma nova conta
    public boolean inserir(Conta conta) {
        String sql = "INSERT INTO Conta (numero_conta, agencia, saldo, tipo_conta, id_cliente) VALUES (?, ?, ?, ?, ?)";
        try (Connection conexao = obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, conta.getNumero());
            stmt.setString(2, conta.getAgencia());
            stmt.setDouble(3, conta.getSaldo());
            stmt.setString(4, conta.getTipoConta().name());
            stmt.setInt(5, conta.getCliente().getId()); // Supondo que o cliente tem um atributo `id`

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("Erro ao inserir conta: " + e.getMessage());
            return false;
        }
    }

    // Atualizar uma conta existente
    public boolean atualizar(Conta conta) {
        String sql = "UPDATE Conta SET agencia = ?, saldo = ?, tipo_conta = ?, id_cliente = ? WHERE numero_conta = ?";
        try (Connection conexao = obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, conta.getAgencia());
            stmt.setDouble(2, conta.getSaldo());
            stmt.setString(3, conta.getTipoConta().name());
            stmt.setInt(4, conta.getCliente().getId());
            stmt.setInt(5, conta.getNumero());

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar conta: " + e.getMessage());
            return false;
        }
    }

    // Remover uma conta pelo número
    public boolean remover(int numeroConta) {
        String sql = "DELETE FROM Conta WHERE numero_conta = ?";
        try (Connection conexao = obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, numeroConta);
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("Erro ao remover conta: " + e.getMessage());
            return false;
        }
    }

    // Buscar todas as contas
    public List<Conta> buscarTodas() {
        String sql = "SELECT * FROM Conta";
        List<Conta> contas = new ArrayList<>();

        try (Connection conexao = obterConexao();
             Statement stmt = conexao.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Conta conta = new Conta(
                        rs.getInt("numero_conta"),
                        rs.getString("agencia"),
                        rs.getDouble("saldo"),
                        Conta.TipoConta.valueOf(rs.getString("tipo_conta")),
                        new Cliente(rs.getInt("id_cliente")) // Simples instância do cliente, ajuste conforme necessário
                ) {
                    @Override
                    public void consultarDados() {
                        System.out.println("Consulta personalizada da ContaDAO.");
                    }
                };
                contas.add(conta);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar contas: " + e.getMessage());
        }
        return contas;
    }

    // Buscar contas com filtros dinâmicos
    public List<Conta> buscarComFiltros(String agencia, Conta.TipoConta tipoConta, Double saldoMin, Double saldoMax) {
        StringBuilder sql = new StringBuilder("SELECT * FROM Conta WHERE 1=1");
        List<Conta> contas = new ArrayList<>();

        if (agencia != null && !agencia.isEmpty()) {
            sql.append(" AND agencia = ?");
        }
        if (tipoConta != null) {
            sql.append(" AND tipo_conta = ?");
        }
        if (saldoMin != null) {
            sql.append(" AND saldo >= ?");
        }
        if (saldoMax != null) {
            sql.append(" AND saldo <= ?");
        }

        try (Connection conexao = obterConexao();
             PreparedStatement stmt = conexao.prepareStatement(sql.toString())) {

            int index = 1;

            if (agencia != null && !agencia.isEmpty()) {
                stmt.setString(index++, agencia);
            }
            if (tipoConta != null) {
                stmt.setString(index++, tipoConta.name());
            }
            if (saldoMin != null) {
                stmt.setDouble(index++, saldoMin);
            }
            if (saldoMax != null) {
                stmt.setDouble(index++, saldoMax);
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Conta conta = new Conta(
                            rs.getInt("numero_conta"),
                            rs.getString("agencia"),
                            rs.getDouble("saldo"),
                            Conta.TipoConta.valueOf(rs.getString("tipo_conta")),
                            new Cliente(rs.getInt("id_cliente")) // Simples instância do cliente, ajuste conforme necessário
                    ) {
                        @Override
                        public void consultarDados() {
                            System.out.println("Consulta personalizada da ContaDAO.");
                        }
                    };
                    contas.add(conta);
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar contas com filtros: " + e.getMessage());
        }
        return contas;
    }

}
