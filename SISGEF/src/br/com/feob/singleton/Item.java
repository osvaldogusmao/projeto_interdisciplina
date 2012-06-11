package br.com.feob.singleton;

import br.com.feob.Enum.GrupoConta;

public class Item {

    private String conta;
    private GrupoConta grupoConta;
    
    /**
     * Constructor
     * 
     * */
    public Item(String conta, GrupoConta grupoConta) {
	this.setConta(conta);
	this.setDescricao(grupoConta);
    }
    
    /**
     * 
     * Getter and Setter
     * 
     * */
    public String getConta() {
	return conta;
    }
    
    public void setConta(String conta) {
	this.conta = conta;
    }
    
    public GrupoConta getDescricao() {
	return grupoConta;
    }
    
    public void setDescricao(GrupoConta descricao) {
	this.grupoConta = descricao;
    }
    
}
