package Controllers;

import Models.Cliente;
import DAO.ClienteDAO;
import Models.Endereco;
import javax.swing.*;
import java.time.LocalDate;
import java.util.List;

public class ClienteController {

    private ClienteDAO clienteDAO;

    public ClienteController() {
        this.clienteDAO = new ClienteDAO();
    }

    // Adicionar novo cliente
    public static void adicionarCliente(String nome, String cpf, String dataNascimento, String telefone, String enderecoText, String senha) {
        try {
            LocalDate data = LocalDate.parse(dataNascimento); // Validar data
            Endereco endereco = new Endereco(enderecoText); // Presume um construtor básico
            Cliente novoCliente = new Cliente(0, nome, cpf, data, telefone, endereco, senha); // ID é gerado automaticamente
            boolean sucesso = ClienteDAO.inserir(novoCliente);
            if (sucesso) {
                JOptionPane.showMessageDialog(null, "Cliente adicionado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao adicionar cliente.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro inesperado: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Editar cliente existente
    public static void editarCliente(int id, String nome, String cpf, String dataNascimento, String telefone, String enderecoText, String senha) {
        try {
            Cliente clienteEditado = ClienteDAO.buscarPorId(id);
            if (clienteEditado != null) {
                clienteEditado.setNome(nome);
                clienteEditado.setCpf(cpf);
                clienteEditado.setDataNascimento(LocalDate.parse(dataNascimento));
                clienteEditado.setTelefone(telefone);
                clienteEditado.setEndereco(new Endereco(enderecoText));
                clienteEditado.setSenha(senha);

                boolean sucesso = ClienteDAO.atualizar(clienteEditado);
                if (sucesso) {
                    JOptionPane.showMessageDialog(null, "Cliente editado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao editar cliente.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Cliente não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro inesperado: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Excluir cliente
    public static void excluirCliente(int id) {
        try {
            boolean sucesso = ClienteDAO.remover(id);
            if (sucesso) {
                JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao excluir cliente.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro inesperado: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Listar todos os clientes
    public static void listarClientes() {
        try {
            List<Cliente> clientes = ClienteDAO.buscarTodos();
            if (clientes.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Não há clientes cadastrados.", "Informação", JOptionPane.INFORMATION_MESSAGE);
            } else {
                for (Cliente cliente : clientes) {
                    System.out.println(cliente.consultarDados());
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar clientes: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
