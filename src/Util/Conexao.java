package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String URL = "jdbc:mysql://localhost:3306/banco";
    private static final String USER = "root";
    private static final String PASSWORD = "dan-kuso";

    public static Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            throw e;
        }
    }

    public static void main(String[] args) {
        try {
            Connection conexao = Conexao.getConnection();
            System.out.println("Conexão realizada com sucesso: " + conexao);
            conexao.close();
        } catch (SQLException e) {
            System.out.println("Falha ao conectar: " + e.getMessage());
        }
    }
}
