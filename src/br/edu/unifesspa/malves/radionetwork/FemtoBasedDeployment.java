package br.edu.unifesspa.malves.radionetwork;

import br.edu.unifesspa.malves.trafficforecast.Environment;
import br.edu.unifesspa.malves.util.Util;
import br.edu.unifesspa.malves.wireless.DRA;
import br.edu.unifesspa.malves.wireless.Femto;

public class FemtoBasedDeployment extends MacroOnlyDeployment {

	/**
	 * Represents the number of MacroBS in HetNet Macro+Femto
	 */
	public double[][] numeroDeMacros;

	/**
	 * Represents the total number of Femto BS's
	 */
	public double[][] numeroDeFemtos;

	/**
	 * Represents the max number of building equipped with Femto BS's
	 */
	public double[] numeroMaximoDePrediosComFemto;

	/**
	 * Represents the number of Femto BS's per Building
	 */
	public double[] numeroDeFemtosPorPredio;
	
	/**
	 * Represents the increased number of outdoor users that macrocell can support after offloading costly indoor traffic to DRA
	 */
	public static final double offGain = 0;

	/**
	 * Superclass constructor call and initializing values
	 */
	public FemtoBasedDeployment(double densidadeDeUsuarios){
		super(densidadeDeUsuarios);
		this.nome = "Femto-Based Deployment";
		int x = Femto.taxaDePenetracao.length, y=this.previsao.getPrevisaoDeTrafego().length;

		this.numeroDeMacros = new double[x][y];
		this.numeroDeFemtos = new double[x][1];
		this.numeroMaximoDePrediosComFemto = new double[x];
		this.numeroDeFemtosPorPredio = new double[x];
		
		this.getNumeroDeMacros();
		this.getNumeroFemtos();
		//this.debug();
	}
	
	/**
	 * Performs the calculation of Total Number of MacroBS's
	 */
	public void getNumeroDeMacros() {		
		for (int i=0; i<DRA.taxaDePenetracao.length; i++){
			double temp = this.densidadeDeUsuarios*Environment.area*Environment.alphaMaximo* (1-Femto.taxaDePenetracao[i]);
			this.numeroDeMacros[i] = Util.getDivisao(this.numeroDeUsuarioAtivosPorMacro, temp); 
		}
	}

	/**
	 * Performs the calculation of Total Number of FemtoBS's
	 */
	public void getNumeroFemtos(){
		for (int i=0; i<Femto.taxaDePenetracao.length; i++)
			this.numeroDeFemtos[i][0] = (Environment.numeroMaximoPredios*Environment.numeroApartamentosPorPredio*Femto.taxaDePenetracao[i])/Environment.porcentagemUsuariosIndoor;
	}

	/**
	 * Print values for Debug
	 */
	public void debug(){
	
	}
}