package br.edu.unifesspa.malves.tests;

import java.util.HashMap;

import org.jfree.ui.RefineryUtilities;

import br.edu.unifesspa.malves.photovoltaics.Panel;
import br.edu.unifesspa.malves.trafficforecast.Environment;
import br.edu.unifesspa.malves.transportnetwork.DRABF;
import br.edu.unifesspa.malves.transportnetwork.DRACF;
import br.edu.unifesspa.malves.transportnetwork.FemtoBB;
import br.edu.unifesspa.malves.transportnetwork.FemtoCB;
import br.edu.unifesspa.malves.util.GraficoBarra;

public class Grafico04 {

	public Grafico04(){
		HashMap<String, Double> emissao = new HashMap<String, Double>();

		DRACF dracf = new DRACF(Panel.radiacaoPadrao, Environment.densidadeDeUsuariosPadrao);
		double emissaoDRACF = ((dracf.estatisticas[1]*Environment.fatorCO2Padrao)/(Environment.densidadeDeUsuariosPadrao*Environment.area))/Environment.anos.length;

		DRABF drabf = new DRABF(Panel.radiacaoPadrao, Environment.densidadeDeUsuariosPadrao);
		double emissaoDRABF = ((drabf.estatisticas[1]*Environment.fatorCO2Padrao)/(Environment.densidadeDeUsuariosPadrao*Environment.area))/Environment.anos.length;

		FemtoCB femtocb = new FemtoCB(Panel.radiacaoPadrao, Environment.densidadeDeUsuariosPadrao);
		double emissaoFemtoCB = ((femtocb.estatisticas[1]*Environment.fatorCO2Padrao)/(Environment.densidadeDeUsuariosPadrao*Environment.area))/Environment.anos.length;

		FemtoBB femtobb = new FemtoBB(Panel.radiacaoPadrao, Environment.densidadeDeUsuariosPadrao);
		double emissaoFemtoBB = ((femtobb.estatisticas[1]*Environment.fatorCO2Padrao)/(Environment.densidadeDeUsuariosPadrao*Environment.area))/Environment.anos.length;

		emissao.put("DRA-CF", emissaoDRACF);
		emissao.put("DRA-BF", emissaoDRABF);
		emissao.put("Femto-CB", emissaoFemtoCB);
		emissao.put("Femto-BB", emissaoFemtoBB);
		
		double[] escalaY = {47, 70};

		GraficoBarra demo = new GraficoBarra(
				this.getClass().getSimpleName(), 
				"", 
				"", 
				"Avoided CO2 Emissions [kg/user/year]", 
				emissao,
				escalaY);
		demo.pack();
		RefineryUtilities.centerFrameOnScreen(demo);
		demo.setVisible(true);		
	}
}