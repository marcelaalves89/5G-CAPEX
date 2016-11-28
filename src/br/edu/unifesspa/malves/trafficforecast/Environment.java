package br.edu.unifesspa.malves.trafficforecast;

/**
 * 
 * @author	Marcela Alves
 * @since	2016-06-19
 *
 */
public abstract class Environment {
	
	/**
	 * Fração de usuários pesados e ordinários (EARTH Assumptions)
	 */
	public static final double[][] fracaoDeUsuarios = {{0.1, 0.2, 0.3},{0.9, 0.8, 0.7}};
	
	/**
	 * Number of apartments per building
	 */
	public static final double numeroApartamentosPorPredio = 15;
	
	/**
	 * Number of floors per building
	 */
	public static final double numeroDeAndaresPorPredio = 5;
	
	/**
	 * 
	 */
	public static final double[] densidadeDeUsuarios = {1000, 1500, 2000, 2500, 3000, 3500, 3500, 4000, 4500, 5000};
	
	//public static final double[] densidadeDeUsuarios = {2750, 3000, 3500, 3500, 3750, 4000, 4250, 4500, 4750, 5000};
	
	/**
	 * Default User density
	 */
	public static final double densidadeDeUsuariosPadrao = 3000;
	
	/**
	 * 
	 */
	public static final double porcentagemUsuariosIndoor = 0.6;
		
	/**
	 * 
	 */
	public static final double porcentagemUsuariosOutdoor = 0.4;
	
	/**
	 * Geographic area (km²)
	 */
	public static final double area =  100;
	
	/**
	 * Max Number of Apartments
	 */
	public static final double numeroMaximoPredios = 10000;
	
	/**
	 * Active users rate
	 */
	public static final double alphaMaximo = 0.16;
	
	/**
	 * Terminal Penetration rate of terminals type
	 */
	public static final double[][] taxaPenetracaoTerminais = {{0.1,0.2,0.3},{0.03,0.05,0.1},{0.3,0.5,0.6}};
	
	/**
	 * 
	 */
	public static final double[] anos = {2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017, 2018, 2019, 2020, 2021, 2022, 2023, 2024, 2025, 2026, 2027, 2028, 2029, 2030};
	
	/**
	 * 
	 */
	public static final double[] fatorCO2 = {0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9};	
	
	/**
	 * 
	 */
	public static final double fatorCO2Padrao = 0.6;
	
	public static final double cotacaoDolar = 3.243;
}