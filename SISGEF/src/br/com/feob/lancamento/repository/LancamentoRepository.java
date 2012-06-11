package br.com.feob.lancamento.repository;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import br.com.feob.Enum.GrupoConta;
import br.com.feob.lancamento.Lancamento;
import br.com.feob.singleton.DadosSistema;
import br.com.feob.singleton.Item;

public class LancamentoRepository {

    private List<Lancamento> lancamentos = DadosSistema.getIstance().getLancamentos();
    private List<Item> grupoContas = DadosSistema.getIstance().getGrupoContas();

    public LancamentoRepository() {

    }

    public void gravarLancamentoTXT(List<Lancamento> lancamentos) {

	PrintWriter printWriter;
	try {
	    printWriter = new PrintWriter("/Users/osvaldo/Desktop/lancamentos.txt");

	    for (Lancamento lancamento : lancamentos) {
		printWriter.println(lancamento.getCodigoLancamento() + "|" + lancamento.getConta() + "|" + lancamento.getAno() + "|"
			+ lancamento.getSaldo());
	    }

	    printWriter.close();
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	}
    }

    
    /**
     * 
     * Metodos de totalização dos lançamentos
     * 
     * */
    public Double getTotalContaPrimeiroNivel(int ano, GrupoConta conta) {

	Double totalConta = 0.00;
	String grupo = getGrupoConta(conta);

	for (Lancamento lancamento : this.lancamentos) {

	    if (lancamento.getAno() == ano && lancamento.getConta().substring(0, 2).equals(grupo)) {
		totalConta = totalConta + lancamento.getSaldo();

	    }
	}
	return totalConta;
    }

    public Double getTotalContaSegundoNivel(int ano, GrupoConta conta) {

	Double totalConta = 0.00;
	String grupo = getGrupoConta(conta);

	for (Lancamento lancamento : this.lancamentos) {

	    if (lancamento.getAno() == ano && lancamento.getConta().substring(0, 5).equals(grupo)) {
		totalConta = totalConta + lancamento.getSaldo();

	    }
	}
	return totalConta;
    }
    
    public Double getTotalContaTerceiroNivel(int ano, GrupoConta conta) {

	Double totalConta = 0.00;
	String grupo = getGrupoConta(conta);

	for (Lancamento lancamento : this.lancamentos) {

	    if (lancamento.getAno() == ano && lancamento.getConta().substring(0, 8).equals(grupo)) {
		totalConta = totalConta + lancamento.getSaldo();

	    }
	}
	return totalConta;
    }

    public Double getTotalContaQuartoNivel(int ano, GrupoConta conta) {

	Double totalConta = 0.00;
	String grupo = getGrupoConta(conta);

	for (Lancamento lancamento : this.lancamentos) {

	    if (lancamento.getAno() == ano && lancamento.getConta().equals(grupo)) {
		totalConta = totalConta + lancamento.getSaldo();

	    }
	}
	return totalConta;
    }
    
    private String getGrupoConta(GrupoConta grupoConta) {

	for (Item item : grupoContas) {

	    if (item.getDescricao() == grupoConta) {
		return item.getConta();
	    }
	}
	return "";
    }

}