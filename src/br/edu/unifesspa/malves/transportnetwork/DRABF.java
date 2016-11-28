package br.edu.unifesspa.malves.transportnetwork;

import br.edu.unifesspa.malves.backhaul.PON;
import br.edu.unifesspa.malves.util.Util;
import br.edu.unifesspa.malves.wireless.DRA;

/**
 * 
 * @author	Marcela Alves
 * @since	2016-06-18
 *
 */
public class DRABF extends DRAPVBased{

	/**
	 * Super constructor call and initializing values
	 */
	public DRABF(double radiacao, double densidadeDeUsuarios){
		super(radiacao, densidadeDeUsuarios);
		this.nome = "Macro+DRA-BF Architecture";
		this.getConsumoMacro();
		this.getConsumoDRA();		
		this.getPotenciaDeGeracao();
		this.getTCO();
		this.getEstatisticas();
		this.debug();
	}

	/**
	 * Calculating the Power Consumption of DRA-BF Only (KWH)
	 */
	public void getConsumoDRA(){				
		
		double temp = DRA.numeroDeRRUSporPredio*(DRA.potenciaRRU + DRA.potenciaPorPortaDU 
												 + (DRA.potenciaPorRackDU/DRA.numeroDePortasDUporRackDU))
		+ PON.numeroDeONUsPorPredio*(PON.potenciaONU+(PON.potenciaOLT/PON.numeroDeWaveLenghtsDWDM));
		
		this.potenciaDRAOnly = Util.getSoma(
				Util.getProdutoPorEscalar(super.numeroDeAntenasDRA, DRA.potenciaAntenaAmplificadora),
				Util.getProdutoPorEscalar(super.numeroMaximoDePrediosComDRA, temp)
				);
		
		Util.converterEmKWH(this.potenciaDRAOnly);
		this.potenciaTotal = Util.getSoma(this.potenciaMacroOnly,this.potenciaDRAOnly);
	}
	
	/**
	 * Print values for Debug
	 */
	public void debug(){

	}
	
	public static void main(String[] args) {
		new DRABF(6.5, 3000);
	}
}