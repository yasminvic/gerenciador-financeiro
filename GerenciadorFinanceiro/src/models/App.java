package models;

import models.enums.Categoria;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class App {
    public static void main(String[] args) {
        ContaBancaria conta = new ContaBancaria();
        MovimentoFinanceiro des1 = new Despesa("despesa1", Categoria.ALIMENTACAO, LocalDate.of(2024, 11, 1), 12.0);
        conta.incluir(des1);
        MovimentoFinanceiro des2 = new Despesa("despesa2", Categoria.ALIMENTACAO, LocalDate.of(2024, 12, 1), 250.0);
        conta.incluir(des2);
        MovimentoFinanceiro rec1 = new Receita("receita1", Categoria.DECIMO_TERCEIRO, LocalDate.of(2024, 11, 1), 100.0);
        conta.incluir(rec1);
        MovimentoFinanceiro rec2 = new Receita("receita2", Categoria.SALARIO, LocalDate.now(), 1000.0);
        conta.incluir(rec2);
        conta.criarArquivo();

        FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("selecione o arquivo csv", "csv");
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(fileFilter);
        
        int retorno = chooser.showOpenDialog(chooser);
        File arquivo = chooser.getSelectedFile();
        
        if (retorno == JFileChooser.APPROVE_OPTION && arquivo.getName().toLowerCase().endsWith(".csv")) {
            conta.lerArquivo(arquivo);
            conta.salvarArquivo(arquivo);
        }

        String textoData = "01/01/2024";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate data = LocalDate.parse(textoData, formatter);

        Double valor = conta.consultarSaldoAtual();
        valor = conta.consultarSaldoPeriodo();

        var teste = conta.listar();
    }
}
