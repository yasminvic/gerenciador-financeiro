package models;

import java.util.ArrayList;

public interface IGerenciamentoContaBancaria {

    public ArrayList<MovimentoFinanceiro> listar();

    public void incluir(MovimentoFinanceiro movimentoFinanceiro);

    public double consultarSaldoAtual();

    public double consultarSaldoPeriodo();
}
