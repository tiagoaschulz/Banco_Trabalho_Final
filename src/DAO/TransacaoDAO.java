package DAO;

import Util.Conexao;
import Models.Transacao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransacaoDAO {

    // Registrar uma nova transação
    public boolean inserir(Transacao transacao) {
        String sql = "INSERT INTO transacoes (conta_id, valor, tipo, data) VALUES (?, ?, ?, ?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, transacao.getContaId());
            stmt.setDouble(2, transacao.getValor());
            stmt.setString(3, transacao.getTipo());
            stmt.setTimestamp(4, new Timestamp(transacao.getData().getTime()));

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // busca de entre todas as transações de uma conta
    public List<Transacao> buscarPorContaId(int contaId) {
        List<Transacao> transacoes = new ArrayList<>();
        String sql = "SELECT * FROM transacoes WHERE conta_id = ? ORDER BY data DESC";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, contaId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Transacao transacao = new Transacao(
                            rs.getInt("id"),
                            rs.getInt("conta_id"),
                            rs.getDouble("valor"),
                            rs.getString("tipo"),
                            rs.getTimestamp("data")
                    );
                    transacoes.add(transacao);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transacoes;
    }

    // busca de todas as transações
    public List<Transacao> buscarTodas() {
        List<Transacao> transacoes = new ArrayList<>();
        String sql = "SELECT * FROM transacoes ORDER BY data DESC";

        try (Connection conn = Conexao.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Transacao transacao = new Transacao(
                        rs.getInt("id"),
                        rs.getInt("conta_id"),
                        rs.getDouble("valor"),
                        rs.getString("tipo"),
                        rs.getTimestamp("data")
                );
                transacoes.add(transacao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transacoes;
    }
}
