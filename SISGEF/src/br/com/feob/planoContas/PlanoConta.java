package br.com.feob.planoContas;

public class PlanoConta {

    private String conta;
    private String descricao;
    private String nivel;
    
    /**
     * 
     * Getter and Setters
     * 
     * */
    public String getConta() {
	return conta;
    }

    public void setConta(String conta){
	this.conta = conta;
    }
    
    public String getDescricao() {
	return descricao;
    }
    
    public void setDescricao(String descricao){
	this.descricao = descricao;
    }

    public String getNivel() {
	return nivel;
    }
    
    public void setNivel(String nivel){
	this.nivel = nivel;
    }
    
    /**
     * 
     * Constructor
     * 
     * */
    public PlanoConta(String conta, String descricao, String nivel) {
	this.conta = conta;
	this.descricao = descricao;
	this.nivel = nivel;
    }
    
    public PlanoConta(){
	
    }
    
    public boolean aceitaLancamento(){
	if(this.nivel.equals("S")){
	    return false;
	}else{
	    return true;
	}
    }
}
