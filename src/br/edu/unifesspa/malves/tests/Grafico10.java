package br.edu.unifesspa.malves.tests;

import br.edu.unifesspa.malves.photovoltaics.Panel;
import br.edu.unifesspa.malves.trafficforecast.Environment;
import br.edu.unifesspa.malves.transportnetwork.DRACF;



public class Grafico10 {

	public Grafico10(){
		
		new DRACF(Panel.radiacaoPadrao, Environment.densidadeDeUsuariosPadrao);		

	}
}