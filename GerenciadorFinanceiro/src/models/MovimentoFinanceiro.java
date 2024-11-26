package models;
import models.enums.Categoria;
import java.time.LocalDate;
import java.util.Objects;

public abstract class MovimentoFinanceiro {

    private String nome;
    private LocalDate data;
    private Categoria categoria;
    private Double valor;

    public MovimentoFinanceiro(String nome, Categoria categoria, LocalDate data, Double valor) {
        this.setNome(nome);
        this.setCategoria(categoria);
        this.setData(data);
        this.setValor(valor);
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome.isBlank() || nome.isEmpty()) {
            throw new IllegalArgumentException("Nome não pode estar vazio");
        }
        this.nome = nome;
    }

    public LocalDate getData() {
        return this.data;
    }

    public void setData(LocalDate data) {
        if (data == null) {
            throw new IllegalArgumentException("Data não deve estar vazia");
        }
        this.data = data;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        if (categoria == null) {
            throw new IllegalArgumentException("Categoria não pode estar vazia");
        }
        this.categoria = categoria;
    }


    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        if (valor < 0  || valor == null) {
            throw new IllegalArgumentException("Valor inválido");
        }
        this.valor = valor;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MovimentoFinanceiro that = (MovimentoFinanceiro) obj;
        return nome.equals(that.nome) &&
            data.equals(that.data) &&
            categoria == that.categoria &&
            valor.equals(that.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, data, categoria, valor);
    }
    
    @Override
    public String toString() {
    	return "Descrição: " + nome + "\n" +
    			"Data: " + data + "\n" +
    			"Categoria: " + categoria + "\n" +
    			"Valor: " + valor + "\n" 
    			;

    }

}
