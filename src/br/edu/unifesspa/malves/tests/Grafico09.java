package br.edu.unifesspa.malves.tests;

import java.util.HashMap;

import org.jfree.ui.RefineryUtilities;

import br.edu.unifesspa.malves.photovoltaics.Panel;
import br.edu.unifesspa.malves.trafficforecast.Environment;
import br.edu.unifesspa.malves.transportnetwork.DRABF;
import br.edu.unifesspa.malves.transportnetwork.DRACF;
import br.edu.unifesspa.malves.transportnetwork.FemtoBB;
import br.edu.unifesspa.malves.transportnetwork.FemtoCB;
import br.edu.unifesspa.malves.util.GraficoLinha;
import br.edu.unifesspa.malves.util.Util;


public class Grafico09 {

	public Grafico09(){

		HashMap<String, double[]> consumo = new HashMap<String, double[]>();
		
		DRACF dracf = new DRACF(Panel.radiacaoPadrao, Environment.densidadeDeUsuariosPadrao);
		consumo.put("DRA-CF", Util.getProdutoPorEscalar(dracf.consumoTotal,0.67*365));
		System.out.println((dracf.estatisticas[0]/1000.0)/21.0+" mWh");

		DRABF drabf = new DRABF(Panel.radiacaoPadrao, Environment.densidadeDeUsuariosPadrao);
		consumo.put("DRA-BF", Util.getProdutoPorEscalar(drabf.consumoTotal, 0.67*365));
		System.out.println((drabf.estatisticas[0]/1000.0)/21.0+" mWh");

		FemtoCB femtocb = new FemtoCB(Panel.radiacaoPadrao, Environment.densidadeDeUsuariosPadrao);
		consumo.put("Femto-CB", Util.getProdutoPorEscalar(femtocb.consumoTotal,0.67*365));
		System.out.println((femtocb.estatisticas[0]/1000.0)/21.0+" mWh");

		FemtoBB femtobb = new FemtoBB(Panel.radiacaoPadrao, Environment.densidadeDeUsuariosPadrao);
		consumo.put("Femto-BB", Util.getProdutoPorEscalar(femtobb.consumoTotal,0.67*365));
		System.out.println((femtobb.estatisticas[0]/1000.0)/21.0+" mWh");
		
		GraficoLinha demo2 = new GraficoLinha(this.getClass().getSimpleName(), 
				"", 
				"Years", 
				"Total Power Consumption", 
				consumo, 
				Environment.anos,
				null,
				null);
		demo2.pack();
		RefineryUtilities.centerFrameOnScreen(demo2);
		demo2.setVisible(true);
	}
}