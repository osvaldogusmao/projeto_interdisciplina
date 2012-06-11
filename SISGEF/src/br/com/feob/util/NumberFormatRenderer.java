package br.com.feob.util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class NumberFormatRenderer extends JLabel implements TableCellRenderer{

    public NumberFormatRenderer() {
	setOpaque(true);
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	
	NumberFormat formatter = new DecimalFormat("#.00");
	
	Color color = new Color(208,216,188);
	setBackground(color);
	setText(formatter.format(value));
	setMaximumSize(new Dimension(80, 12));
	setHorizontalAlignment(JLabel.RIGHT);
	setForeground(Color.black);
	return this;
    }

}
