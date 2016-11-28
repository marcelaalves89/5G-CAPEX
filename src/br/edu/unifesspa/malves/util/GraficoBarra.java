package br.edu.unifesspa.malves.util;

import java.awt.Color;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

public class GraficoBarra extends ApplicationFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3222603704302811628L;
	
	private String tituloJanela;

	private String tituloGrafico;

	private String tituloEixoX;

	private String tituloEixoY;

	private HashMap<String, Double> dados;
	
	private double[] escalaY;

	public GraficoBarra(String tituloJanela, String tituloGrafico, String tituloEixoX, String tituloEixoY, HashMap<String, Double> dados, double[] escalaY) {
		super(tituloJanela);
		this.tituloJanela = tituloJanela;
		this.tituloGrafico = tituloGrafico;
		this.tituloEixoX = tituloEixoX;
		this.tituloEixoY = tituloEixoY;
		this.dados = dados;
		this.escalaY = escalaY;

		JPanel chartPanel = createDemoPanel();
		chartPanel.setPreferredSize(new java.awt.Dimension(600, 500));
		setContentPane(chartPanel);
	}

	private JFreeChart createChart(final CategoryDataset dataset) {
		JFreeChart chart = ChartFactory.createBarChart(
				this.tituloGrafico,     
				this.tituloEixoX,                     
				this.tituloEixoY,                     
				dataset,              
				PlotOrientation.VERTICAL,
				true,                     
				true,                 
				true);
		
		CategoryPlot plot = chart.getCategoryPlot();
		Font font = new Font("Tahoma", Font.BOLD, 16); 
		plot.getDomainAxis().setLabelFont(font);
		plot.getRangeAxis().setLabelFont(font);
		BarRenderer br = (BarRenderer) plot.getRenderer();
		br.setMaximumBarWidth(.1);
		plot.setBackgroundPaint(Color.white);
		plot.setDomainGridlinePaint(new Color(0x00, 0x00, 0x00));
		plot.setRangeGridlinePaint(new Color(0x00, 0x00, 0x00));
		
		if (this.escalaY != null){
	        NumberAxis range = (NumberAxis) plot.getRangeAxis();
	        range.setRange(this.escalaY[0], this.escalaY[1]);		}
		
		return chart;
	}

	public JPanel createDemoPanel() {
		JFreeChart chart = this.createChart(createDataset());
		ChartPanel panel = new ChartPanel(chart);
		panel.setMouseWheelEnabled(true);
		

		OutputStream arquivo;
		try {
			arquivo = new FileOutputStream("c:/users/hugo/desktop/5g2/"+this.tituloJanela+".png");
			ChartUtilities.writeChartAsPNG(arquivo, chart, 600, 500);
			arquivo.close();
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return panel;
	}

	private CategoryDataset createDataset() {		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();		
		for(Entry<String, Double> elemento : this.dados.entrySet()) {
			String key = elemento.getKey();
			Double value = elemento.getValue();		    		    			    	    
			dataset.addValue(value.doubleValue(), key, "");
		}	    
		return dataset;
	}
}