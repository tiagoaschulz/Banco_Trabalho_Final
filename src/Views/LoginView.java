package Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginFrame extends JFrame {

    private JTextField passwordField;
    private JButton btnFuncionario, btnCliente;

    public LoginFrame() {
        setTitle("Tela de Login");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        setLayout(new BorderLayout());

        // Painel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

        // Adicionando a senha
        JLabel lblSenha = new JLabel("Digite a senha:", SwingConstants.CENTER);
        passwordField = new JPasswordField(15);
        passwordField.setEchoChar('*');
        JPanel senhaPanel = new JPanel();
        senhaPanel.add(lblSenha);
        senhaPanel.add(passwordField);

        // Botões
        btnFuncionario = new JButton("Funcionário");
        btnCliente = new JButton("Cliente");

        // Ação para Funcionário
        btnFuncionario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                autenticar("funcionario");
            }
        });

        // Ação para Cliente
        btnCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                autenticar("cliente");
            }
        });

        // Adicionando ao painel
        panel.add(senhaPanel);
        panel.add(btnFuncionario);
        panel.add(btnCliente);

        add(panel, BorderLayout.CENTER);
    }

    // autenticar
    private void autenticar(String tipoUsuario) {
        String senha = new String(passwordField.getPassword());

        //senha teste
        String senhaFuncionario = "admin123";
        String senhaCliente = "cliente123";

        if (tipoUsuario.equals("funcionario") && senha.equals(senhaFuncionario)) {
            // Autenticação bem-sucedida para funcionário
            JOptionPane.showMessageDialog(this, "Acesso concedido para Funcionário.");
            new MainFrame("funcionario").setVisible(true);  // Acessa o menu do Funcionário
            dispose();
        } else if (tipoUsuario.equals("cliente") && senha.equals(senhaCliente)) {
            // Autenticação bem-sucedida para cliente
            JOptionPane.showMessageDialog(this, "Acesso concedido para Cliente.");
            new MainFrame("cliente").setVisible(true);  // Acessa o menu do Cliente
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Senha incorreta! Tente novamente.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginFrame().setVisible(true);
            }
        });
    }
}