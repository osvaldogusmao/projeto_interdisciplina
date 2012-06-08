package br.com.feob.planoContas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;


public class PlanoContasTableModel extends AbstractTableModel{

    private String[] columnNames = {"Conta", "Descrição"};
    private List<PlanoConta> planoContas;
    
    public PlanoContasTableModel() {
	planoContas = new ArrayList<PlanoConta>();
    }
    
    public PlanoContasTableModel(List<PlanoConta> planoContas){
	this();
	this.planoContas.clear();
	this.planoContas.addAll(planoContas);
	super.fireTableDataChanged();
    }
    
    public void setData(List<PlanoConta> planoContas){
	this.planoContas.clear();
	this.planoContas.addAll(planoContas);
	super.fireTableDataChanged();
    }

    
    public void deletePlanoConta(int indice){
	this.planoContas.remove(indice);
	super.fireTableRowsDeleted(0, indice-1);
    }
    
    public void addItem(PlanoConta planoConta){
	this.planoContas.add(planoConta);
    }
    
    @Override
    public int getRowCount() {
	return planoContas.size();
    }

    @Override
    public int getColumnCount() {
	return columnNames.length;
    }

    @Override  
    public String getColumnName(int col) {  
        return columnNames[col];  
    }  
      
    public String[] getColumnNames() {  
        return columnNames;  
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
		PlanoConta planoConta = planoContas.get(rowIndex);
		
		switch (columnIndex) {
			case 0: return planoConta.getConta();
			case 1: return planoConta.getDescricao();
		}
	return null;
    }
    
    public boolean verificaItemExistente(String conta){
	for (PlanoConta pc : planoContas) {
	    if(pc.getConta().equals(conta)){
		return true;
	    }
	}
	return false;
    }
    
}
