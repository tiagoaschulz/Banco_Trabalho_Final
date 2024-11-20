package Controllers;

import Models.Conta;
import Models.Cliente;
import Models.Conta.TipoConta;
import Models.Conta_Corrente; // Supondo que você tenha uma subclasse concreta
import Models.Conta_Poupanca; // Outra subclasse concreta, se necessário
import DAO.ContaDAO;
import javax.swing.*;
import java.time.LocalDate;
import java.util.List;

public class ContaController {

    private ContaDAO contaDAO;

    public ContaController() {
        this.contaDAO = new ContaDAO(); // Inicializa o DAO de Conta
    }

    // Adicionar nova conta
    public void adicionarConta(String numero, String agencia, String tipoContaStr, Cliente cliente, double limite, String dataVencimentoStr, double taxaRendimento) {
        try {
            int numeroConta = Integer.parseInt(numero);  // Converte número da conta
            TipoConta tipoConta = TipoConta.valueOf(tipoContaStr.toUpperCase());  // Converte o tipo de conta

            LocalDate dataVencimento = LocalDate.parse(dataVencimentoStr);  // Converte a string para LocalDate

            Conta novaConta;

            // Inicializa a conta com base no tipo
            if (tipoConta == TipoConta.CORRENTE) {
                // Para Conta Corrente, usa limite e data de vencimento
                novaConta = new Conta_Corrente(numeroConta, agencia, cliente, limite, dataVencimento, tipoConta);
            } else if (tipoConta == TipoConta.POUPANCA) {
                // Para Conta Poupança, usa taxa de rendimento
                novaConta = new Conta_Poupanca(numeroConta, agencia, cliente, taxaRendimento, tipoConta);
            } else {
                throw new IllegalArgumentException("Tipo de conta inválido.");
            }

            // Chama o DAO para inserir a nova conta
            boolean sucesso = contaDAO.inserir(novaConta);
            if (sucesso) {
                JOptionPane.showMessageDialog(null, "Conta adicionada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao adicionar conta.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "O número da conta deve ser um número válido!", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro inesperado: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }


    // Editar conta existente
    public void editarConta(String numero, String agencia, String tipoContaStr, double saldo) {
        try {
            int numeroConta = Integer.parseInt(numero);
            Conta contaEditada = ContaDAO.buscarPorNumero(numeroConta);

            if (contaEditada != null) {
                contaEditada.setAgencia(agencia);
                contaEditada.depositar(saldo - contaEditada.getSaldo()); // Ajusta o saldo
                contaEditada.setTipoConta(TipoConta.valueOf(tipoContaStr.toUpperCase()));

                boolean sucesso = contaDAO.atualizar(contaEditada);
                if (sucesso) {
                    JOptionPane.showMessageDialog(null, "Conta editada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao editar conta.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Conta não encontrada.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "O número da conta deve ser um número válido!", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro inesperado: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Excluir conta
    public void excluirConta(String numero) {
        try {
            int numeroConta = Integer.parseInt(numero);
            boolean sucesso = contaDAO.remover(numeroConta);

            if (sucesso) {
                JOptionPane.showMessageDialog(null, "Conta excluída com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao excluir conta.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "O número da conta deve ser um número válido!", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro inesperado: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Listar todas as contas
    public void listarContas() {
        try {
            List<Conta> contas = contaDAO.buscarTodas();
            if (contas.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Não há contas cadastradas.", "Informação", JOptionPane.INFORMATION_MESSAGE);
            } else {
                for (Conta conta : contas) {
                    conta.consultarDadosConta();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar contas: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
