package Models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Relatorio {

    // Atributos da classe Relatorio
    private String tipo;  // Tipo do relatório (Ex: Relatório de Transações, Relatório de Movimentações)
    private LocalDateTime dataGeracao;  // Data de geração do relatório
    private List<String> dados;  // Lista contendo as informações do relatório

    // Construtor
    public Relatorio(String tipo) {
        this.tipo = tipo;
        this.dataGeracao = LocalDateTime.now();  // A data de geração é o momento atual
        this.dados = new ArrayList<>();
    }

    // Método para gerar um relatório geral
    public void gerarRelatorioGeral() {
        // Simula a geração de um relatório com dados fictícios
        System.out.println("Gerando Relatório Geral...");
        dados.add("Relatório do tipo: " + tipo);
        dados.add("Data de Geração: " + dataGeracao.toString());
        dados.add("Transações realizadas: 100");
        dados.add("Total movimentado: R$ 500.000,00");
        System.out.println("Relatório gerado com sucesso!");
    }

    // Método para exportar o relatório para um arquivo Excel
    public void exportarParaExcel() {
        System.out.println("Exportando relatório para Excel...");

        //Apache POI para exportar os dados para um arquivo Excel
        try {

            org.apache.poi.ss.usermodel.Workbook workbook = new org.apache.poi.xssf.usermodel.XSSFWorkbook();
            org.apache.poi.ss.usermodel.Sheet sheet = workbook.createSheet("Relatório");

            org.apache.poi.ss.usermodel.Row titleRow = sheet.createRow(0);
            titleRow.createCell(0).setCellValue("Tipo");
            titleRow.createCell(1).setCellValue("Data de Geração");
            titleRow.createCell(2).setCellValue("Dados");


            org.apache.poi.ss.usermodel.Row row = sheet.createRow(1);
            row.createCell(0).setCellValue(tipo);
            row.createCell(1).setCellValue(dataGeracao.toString());
            row.createCell(2).setCellValue(String.join(", ", dados));

            // Salvando o arquivo Excel
            java.io.FileOutputStream fileOut = new java.io.FileOutputStream("relatorio.xlsx");
            workbook.write(fileOut);
            fileOut.close();

            // Fechando o workbook
            workbook.close();
            System.out.println("Relatório exportado para Excel com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao exportar o relatório para Excel.");
        }
    }

    // Getters e Setters
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime getDataGeracao() {
        return dataGeracao;
    }

    public void setDataGeracao(LocalDateTime dataGeracao) {
        this.dataGeracao = dataGeracao;
    }

    public List<String> getDados() {
        return dados;
    }

    public void setDados(List<String> dados) {
        this.dados = dados;
    }
}
