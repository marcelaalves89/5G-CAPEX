package br.edu.unifesspa.malves.tests;

import java.util.HashMap;

import org.jfree.ui.RefineryUtilities;

import br.edu.unifesspa.malves.photovoltaics.Meter;
import br.edu.unifesspa.malves.photovoltaics.Panel;
import br.edu.unifesspa.malves.trafficforecast.Environment;
import br.edu.unifesspa.malves.transportnetwork.DRABF;
import br.edu.unifesspa.malves.transportnetwork.DRACF;
import br.edu.unifesspa.malves.transportnetwork.FemtoBB;
import br.edu.unifesspa.malves.transportnetwork.FemtoCB;
import br.edu.unifesspa.malves.util.GraficoLinha;
import br.edu.unifesspa.malves.util.Util;

public class Grafico01 {

	public Grafico01(){
		HashMap<String, double[]> tco = new HashMap<String, double[]>();

		//DRA-CF
		DRACF dracf = null;
		double[] tcoDRACF = new double[Panel.radiacao.length];

		//DRA-BF
		DRABF drabf = null;
		double[] tcoDRABF = new double[Panel.radiacao.length];

		//Femto-CB
		FemtoCB femtocb = null;
		double[] tcoFemtoCB = new double[Panel.radiacao.length];

		//Femto-BB
		FemtoBB femtobb = null;
		double[] tcoFemtoBB = new double[Panel.radiacao.length];

		for (int i=0; i<Panel.radiacao.length; i++){		
			dracf = new DRACF(Panel.radiacao[i], Environment.densidadeDeUsuariosPadrao);
			tcoDRACF[i] = dracf.estatisticas[2]/1000000.0;

			drabf = new DRABF(Panel.radiacao[i], Environment.densidadeDeUsuariosPadrao);
			tcoDRABF[i] = drabf.estatisticas[2]/1000000.0;

			femtocb = new FemtoCB(Panel.radiacao[i], Environment.densidadeDeUsuariosPadrao);
			tcoFemtoCB[i] = femtocb.estatisticas[2]/1000000.0;

			femtobb = new FemtoBB(Panel.radiacao[i], Environment.densidadeDeUsuariosPadrao);
			tcoFemtoBB[i] = femtobb.estatisticas[2]/1000000.0;
		}
		
		System.out.println(this.getClass().getSimpleName()+": Consumo Total de Cada Arquitetura (Milhoes de Reais) junto à Concessionária");
		System.out.println("DRA-CF: "+ (dracf.estatisticas[0]/1000)+ " MWH" );
		System.out.println("DRA-CF: "+ Util.formataValorEmReais(dracf.estatisticas[0]*Meter.custoKwhCompra) );
		System.out.println("DRA-BF: "+ (drabf.estatisticas[0]/1000)+ " KWH" );
		System.out.println("DRA-BF: "+ Util.formataValorEmReais(drabf.estatisticas[0]*Meter.custoKwhCompra) );
		System.out.println("Femto-CB: "+ (femtocb.estatisticas[0]/1000)+ " KWH" );
		System.out.println("Femto-CB: "+ Util.formataValorEmReais(femtocb.estatisticas[0]*Meter.custoKwhCompra) );
		System.out.println("Femto-BB: "+ (femtobb.estatisticas[0]/1000)+ " KWH" );
		System.out.println("Femto-BB: "+ Util.formataValorEmReais(femtobb.estatisticas[0]*Meter.custoKwhCompra) );
		System.out.println();
		
		tco.put("DRA-CF", tcoDRACF);
		tco.put("DRA-BF", tcoDRABF);
		tco.put("Femto-CB", tcoFemtoCB);
		tco.put("Femto-BB", tcoFemtoBB);
		
		double[] escalaX = {1.225, 7.275};
		double[] escalaY = {90, 613};

		GraficoLinha demo = new GraficoLinha(this.getClass().getSimpleName(), 
				"", 
				"Solar Irradiance [kWh/m²/day]", 
				"Capital Expenditure (CAPEX) [Million BRL]", 
				tco, 
				Panel.radiacao,
				escalaX,
				escalaY);
		demo.pack();
		RefineryUtilities.centerFrameOnScreen(demo);
		demo.setVisible(true);		
	}
}