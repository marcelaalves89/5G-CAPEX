package br.edu.unifesspa.malves.radionetwork;

import br.edu.unifesspa.malves.trafficforecast.Environment;
import br.edu.unifesspa.malves.util.Util;
import br.edu.unifesspa.malves.wireless.DRA;

/**
 * 
 * @author	Marcela Alves
 * @since	2016-06-18
 *
 */
public class DRABasedDeployment extends MacroOnlyDeployment {

	/**
	 * Represents the max number of building equipped with DRA systems
	 */
	public double[] numeroMaximoDePrediosComDRA;

	/**
	 * Represents the number of MacroBS in HetNet Macro+DRA
	 */
	public double[][] numeroDeMacros;

	/**
	 * Represents the total number of DRA Antennas
	 */
	public double[][] numeroDeAntenasDRA;

	/**
	 * 	Super constructor call and initializing values
	 */
	public DRABasedDeployment(double densidadeDeUsuarios){
		super(densidadeDeUsuarios);
		this.nome = "DRA-Based Deployment";

		this.numeroDeMacros = new double[DRA.taxaDePenetracao.length][this.previsao.getPrevisaoDeTrafego().length];
		this.numeroDeAntenasDRA = new double[DRA.taxaDePenetracao.length][1];
		this.numeroMaximoDePrediosComDRA = new double[DRA.taxaDePenetracao.length];

		this.getNumeroDeMacros();
		this.getNumeroDRAS();
	}

	/**
	 * Total Number of MacroBS's
	 */
	public void getNumeroDeMacros() {
		for (int i=0; i<DRA.taxaDePenetracao.length; i++){
			double temp = this.densidadeDeUsuarios*Environment.area*Environment.alphaMaximo*(1-DRA.taxaDePenetracao[i]*Environment.porcentagemUsuariosIndoor);
			this.numeroDeMacros[i] = Util.getDivisao(this.numeroDeUsuarioAtivosPorMacro, temp); 
		}
	}

	/**
	 * Total Number of DRA antennas, DRA Antennas per Building
	 */
	public void getNumeroDRAS(){
		for (int i=0; i<DRA.taxaDePenetracao.length; i++){
			this.numeroMaximoDePrediosComDRA[i] = DRA.taxaDePenetracao[i]*Environment.numeroMaximoPredios;
			this.numeroDeAntenasDRA[i][0] = this.numeroMaximoDePrediosComDRA[i]*Environment.numeroDeAndaresPorPredio;			
		}
	}
}