package br.com.feob.renderer;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

public class ExcluirRenderer extends AbstractCellEditor implements TableCellRenderer, ActionListener{

	
	JTable table;
	JButton renderButton;
	
	public ExcluirRenderer(JTable table, int column) {
		
		super();
		this.table = table;
		renderButton = new JButton();
		
		renderButton.setBorderPainted(false);
		renderButton.addActionListener(this);
		renderButton.setIcon(new ImageIcon(getClass().getResource("/assets/images/delete.png")));
		
		TableColumnModel columnModel = table.getColumnModel();
		columnModel.getColumn(column).setCellRenderer(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		fireEditingStopped();
		System.out.println(table.getSelectedRow());
		
	}


	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		
		return renderButton;
	}

	@Override
	public Object getCellEditorValue() {

		return null;
	}
	
	
}
