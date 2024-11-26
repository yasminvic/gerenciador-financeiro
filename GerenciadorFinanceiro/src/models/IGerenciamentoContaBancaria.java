package models;

import java.time.LocalDate;
import java.util.ArrayList;

public interface IGerenciamentoContaBancaria {

    public ArrayList<MovimentoFinanceiro> listar();

    public void incluir(MovimentoFinanceiro movimentoFinanceiro);

    public double consultarSaldo(LocalDate data);
}
