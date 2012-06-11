package br.com.feob.lancamento;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class LancamentoTableModel extends AbstractTableModel {

    String[] columnNames = { "Sequência", "Conta", "Descricao", "Ano", "Valor" };
    List<Lancamento> lancamentos;

    public LancamentoTableModel(List<Lancamento> lancamentos) {
	this();
	this.lancamentos.clear();
	this.lancamentos.addAll(lancamentos);
	super.fireTableDataChanged();
    }

    public LancamentoTableModel() {
	lancamentos = new ArrayList<Lancamento>();
    }

    public void setData(List<Lancamento> lancamentos) {
	this.lancamentos.clear();
	this.lancamentos.addAll(lancamentos);
	super.fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
	return lancamentos.size();
    }

    @Override
    public int getColumnCount() {
	return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
	return columnNames[column];
    }

    public String[] columnNames() {
	return this.columnNames;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

	Lancamento lancamento = lancamentos.get(rowIndex);

	switch (columnIndex) {
	case 0:
	    return lancamento.getCodigoLancamento();
	case 1:
	    return lancamento.getConta();
	case 2:
	    return lancamento.getDescricaoConta();
	case 3:
	    return lancamento.getAno();
	case 4:
	    return lancamento.getSaldo();
	default:
	    break;
	}

	return null;
    }

    public Double getTotalColuna(int column){
	
	Double totalValor = 0.00;
	
	for (int i = 0; i < getRowCount(); i++) {
	    
	    totalValor = totalValor + Double.parseDouble(this.getValueAt(i, column).toString());
	}
	
	return totalValor;
    }
    
    
    public void deleteItem(int rowIndex) {
	this.lancamentos.remove(rowIndex);
	this.fireTableRowsDeleted(0, rowIndex - 1);
    }

    public void addItem(Lancamento lancamento) {
	this.lancamentos.add(lancamento);
    }

    public boolean verificaItemExistente(String conta, int ano) {
	for (Lancamento lancamento : lancamentos) {

	    if (lancamento.getConta().equals(conta) && lancamento.getAno() == ano) {
		return true;
	    }
	}
	return false;
    }

}
