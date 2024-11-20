package Views;

import DAO.ContaDAO;
import Models.Conta;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ContaView extends JFrame {
    private JTextField txtNumeroConta, txtSaldo, txtTipoConta, txtId;
    private JTextField txtFiltroNumero, txtFiltroTipo, txtFiltroSaldoMin, txtFiltroSaldoMax;
    private JButton btnAdicionar, btnAtualizarLista, btnEditar, btnExcluir, btnAplicarFiltro, btnLimparFiltro;
    private JTable tabelaContas;
    private DefaultTableModel tabelaModelo;

    private ContaDAO contaDAO;

    public ContaView() {
        // janela principal
        setTitle("Gerenciamento de Contas Bancárias");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        contaDAO = new ContaDAO();

        // Painel de Formulário
        JPanel painelFormulario = new JPanel(new GridLayout(5, 2, 10, 10));
        painelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        painelFormulario.add(new JLabel("ID (Para Editar):"));
        txtId = new JTextField();
        txtId.setEditable(false);
        painelFormulario.add(txtId);

        painelFormulario.add(new JLabel("Número da Conta:"));
        txtNumeroConta = new JTextField();
        painelFormulario.add(txtNumeroConta);

        painelFormulario.add(new JLabel("Saldo:"));
        txtSaldo = new JTextField();
        painelFormulario.add(txtSaldo);

        painelFormulario.add(new JLabel("Tipo de Conta:"));
        txtTipoConta = new JTextField();
        painelFormulario.add(txtTipoConta);

        btnAdicionar = new JButton("Adicionar Conta");
        painelFormulario.add(btnAdicionar);

        btnAtualizarLista = new JButton("Atualizar Lista");
        painelFormulario.add(btnAtualizarLista);

        btnEditar = new JButton("Editar Conta");
        painelFormulario.add(btnEditar);

        btnExcluir = new JButton("Excluir Conta");
        painelFormulario.add(btnExcluir);

        add(painelFormulario, BorderLayout.NORTH);

        // filtros
        JPanel painelFiltros = new JPanel(new GridLayout(2, 5, 10, 10));
        painelFiltros.setBorder(BorderFactory.createTitledBorder("Filtros"));

        painelFiltros.add(new JLabel("Número da Conta:"));
        txtFiltroNumero = new JTextField();
        painelFiltros.add(txtFiltroNumero);

        painelFiltros.add(new JLabel("Tipo de Conta:"));
        txtFiltroTipo = new JTextField();
        painelFiltros.add(txtFiltroTipo);

        painelFiltros.add(new JLabel("Saldo Mínimo:"));
        txtFiltroSaldoMin = new JTextField();
        painelFiltros.add(txtFiltroSaldoMin);

        painelFiltros.add(new JLabel("Saldo Máximo:"));
        txtFiltroSaldoMax = new JTextField();
        painelFiltros.add(txtFiltroSaldoMax);

        btnAplicarFiltro = new JButton("Aplicar Filtro");
        painelFiltros.add(btnAplicarFiltro);

        btnLimparFiltro = new JButton("Limpar Filtros");
        painelFiltros.add(btnLimparFiltro);

        add(painelFiltros, BorderLayout.SOUTH);

        // tabela exibir contas
        tabelaModelo = new DefaultTableModel(new String[]{"ID", "Número", "Saldo", "Tipo", "Data Criação"}, 0);
        tabelaContas = new JTable(tabelaModelo);
        JScrollPane scrollPane = new JScrollPane(tabelaContas);
        add(scrollPane, BorderLayout.CENTER);

        // ações dos botões
        configurarEventos();

        // carregar contas na tabela ao iniciar
        carregarContasNaTabela();
    }

    private void configurarEventos() {
        btnAdicionar.addActionListener(e -> adicionarConta());
        btnAtualizarLista.addActionListener(e -> carregarContasNaTabela());
        btnEditar.addActionListener(e -> editarConta());
        btnExcluir.addActionListener(e -> excluirConta());
        btnAplicarFiltro.addActionListener(e -> aplicarFiltros());
        btnLimparFiltro.addActionListener(e -> {
            limparFiltros();
            carregarContasNaTabela();
        });

        tabelaContas.getSelectionModel().addListSelectionListener(e -> carregarCamposParaEdicao());
    }

    private void adicionarConta() {

    }

    private void carregarContasNaTabela() {
        List<Conta> contas = contaDAO.buscarTodas();
        tabelaModelo.setRowCount(0); // Limpar tabela
        for (Conta conta : contas) {
            tabelaModelo.addRow(new Object[]{
                    conta.getId(),
                    conta.getNumeroConta(),
                    conta.getSaldo(),
                    conta.getTipoConta(),
                    conta.getDataCriacao()
            });
        }
    }

    private void aplicarFiltros() {
        String numero = txtFiltroNumero.getText();
        String tipo = txtFiltroTipo.getText();
        String saldoMinText = txtFiltroSaldoMin.getText();
        String saldoMaxText = txtFiltroSaldoMax.getText();

        Double saldoMin = null;
        Double saldoMax = null;

        try {
            if (!saldoMinText.isEmpty()) saldoMin = Double.parseDouble(saldoMinText);
            if (!saldoMaxText.isEmpty()) saldoMax = Double.parseDouble(saldoMaxText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Valores de saldo inválidos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        List<Conta> contasFiltradas = contaDAO.buscarComFiltros(numero, tipo, saldoMin, saldoMax);
        tabelaModelo.setRowCount(0); // Limpar tabela
        for (Conta conta : contasFiltradas) {
            tabelaModelo.addRow(new Object[]{
                    conta.getId(),
                    conta.getNumeroConta(),
                    conta.getSaldo(),
                    conta.getTipoConta(),
                    conta.getDataCriacao()
            });
        }
    }

    private void limparFiltros() {
        txtFiltroNumero.setText("");
        txtFiltroTipo.setText("");
        txtFiltroSaldoMin.setText("");
        txtFiltroSaldoMax.setText("");
    }

    private void editarConta() {

    }

    private void excluirConta() {

    }

    private void carregarCamposParaEdicao() {

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ContaView contaView = new ContaView();
            contaView.setVisible(true);
        });
    }
}