package br.edu.unifesspa.malves.radionetwork;

import br.edu.unifesspa.malves.trafficforecast.Environment;
import br.edu.unifesspa.malves.trafficforecast.PrevisaoDeTrafego;
import br.edu.unifesspa.malves.util.Util;
import br.edu.unifesspa.malves.wireless.Macro;

/**
 * 
 * @author	Marcela Alves
 * @since	2016-06-12
 *
 */
public class MacroOnlyDeployment {

	/**
	 * Traffic forecast calculated previously
	 */
	public PrevisaoDeTrafego previsao;
	
	/**
	 * Macro Density
	 */
	public double[] densidadeDeMacros;
	
	/**
	 * Macro cell range
	 */
	public double[] alcanceCelulaMacro;
		
	/**
	 * Number of Macros
	 */
	public double[] numeroDeMacros;
	
	/**
	 * Number of active users per MacroBS
	 */
	public double[] numeroDeUsuarioAtivosPorMacro;
	
	/**
	 * Power consumption of Macro Only Architecture
	 */
	public double[] potencia;
	
	/**
	 * User Density
	 */
	public double densidadeDeUsuarios;
	
	/**
	 * Architecture's Name
	 */
	public String nome;
	
	/**
	 * 	Super constructor call and initializing values
	 */
	public MacroOnlyDeployment(double densidadeDeUsuarios){
		this.densidadeDeUsuarios = densidadeDeUsuarios;
		this.previsao = new PrevisaoDeTrafego(this.densidadeDeUsuarios);
		this.nome = "Macro Only Deployment";		
		this.run();
	}
	
	/**
	 * Performs the calculation of Macro Density, Macro Cell Range, Number of Macros, Number of Active Users Per Macro and Power consumption of Macro Only Architecture 
	 */
	public void run(){
		this.densidadeDeMacros = Util.getProdutoPorEscalar(previsao.getPrevisaoDeTrafego(), (1.0/Macro.capacidadeDaCelula));
		
		//Macro cell range
		double[] temp = Util.getProdutoPorEscalar(this.densidadeDeMacros, (1.5*Math.sqrt(3)));
		this.alcanceCelulaMacro = Util.getPotencia((Util.getPotencia(temp, -1.0)),1.0/2.0);
		
		//Number of Macros
		this.numeroDeMacros = Util.getProdutoPorEscalar(this.densidadeDeMacros, Environment.area);
		
		//Number of Active users per Macro
		this.numeroDeUsuarioAtivosPorMacro = Util.getProdutoPorEscalar(Util.getPotencia(this.previsao.getTaxaMediaPorUsuarioExtendida(), -1.0), Macro.capacidadeDaCelula);
	}
}