package br.edu.unifesspa.malves.transportnetwork;

import br.edu.unifesspa.malves.backhaul.Fiber;
import br.edu.unifesspa.malves.util.Util;
import br.edu.unifesspa.malves.wireless.DRA;

/**
 * 
 * @author	Marcela Alves
 * @since	2016-06-18
 *
 */
public class DRACF extends DRAPVBased{

	/**
	 * Super constructor call and initializing values
	 */
	public DRACF(double hsp, double densidadeDeUsuarios){
		super(hsp, densidadeDeUsuarios);
		this.nome = "Macro+DRA-CF Architecture";
		this.getConsumoMacro();
		this.getConsumoDRA();		
		this.getPotenciaDeGeracao();
		this.getTCO();
		this.getEstatisticas();
		//this.debug();
	}

	/**
	 * The Power Consumption of DRA-CF Only (KWH)
	 */
	public void getConsumoDRA(){		
		double[][] temp = Util.getProdutoPorEscalar(super.numeroDeAntenasDRA, DRA.potenciaAntenaAmplificadora);		
		double[][] temp2 = Util.getDivisao(super.numeroDeAntenasDRA, DRA.numeroMaximoDeAntenasPorRRU);		
		double temp3 = DRA.potenciaRRU+2*Fiber.potenciaSFP+DRA.potenciaPorPortaDU+(DRA.potenciaPorRackDU/DRA.numeroDePortasDUporRackDU);
		
		temp2 = Util.getProdutoPorEscalar(temp2, temp3);		
		this.potenciaDRAOnly = Util.getSoma(temp, temp2); 

		Util.converterEmKWH(this.potenciaDRAOnly);
		this.potenciaTotal = Util.getSoma(this.potenciaMacroOnly,this.potenciaDRAOnly);
	}

	/**
	 * Print values for Debug
	 */
	public void debug(){

	}
}