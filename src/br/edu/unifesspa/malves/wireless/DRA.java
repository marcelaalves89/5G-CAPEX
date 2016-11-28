package br.edu.unifesspa.malves.wireless;

public abstract class DRA {

	public static final double numeroMaximoDeAntenasPorRRU = 8;
	
	public static final double numeroDeRRUSporPredio = 1;
	
	public static final double numeroDePortasDUporRackDU = 200;
	
	public static final double potenciaAntenaAmplificadora = 25;
	
	public static final double potenciaRRU = 100;
	
	public static final double potenciaPorPortaDU = 25;
	
	public static final double potenciaPorRackDU = 150;
	
	public static final double[] taxaDePenetracao = {0,0.05,0.1,0.15,0.2,0.25,0.3,0.35,0.4,0.45,0.5,0.55,0.6,0.65,0.7,0.75,0.8,0.85,0.9,0.95,1};
}