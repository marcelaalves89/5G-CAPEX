package br.edu.unifesspa.malves.photovoltaics;


/**
 * 
 * @author Marcela Alves
 * @since 2016-07-13
 *
 */
public class Panel {

	/**
	 * Rated Power in Watts (W)
	 */
	public static final double potenciaNominal = 320;
	
	/**
	 * Panel Area in m²
	 */
	public static final double area = 1.9188;
	
	/**
	 * 
	 */
	public static final double garantiaPainel = 25;
	
	/**
	 * Panel Acquisition Cost (in Brazilian Real R$)
	 */
	public static final double custoPorPainel = 659.97;
	
	/**
	 * 
	 */	
	public static final double eficiencia = 0.1668;
	
	/**
	 * 
	 */
	public static final double taxaDesempenho = 0.99;
	
	/**
	 * 
	 */
	public static final double hspPadrao = 5;
	
	/**
	 * 
	 */
	public static final double[] hsp = {1, 1.5, 2, 2.5, 3, 3.5, 4, 4.5, 5, 5.5, 6, 6.5, 7, 7.5, 8, 8.5, 9, 9.5, 10, 10.5, 11, 11.5, 12};
	
	/**
	 * 
	 */
	public static final double radiacaoPadrao = 5.5;
	
	/**
	 * 
	 */
	public static final double[] radiacao = {1.5, 2, 2.5, 3, 3.5, 4, 4.5, 5, 5.5, 6, 6.5, 7};
	
	/**
	 * 
	 */
	public static final double custoKitInstalacao = 199.75;
	
	/**
	 * 
	 */
	public static final double garantiaKitInstalacao = 12;
}