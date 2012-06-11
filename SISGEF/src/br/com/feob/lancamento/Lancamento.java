package br.com.feob.lancamento;

import java.util.List;

import br.com.feob.planoContas.PlanoConta;
import br.com.feob.singleton.DadosSistema;

public class Lancamento {

    private long codigoLancamento;
    private String contaContabil;
    private int ano;
    private double saldo;

    public long getCodigoLancamento() {
	return codigoLancamento;
    }

    public void setCodigoLancamento(long codigoLancamento) {
	this.codigoLancamento = codigoLancamento;
    }

    public String getConta() {
	return contaContabil;
    }

    public void setConta(String conta) {
	this.contaContabil = conta;
    }

    public int getAno() {
	return ano;
    }

    public void setAno(int ano) {
	this.ano = ano;
    }

    public double getSaldo() {
	return saldo;
    }

    public void setSaldo(double saldo) {
	this.saldo = saldo;
    }
    
    public String getDescricaoConta(){
	
	List<PlanoConta> planoContas = DadosSistema.getIstance().getPlanoContas();
	
	for(PlanoConta planoConta : planoContas){
	    
	   if(planoConta.getConta().equals(this.getConta())){
	       return planoConta.getDescricao();
	   }
	}
	return null;
    }

}
