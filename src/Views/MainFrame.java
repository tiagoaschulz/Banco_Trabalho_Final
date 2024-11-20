package Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("Sistema Bancário");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Menu
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");

        JMenuItem itemContas = new JMenuItem("Gerenciar Contas");
        JMenuItem itemClientes = new JMenuItem("Gerenciar Clientes");
        JMenuItem itemRelatorios = new JMenuItem("Visualizar Relatórios");

        menu.add(itemContas);
        menu.add(itemClientes);
        menu.add(itemRelatorios);
        menuBar.add(menu);

        setJMenuBar(menuBar);

        // acoes do menu
        itemContas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ContaView().setVisible(true);
                setVisible(false); // Fecha a janela principal
            }
        });

        itemClientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ClienteView().setVisible(true);
                setVisible(false);
            }
        });

        itemRelatorios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RelatorioView().setVisible(true);
                setVisible(false);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
}
