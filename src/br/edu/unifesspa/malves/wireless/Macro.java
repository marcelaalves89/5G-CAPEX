package br.edu.unifesspa.malves.wireless;

public abstract class Macro {

	public static double larguraDeBanda = (10*Math.pow(10,6));
	
	public static final double aM = 4.7; 
	
	public static final double bM = 130;
	
	public static final double numeroDeSetores = 3;
	
	public static final double potenciaDeTransmissao = 40;
	
	public static final double capacidadeDaCelula = Macro.larguraDeBanda*3*((Math.log10(1+1.1)/Math.log10(2))/Math.pow(10, 6));
	
	public static final double demandaPicoPorMacro = Macro.capacidadeDaCelula;
	
	public static final double demandaMediaPorMacro = Macro.capacidadeDaCelula*9.64/16.0;	
	
	public static final double potencia = 650;
}