package br.com.feob.indices;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.ListSelectionModel;
import javax.swing.event.TableColumnModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class IndicesTableModel extends AbstractTableModel {

    private String[] columnNames = {"Descrição", "Valor", "Ano"};
    List<Indice> indices;

    public IndicesTableModel() {
	indices = new ArrayList<Indice>();
    }

    public void setData(List<Indice> indices) {
	this.indices.clear();
	this.indices.addAll(indices);
	super.fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
	return indices.size();
    }

    @Override
    public int getColumnCount() {
	return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
	return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
	if (getValueAt(0, columnIndex) != null) {
	    return getValueAt(0, columnIndex).getClass();
	} else
	    return null;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

	Indice indice = indices.get(rowIndex);
	
	if (columnIndex == 0) {
	    return indice.getDescricao();
	}else if(columnIndex == 1){
	    return indice.getValor();
	}else if(columnIndex == 2){
	    return indice.getAno();
	}else{
	    return 0;
	}
    }
}