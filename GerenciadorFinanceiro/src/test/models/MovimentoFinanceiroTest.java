package test.models;

import java.time.LocalDate;
import models.Receita;
import models.Despesa;
import models.MovimentoFinanceiro;
import models.ContaBancaria;
import models.enums.Categoria;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MovimentoFinanceiroTest {
    
    public MovimentoFinanceiroTest() {
    }

    /**
     * Teste de getNome.
     */
    @Test
    public void testeGetNome() {
        System.out.println("getNome");
        MovimentoFinanceiro instance = new Despesa("teste", Categoria.EDUCACAO, LocalDate.MAX, Double.NaN);
        String expResult = "teste";
        String result = instance.getNome();
        assertEquals(expResult, result);
    }

    /**
     * Teste de setNome.
     */
    @Test
    public void testeSetNome() {
        System.out.println("setNome");
        String nome = "testeNome";
        MovimentoFinanceiro instance = new Despesa("testeNome", Categoria.SALARIO, LocalDate.MAX, Double.NaN);
        instance.setNome(nome);
        assertEquals(instance.getNome(), nome);
    }

    /**
     * Teste de erro setNome.
     */
    @Test
    public void testeErroSetNome() {
        System.out.println("erroSetNome");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                new Despesa("", Categoria.SALARIO, LocalDate.MAX, Double.NaN);
            }
        );
        String result = exception.getMessage();
        String expected = "Nome não pode estar vazio";
        assertEquals(expected, result);
    }
    
    /**
     * Teste de getData.
     */
    @Test
    public void testeGetData() {
        System.out.println("getData");
        MovimentoFinanceiro instance = new Despesa("nome", Categoria.SALARIO, LocalDate.of(2024, 01, 01), Double.NaN);
        LocalDate expResult = LocalDate.of(2024, 01, 01);
        LocalDate result = instance.getData();
        assertEquals(expResult, result);
    }

    /**
     * Teste de setData.
     */
    @Test
    public void testeSetData() {
        System.out.println("setData");
        LocalDate data = LocalDate.now();
        MovimentoFinanceiro instance = new Despesa("nome", Categoria.SALARIO, data, Double.NaN);
        assertEquals(instance.getData(), data);
    }
    
    /**
     * Teste de erro setData.
     */
    @Test
    public void testeErroSetData() {
        System.out.println("erroSetData");        
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                new Despesa("nome", Categoria.SALARIO, null, Double.NaN);
            }
        );
        String result = exception.getMessage();
        String expected = "Data não deve estar vazia";
        assertEquals(expected, result);
    }

    /**
     * Teste de getCategoria.
     */
    @Test
    public void testeGetCategoria() {
        System.out.println("getCategoria");
        MovimentoFinanceiro instance = new Despesa("nome", Categoria.SALARIO, LocalDate.MAX, Double.NaN);
        Categoria expResult = Categoria.SALARIO;
        Categoria result = instance.getCategoria();
        assertEquals(expResult, result);
    }

    /**
     * Teste de setCategoria.
     */
    @Test
    public void testSetCategoria() {
        System.out.println("setCategoria");
        Categoria categoria = Categoria.ALIMENTACAO;
        MovimentoFinanceiro instance = new Despesa("nome", categoria, LocalDate.MAX, Double.NaN);
        assertEquals(instance.getCategoria(), categoria);
    }
    
    /**
     * Teste de erro setCategoria.
     */
    @Test
    public void testErroSetCategoria() {
        System.out.println("erroSetCategoria");        
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                new Despesa("nome", null, LocalDate.MAX, Double.NaN);
            }
        );
        String result = exception.getMessage();
        String expected = "Categoria não pode estar vazia";
        assertEquals(expected, result);
    }

    /**
     * Teste de getValor.
     */
    @Test
    public void testeGetValor() {
        System.out.println("getValor");
        MovimentoFinanceiro instance = new Despesa("nome", Categoria.SALARIO, LocalDate.MAX, 12d);
        Double expResult = 12d;
        Double result = instance.getValor();
        assertEquals(expResult, result);
    }
    
    /**
     * Teste de setValor.
     */
    @Test
    public void testSetValor() {
        System.out.println("setValor");
        double valor = 10d;
        MovimentoFinanceiro instance = new Despesa("nome", Categoria.ALIMENTACAO, LocalDate.MAX, valor);
        assertEquals(instance.getValor(), valor);
    }

    /**
     * Teste de erro setValor.
     */
    @Test
    public void testeErroSetValor() {
        System.out.println("erroSetValor");        
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                new Despesa("nome", Categoria.SALARIO, LocalDate.MAX, -12d);
            }
        );
        String result = exception.getMessage();
        String expected = "Valor inválido";
        assertEquals(expected, result);
    }

    /**
     * Teste de equals.
     */
    @Test
    public void testeEquals() {
        System.out.println("equals");
        Object obj = new Despesa("nome", Categoria.SALARIO, LocalDate.MAX, Double.NaN);
        MovimentoFinanceiro instance = new Despesa("nome", Categoria.SALARIO, LocalDate.MAX, Double.NaN);
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        
        obj = new Receita("nome", Categoria.SALARIO, LocalDate.MAX, Double.NaN);
        instance = new Despesa("nome", Categoria.SALARIO, LocalDate.MAX, Double.NaN);
        expResult = false;
        result = instance.equals(obj);
        assertEquals(expResult, result);

    }

    /**
     * Teste de hashCode.
     */
    @Test
    public void testeHashCode() {
        System.out.println("hashCode");
        MovimentoFinanceiro instance = new Despesa("nome", Categoria.SALARIO, LocalDate.MAX, Double.NaN);
        MovimentoFinanceiro instance2 = new Receita("nome", Categoria.DECIMO_TERCEIRO, LocalDate.MAX, Double.NaN);
        int expResult = instance.hashCode();
        int result = instance2.hashCode();
        assertNotEquals(expResult, result);
    }

    /**
     * Teste de toString.
     */
    @Test
    public void testeToString() {
        System.out.println("toString");
        MovimentoFinanceiro instance = new Despesa("nome", Categoria.SALARIO, LocalDate.of(2024,01,01), 12d);
        String expResult = "Descrição: nome\n" +
    			"Data: 2024-01-01\n" +
  			"Categoria: SALARIO\n" +
 			"Valor: 12.0\n";
        String result = instance.toString();
        assertEquals(expResult, result);
    }    
}
