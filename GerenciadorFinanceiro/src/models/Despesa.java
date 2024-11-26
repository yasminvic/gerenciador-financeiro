package models;
import models.enums.Categoria;
import java.time.LocalDate;

public class Despesa extends MovimentoFinanceiro {
    private String nome;
    private LocalDate data;
    private Categoria categoria;
    private Double valor;

    public Despesa(String nome, Categoria categoria, LocalDate data, Double valor) {
        super(nome, categoria, data, valor);
    }
}
