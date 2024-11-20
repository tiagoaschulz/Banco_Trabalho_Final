package Views;

import Controllers.ClienteController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class ClienteView extends JFrame {

    private JTextField txtNome, txtCpf, txtEndereco, txtTelefone, txtDataNascimento, txtSenha;
    private JButton btnAdicionar, btnEditar, btnExcluir, btnListar;

    private ClienteController ClienteController;

    public ClienteView() {
        setTitle("Gerenciar Clientes");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ClienteController = new ClienteController();

        // Layout e componentes
        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(6, 2)); // Ajustado para incluir mais campos

        painel.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        painel.add(txtNome);

        painel.add(new JLabel("CPF:"));
        txtCpf = new JTextField();
        painel.add(txtCpf);

        painel.add(new JLabel("Data Nascimento (YYYY-MM-DD):"));
        txtDataNascimento = new JTextField();
        painel.add(txtDataNascimento);

        painel.add(new JLabel("Telefone:"));
        txtTelefone = new JTextField();
        painel.add(txtTelefone);

        painel.add(new JLabel("Endereço:"));
        txtEndereco = new JTextField();
        painel.add(txtEndereco);

        painel.add(new JLabel("Senha:"));
        txtSenha = new JTextField();
        painel.add(txtSenha);

        btnAdicionar = new JButton("Adicionar");
        btnEditar = new JButton("Editar");
        btnExcluir = new JButton("Excluir");
        btnListar = new JButton("Listar");

        painel.add(btnAdicionar);
        painel.add(btnEditar);
        painel.add(btnExcluir);
        painel.add(btnListar);

        // Ação para adicionar cliente
        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Chama o método adicionarCliente da instância ClienteController
                    LocalDate dataNascimento = LocalDate.parse(txtDataNascimento.getText());  // Converte data
                    ClienteController.adicionarCliente(
                            txtNome.getText(),
                            txtCpf.getText(),
                            dataNascimento.toString(),  // Passa a data de nascimento no formato correto
                            txtTelefone.getText(),
                            txtEndereco.getText(),
                            txtSenha.getText()
                    );
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao adicionar cliente: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Ação para editar cliente
        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(txtCpf.getText());  // Supondo que o CPF seja o ID para editar
                    LocalDate dataNascimento = LocalDate.parse(txtDataNascimento.getText());  // Converte data
                    ClienteController.editarCliente(
                            id,
                            txtNome.getText(),
                            txtCpf.getText(),
                            dataNascimento.toString(),  // Passa a data de nascimento no formato correto
                            txtTelefone.getText(),
                            txtEndereco.getText(),
                            txtSenha.getText()
                    );
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao editar cliente: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Ação para excluir cliente
        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(txtCpf.getText());  // Assume que o CPF seja o ID do cliente para exclusão
                    ClienteController.excluirCliente(id);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao excluir cliente: ID inválido", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Ação para listar clientes
        btnListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClienteController.listarClientes();
            }
        });

        add(painel, BorderLayout.CENTER);
    }
}
