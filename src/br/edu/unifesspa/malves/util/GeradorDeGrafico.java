package br.edu.unifesspa.malves.util;

import java.awt.Dimension;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import br.edu.unifesspa.malves.trafficforecast.Environment;

public class GeradorDeGrafico extends JFrame{

	private static final long serialVersionUID = -848055779687594288L;

	private String tituloGrafico;

	private String legendaX;

	private String legendaY;
	
	private String dadoPrimeiraBarra;
	
	private String dadoSegundaBarra;
	
	private double[] primeiraBarra;
	
	private double[] segundaBarra;

	public GeradorDeGrafico(String tituloJanela, String tituloGrafico, String legendaX, String legendaY, String dadoPrimeiraBarra, double[] primeiraBarra,  String dadoSegundaBarra, double[] segundaBarra) {
		//Initializing
		super(tituloJanela);
		this.tituloGrafico = tituloGrafico;
		this.legendaX = legendaX;
		this.legendaY = legendaY;
		this.dadoPrimeiraBarra = dadoPrimeiraBarra;
		this.dadoSegundaBarra = dadoSegundaBarra;
		this.primeiraBarra = primeiraBarra;
		this.segundaBarra = segundaBarra;
	}

	public void createChart(){
		//Create chart
		CategoryDataset dataset = this.createDataset(this.primeiraBarra, this.segundaBarra);
		JFreeChart chart = this.createBarChart(dataset);
		ChartPanel panel = new ChartPanel(chart);
		panel.setPreferredSize(new Dimension(800, 600));
		setContentPane(panel);
	}

	private CategoryDataset createDataset(double[] primeiraBarra, double[] segundaBarra) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (int i=0; i<primeiraBarra.length; i++){
			if (i<=15){
				dataset.addValue(primeiraBarra[i], this.dadoPrimeiraBarra, ""+Environment.anos[i]);
				dataset.addValue(segundaBarra[i], this.dadoSegundaBarra, ""+Environment.anos[i]);
			}
		}
		return dataset;
	}

	private JFreeChart createBarChart(CategoryDataset dataset) {
		JFreeChart chart = ChartFactory.createBarChart(
				this.tituloGrafico,
				this.legendaX,
				this.legendaY,
				dataset,
				PlotOrientation.VERTICAL,
				true, false, false);
		return chart;
	}
}