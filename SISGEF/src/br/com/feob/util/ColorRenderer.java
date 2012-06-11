package br.com.feob.util;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class ColorRenderer extends JLabel implements TableCellRenderer {

    
    public ColorRenderer() {
	setOpaque(true);
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	
	Color color = new Color(208,216,188);
	setBackground(color);
	this.setText(value.toString());
	setForeground(Color.black);
	return this;
    }

}
