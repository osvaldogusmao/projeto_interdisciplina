package br.com.feob.singleton;

public class DadosSistema {

    private static DadosSistema instance = new DadosSistema();
    private String[][] planoContas;
    private String[] colunasPlanoContas;
    
    /**
     * Getter and Setter
     * 
     * */
    public String[][] getPlanoContas() {
	return planoContas;
    }

    public void setPlanoContas(String[][] planoContas) {
	this.planoContas = planoContas;
    }
    
    public String[] getColunasPlanoContas() {
	return colunasPlanoContas;
    }

    public void setColunasPlanoContas(String[] colunasPlanoContas) {
	this.colunasPlanoContas = colunasPlanoContas;
    }
    
    /**
     * 
     * Constructor
     * 
     * */
    private DadosSistema(){
	
    }
    
    /**
     * 
     * getInstance()
     * 
     * */
    public static DadosSistema getIstance(){
	return instance;
    }
}