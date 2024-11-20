package Views;

import Controllers.RelatorioController;
import javax.swing.*;
import java.awt.*;

public class RelatorioView extends JFrame {

    private JTextArea txtAreaRelatorio;
    private JButton btnGerarRelatorio;

    private RelatorioController relatorioController;

    public RelatorioView() {
        setTitle("Visualizar Relatórios");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        relatorioController = new RelatorioController();

        txtAreaRelatorio = new JTextArea();
        txtAreaRelatorio.setEditable(false);
        JScrollPane scroll = new JScrollPane(txtAreaRelatorio);

        btnGerarRelatorio = new JButton("Gerar Relatório");

        btnGerarRelatorio.addActionListener(e -> {
            String relatorio = relatorioController.gerarRelatorio();
            txtAreaRelatorio.setText(relatorio);
        });

        add(scroll, BorderLayout.CENTER);
        add(btnGerarRelatorio, BorderLayout.SOUTH);
    }
}
