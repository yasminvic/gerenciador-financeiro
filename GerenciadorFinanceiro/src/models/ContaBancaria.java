package models;

import models.enums.Categoria;

import java.awt.event.FocusAdapter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.DatabaseMetaData;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class ContaBancaria implements IGerenciamentoContaBancaria {

    private ArrayList<MovimentoFinanceiro> movimentoFinanceiro = new ArrayList<>();
    private Double saldo = 0.0;

    public ContaBancaria() {
    }

    public Double getSaldo() {
        return saldo;
    }

    /**
     * retorna a lista de movimentos ordenada por crescente de data
     */
    @Override
    public ArrayList<MovimentoFinanceiro> listar() {
        Collections.sort(movimentoFinanceiro, Comparator.comparing(MovimentoFinanceiro::getData));
        return movimentoFinanceiro;
    }
    
    /**
     * inclui um movimento e adiciona ou subtrai do saldo considerando
     * até a data atual.
     * @param movimentoFinanceiro movimento a ser adicionado
     */
    @Override
    public void incluir(MovimentoFinanceiro movimentoFinanceiro) {
        if (!this.movimentoFinanceiro.contains(movimentoFinanceiro)) {
            if (movimentoFinanceiro.getData().compareTo(LocalDate.now()) <= 0) {
                if (movimentoFinanceiro instanceof Despesa) {
                    saldo -= ((Despesa) movimentoFinanceiro).getValor();
                }
                if (movimentoFinanceiro instanceof Receita) {
                    saldo += ((Receita) movimentoFinanceiro).getValor();
                }
            }
            this.movimentoFinanceiro.add(movimentoFinanceiro);
        }
    }

    /**
     * filtra o saldo com base na data informada
     * @param dataFiltro data na qual será feito o filtro
     * @return o valor
     */
    public double consultarSaldo(LocalDate dataFiltro) {
    	double valor = 0;
        for (MovimentoFinanceiro movi : movimentoFinanceiro) {
        	
        	if(movi.getData().compareTo(dataFiltro) <= 0 || dataFiltro == null) {
        		if (movi instanceof Despesa) {
                    valor -= movi.getValor();
                } else {
                    valor += movi.getValor();
                }
        	}
            
        }
        return valor;
    }
    
    /**
     * cria o arquivo e salva as informações nele
     */
    public void criarArquivo() {
        try {            
            File arquivo = new File("arquivoContaBancaria.csv");

            salvarArquivo(arquivo);
            
        } catch (NullPointerException e) {
            System.err.println("Erro ao criar o arquivo CSV: " + e.getMessage());
        }
    }

    /**
     * salva as informações no local escolhido
     * @param arquivo local para salvar informações
     */
    public void salvarArquivo(File arquivo) {
        try {
            PrintWriter arquivoEscrita = new PrintWriter(arquivo);

            // cabeçalho
            arquivoEscrita.println("tipo;nome;data;categoria;valor");

            // dados
            for (MovimentoFinanceiro movimento : movimentoFinanceiro) {
                arquivoEscrita.println(
                    movimento.getClass().getSimpleName().toLowerCase()+";"+
                    movimento.getNome()+";"+
                    movimento.getData().toString()+";"+
                    movimento.getCategoria()+";"+
                    movimento.getValor()
                );
            }

            arquivoEscrita.close();
            
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado");
        }
    }

    /**
     * faz a leitura do arquivo e adiciona as informações na lista de movimentos
     * @param arquivo local da leitura
     */
    public void lerArquivo(File arquivo) {
        try {
            Scanner arquivoScanner = new Scanner(arquivo, "UTF-8");
            
            // verifica se tem mais linhas do que só o cabeçalho
            if (arquivoScanner.hasNextLine()) {
                String cabecalho = arquivoScanner.nextLine(); // pula o cabeçalho

                if (!cabecalho.equals("tipo;nome;data;categoria;valor")) {
                    arquivoScanner.close();
                    throw new IllegalArgumentException("Arquivo não é compativel");
                }

                while (arquivoScanner.hasNextLine()) {
                    String linha = arquivoScanner.nextLine(); // pega a proxima linha com informações
                    String[] informacoes = linha.split(";"); // separa a string onde tem ";" em uma lista
                    
                    String tipo = informacoes[0];
                    String nome = informacoes[1];
                    LocalDate data = LocalDate.parse(informacoes[2]);
                    Categoria categoria = Categoria.valueOf(informacoes[3]);
                    Double valor = Double.valueOf(informacoes[4]);
                    
                    MovimentoFinanceiro movimento;

                    if (tipo.equals("despesa")) {
                        movimento = new Despesa(nome, categoria, data, valor);
                    } else {
                        movimento = new Receita(nome, categoria, data, valor);
                    }                    
                    this.incluir(movimento);
                }
                
                arquivoScanner.close();
            }
            
        } catch (FileNotFoundException  e) {
            System.out.println("Arquivo não encontrado");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
