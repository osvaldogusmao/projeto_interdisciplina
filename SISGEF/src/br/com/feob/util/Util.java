package br.com.feob.util;

import javax.swing.text.MaskFormatter;

public class Util {

    public MaskFormatter createFormatter(String s) {
	MaskFormatter formatter = null;
	try {
	    formatter = new MaskFormatter(s);
	} catch (java.text.ParseException exc) {
	    System.err.println("formatter is bad: " + exc.getMessage());
	    System.exit(-1);
	}
	return formatter;
    }

}
