package br.com.feob.indices;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.TickUnits;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.Spacer;

public class FrmGraficos extends javax.swing.JFrame {

    private JLabel lbResultado = new JLabel();

    public FrmGraficos(List<Indice> indices, String title) {

	super.setPreferredSize(new Dimension(490, 350));
	super.setResizable(false);
	JPanel panelgeral = new JPanel();
	panelgeral.setLayout(new FlowLayout(FlowLayout.CENTER));

	CategoryDataset dataset = createDataset(indices);

	JFreeChart chart = createChart(dataset, title);

	ChartPanel chartPanel = new ChartPanel(chart);

	panelgeral.add(chartPanel, JPanel.TOP_ALIGNMENT);

	JPanel panel = new JPanel();
	panel.add(lbResultado);
	
	panelgeral.add(panel, JPanel.BOTTOM_ALIGNMENT);

	chartPanel.setPreferredSize(new Dimension(500, 270));
	chartPanel.setEnforceFileExtensions(false);

	setContentPane(panelgeral);

    }

    private CategoryDataset createDataset(List<Indice> indices) {
	final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

	int maiorAno = 0;
	Double valorAno = 0.0;

	for (Indice indice : indices) {
	    if (indice.getAno() > maiorAno) {
		maiorAno = indice.getAno();
		valorAno = indice.getValor();
	    }
	    dataset.addValue(indice.getValor(), "Anos", indice.getAno().toString());
	}

	for (Indice indice : indices) {
	    if (indice.getAno() < maiorAno) {

		if (indice.getValor() < valorAno) {
		    lbResultado.setText("A Liquidez Imediata do período melhorou em relação ao ano de " + indice.getAno());
		    break;
		}else{
		    lbResultado.setText("A Liquidez Imediata do período piorou em relação ao ano de " + indice.getAno());
		    break;
		}

	    }
	}

	return dataset;
    }

    private JFreeChart createChart(final CategoryDataset dataset, String title) {

	final JFreeChart chart = ChartFactory.createWaterfallChart(title, "Anos", "Valores", dataset, PlotOrientation.VERTICAL, true,
		true, false);
	chart.setBackgroundPaint(Color.white);

	final CategoryPlot plot = chart.getCategoryPlot();
	plot.setBackgroundPaint(Color.white);
	plot.setRangeGridlinePaint(Color.black);
	plot.setRangeGridlinesVisible(true);
	plot.setAxisOffset(new RectangleInsets(05, 05, 05, 05));

	final ValueAxis rangeAxis = plot.getRangeAxis();

	// create a custom tick unit collection...
	final DecimalFormat formatter = new DecimalFormat("###,##0.00");
	formatter.setNegativePrefix("(");
	formatter.setNegativeSuffix(")");
	final TickUnits standardUnits = new TickUnits();
	standardUnits.add(new NumberTickUnit(1, formatter));
	standardUnits.add(new NumberTickUnit(2, formatter));
	standardUnits.add(new NumberTickUnit(3, formatter));
	standardUnits.add(new NumberTickUnit(4, formatter));
	standardUnits.add(new NumberTickUnit(5, formatter));
	standardUnits.add(new NumberTickUnit(10, formatter));
	standardUnits.add(new NumberTickUnit(15, formatter));
	standardUnits.add(new NumberTickUnit(20, formatter));
	standardUnits.add(new NumberTickUnit(25, formatter));
	standardUnits.add(new NumberTickUnit(50, formatter));
	standardUnits.add(new NumberTickUnit(100, formatter));

	rangeAxis.setStandardTickUnits(standardUnits);

	final BarRenderer renderer = (BarRenderer) plot.getRenderer();
	renderer.setDrawBarOutline(false);

	final DecimalFormat labelFormatter = new DecimalFormat("##,###.00");
	labelFormatter.setNegativePrefix("(");
	labelFormatter.setNegativeSuffix(")");
	renderer.setItemLabelGenerator(new StandardCategoryItemLabelGenerator("{2}", labelFormatter));
	renderer.setItemLabelsVisible(true);

	return chart;
    }

}
