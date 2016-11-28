package br.edu.unifesspa.malves.util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Stroke;
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
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;

public class GraficoLinha extends ApplicationFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3222603704302811628L;
	
	private String tituloJanela;
	
	private String tituloGrafico;
	
	private String tituloEixoX;
	
	private String tituloEixoY;
	
	private HashMap<String, double[]> dadosX;
	
	private double[] dadosY;
	
	private double escalaX[];
	
	private double escalaY[];

	public GraficoLinha(String tituloJanela, String tituloGrafico, String tituloEixoX, String tituloEixoY, HashMap<String, double[]> dadosX, double[] dadosY, double[] escalaX, double[] escalaY) {
		super(tituloJanela);
		this.tituloJanela = tituloJanela;
		this.tituloGrafico = tituloGrafico;
		this.tituloEixoX = tituloEixoX;
		this.tituloEixoY = tituloEixoY;
		this.dadosX = dadosX;
		this.dadosY = dadosY;
		this.escalaX = escalaX;
		this.escalaY = escalaY;
		
		JPanel chartPanel = createDemoPanel();
		chartPanel.setPreferredSize(new java.awt.Dimension(600, 500));
		setContentPane(chartPanel);
	}

	private JFreeChart createChart(final XYDataset dataset) {

		JFreeChart chart = ChartFactory.createXYLineChart(
				this.tituloGrafico,     
				this.tituloEixoX,                     
				this.tituloEixoY,                     
				dataset,              
				PlotOrientation.VERTICAL,
				true,      
				false,                 
				false             
				);

		XYPlot plot = (XYPlot) chart.getPlot();
		Font font = new Font("Tahoma", Font.BOLD, 16); 
		plot.getDomainAxis().setLabelFont(font);
		plot.getRangeAxis().setLabelFont(font);
		plot.setDomainPannable(true);
		plot.setRangePannable(true);
		plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
		plot.setBackgroundPaint(Color.white);
		plot.setDomainGridlinePaint(new Color(0x00, 0x00, 0x00));
		plot.setRangeGridlinePaint(new Color(0x00, 0x00, 0x00));
		
		if (this.escalaX != null && this.escalaY != null){
	        NumberAxis domain = (NumberAxis) plot.getDomainAxis();
	        domain.setRange(this.escalaX[0], this.escalaX[1]);
	        //domain.setTickUnit(new NumberTickUnit(0.1));
	        NumberAxis range = (NumberAxis) plot.getRangeAxis();
	        range.setRange(this.escalaY[0], this.escalaY[1]);
	        //range.setTickUnit(new NumberTickUnit(0.1));
		}

		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(){
			
			

			private static final long serialVersionUID = 1L;
			Stroke soild = new BasicStroke(3.0f);
			Stroke dashed =  new BasicStroke(3.0f,BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, new float[] {10.0f}, 0.0f);
			@Override
			public Stroke getItemStroke(int row, int column) {
				if (row > 10){
					double x = dataset.getXValue(row, column);
					if ( x > 1){
						return dashed;
					} else {
						return soild;
					} 
				} else
					return super.getItemStroke(row, column);
			}
		};

		renderer.setSeriesPaint(0, Color.MAGENTA); 
		renderer.setSeriesPaint(1, Color.RED);
		renderer.setSeriesPaint(2, Color.BLUE); 
		renderer.setSeriesPaint(3, Color.DARK_GRAY);
		renderer.setBaseShapesVisible(true);
		renderer.setBaseShapesFilled(true);
		renderer.setBaseStroke(new BasicStroke(4));
		plot.setRenderer(renderer);
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

	private XYDataset createDataset() {		
		XYSeriesCollection dataset = new XYSeriesCollection();
		XYSeries series = null;
		for(Entry<String, double[]> elemento : this.dadosX.entrySet()) {
		    String key = elemento.getKey();
		    double[] valores = elemento.getValue();
		    series = new XYSeries(key);
		    for (int i=0; i<valores.length; i++){		    			    	
		    	series.add(this.dadosY[i], valores[i]);	
		    }
		    dataset.addSeries(series);
		}	    
		return dataset;
	}
}