package br.edu.unifesspa.malves.transportnetwork;

import br.edu.unifesspa.malves.backhaul.Fiber;
import br.edu.unifesspa.malves.backhaul.VDSL;
import br.edu.unifesspa.malves.util.Util;
import br.edu.unifesspa.malves.wireless.Femto;

/**
 * 
 * @author	Marcela Alves
 * @since	2016-07-12
 *
 */
public class FemtoCB extends FemtoPVBased {

	/**
	 * Super constructor call and initializing values
	 */
	public FemtoCB(double incidenciaSolar, double densidadeDeUsuarios){
		super(incidenciaSolar, densidadeDeUsuarios);
		this.nome = "Macro+Femto-CB Architecture";
		this.getConsumoMacro();
		this.getConsumoFemto();
		this.getPotenciaDeGeracao();
		this.getTCO();
		this.getEstatisticas();
		//this.debug();
	}

	/**
	 * Calculating the Power Consumption of Femto-CB Only (KWH)
	 */
	public void getConsumoFemto(){		
		double[][] temp = Util.getProdutoPorEscalar(super.numeroDeFemtos, Femto.potencia+VDSL.potenciaModemFemto);
		double temp2 = (VDSL.potenciaDSLAM+2*Fiber.potenciaSFP)/VDSL.numeroDePortasDSLAM;
		double[][] temp3 = Util.getProdutoPorEscalar(super.numeroDeFemtos, temp2);
		double temp4 = VDSL.potenciaSwitchGigabitEthernet/(VDSL.numeroDePortasDSLAM*VDSL.numeroDePortasSwitchGigabitEthernet);
		double[][] temp5 = Util.getProdutoPorEscalar(super.numeroDeFemtos, temp4);
		this.potenciaFemtoOnly = Util.getSoma(temp, Util.getSoma(temp3,temp5));		
		Util.converterEmKWH(this.potenciaFemtoOnly);
		this.potenciaTotal = Util.getSoma(this.potenciaMacroOnly,this.potenciaFemtoOnly);
	}
	
	/**
	 * Print values for Debug
	 */
	public void debug(){

	}
}