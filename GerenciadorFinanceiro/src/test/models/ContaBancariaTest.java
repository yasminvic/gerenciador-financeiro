package test.models;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

import models.ContaBancaria;
import models.Despesa;
import models.MovimentoFinanceiro;
import models.Receita;
import models.enums.Categoria;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContaBancariaTest {
    
    public ContaBancariaTest() {
    }

    /**
     * Teste de getSaldo.
     */
    @Test
    public void testeGetSaldo() {
        System.out.println("getSaldo");
        ContaBancaria instance = new ContaBancaria();
        Double expResult = 0.0;
        Double result = instance.getSaldo();
        assertEquals(expResult, result);
    }

    /**
     * Teste de listar.
     */
    @Test
    public void testeListar() {
        System.out.println("listar");
        ContaBancaria instance = new ContaBancaria();
        ArrayList<MovimentoFinanceiro> expResult = new ArrayList<>();
        ArrayList<MovimentoFinanceiro> result = instance.listar();
        assertEquals(expResult, result);
    }

    /**
     * Teste de incluir.
     */
    @Test
    public void testeIncluir() {
        System.out.println("incluir");
        MovimentoFinanceiro movimentoFinanceiro = new Despesa("teste", Categoria.ALIMENTACAO, LocalDate.now(), 12d);
        ContaBancaria instance = new ContaBancaria();
        instance.incluir(movimentoFinanceiro);      
        assertNotNull(instance.listar());
    }

    /**
     * Teste de consultarSaldo.
     */
    @Test
    public void testeConsultarSaldo() {
        System.out.println("consultarSaldo");
        LocalDate dataFiltro = LocalDate.now();
        ContaBancaria instance = new ContaBancaria();
        MovimentoFinanceiro despesa1 = new Despesa("despesa1", Categoria.FERIAS, LocalDate.of(2024, 04, 12), 12d);
        MovimentoFinanceiro receita1 = new Receita("receita1", Categoria.SALARIO, LocalDate.of(2025, 01, 01), 100d);
        instance.incluir(receita1);
        instance.incluir(despesa1);
        double expResult = -12;
        double result = instance.consultarSaldo(dataFiltro);
        assertEquals(expResult, result, 0);
    }

    /**
     * Teste de criarArquivo.
     */
    @Test
    public void testeCriarArquivo() {
        System.out.println("criarArquivo");
        ContaBancaria instance = new ContaBancaria();
        instance.criarArquivo();   
        File arquivo = new File("arquivoContaBancaria.csv");   
        boolean arquivoFoiCriado = arquivo.exists();
        
        assertTrue(arquivoFoiCriado);
        assertTrue(arquivo.length() > 0);
    }

    /**
     * Teste de salvarArquivo.
     */
    @Test
    public void testeSalvarArquivo() {
        System.out.println("salvarArquivo");
        File arquivo = new File("teste.txt");
        ContaBancaria instance = new ContaBancaria();
        instance.salvarArquivo(arquivo);
        assertTrue(arquivo.length() > 0);
    }

    /**
     * Teste de lerArquivo.
     */
    @Test
    public void tesetLerArquivo() {
        System.out.println("lerArquivo");
        File arquivo = new File("teste.txt");
        ContaBancaria instance = new ContaBancaria();
        instance.lerArquivo(arquivo);        
        assertNotNull(instance.listar());
        assertTrue(arquivo.length() > 0);
    }
    
}
