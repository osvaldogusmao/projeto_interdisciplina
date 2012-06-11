package br.com.feob.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;

public class DecimalRenderer extends DefaultTableCellRenderer {
    
    NumberFormat formatter;

    public DecimalRenderer(String pattern) {
	this(new DecimalFormat(pattern));
    }

    public DecimalRenderer(DecimalFormat formatter) {
	this.formatter = formatter;
	setHorizontalAlignment(JLabel.RIGHT);
    }

    public void setValue(Object value) {
	setText((value == null) ? "" : formatter.format(((Double) value).doubleValue()));
    }
}