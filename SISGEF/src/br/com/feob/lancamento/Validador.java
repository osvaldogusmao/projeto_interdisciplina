package br.com.feob.lancamento;

public class Validador {

    private int ano;
    private boolean validado;

    public Validador(int ano, boolean validado){
	this.ano = ano;
	this.validado = validado;
    }
    
    public int getAno() {
	return ano;
    }

    public void setAno(int ano) {
	this.ano = ano;
    }

    public boolean isValidado() {
	return validado;
    }

    public void setValidado(boolean validado) {
	this.validado = validado;
    }

}
