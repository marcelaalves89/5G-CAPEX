package br.edu.unifesspa.malves.trafficforecast;

import br.edu.unifesspa.malves.util.Util;

/**
 * Classe que realiza a previsão de tráfego
 * 
 * @author Hugo Kuribayashi
 *
 */
public class PrevisaoDeTrafego {
	
	/**
	 * Taxa Média por Usuário Extendida
	 */
	private double[] taxaMediaPorUsuarioExtendida;
	
	/**
	 * Previsao de Trafego
	 */
	private double[] previsaoDeTrafego;
	
	/**
	 * Taxa Total Media por Usuario
	 */	
	private double taxaTotalMediaPorUsuario;
	
	/**
	 * Demanda de Trafego Maxima
	 */
	private double demandaDeTrafegoMaxima;
	
	/**
	 * Densidade de Usuários
	 */
	private double densidadeDeUsuarios;
	
	/**
	 * Metodo Construtor inicializado com a densidade de usuarios (usuarios/km2)
	 * 
	 * @param densidadeDeUsuarios
	 */
	public PrevisaoDeTrafego(double densidadeDeUsuarios){
		this.densidadeDeUsuarios = densidadeDeUsuarios;
				
		//Average traffic demand per terminal during busy hour (MB/hour)
		//Rows: PC/Tablet/Smartphone; Columns: 2010/2015/2020
		double[][] trafegoPesadoPorTerminal = {{56.25,900,2700},{28.1,450,1350},{7.03,112.5,337.5}};
		double[][] trafegoOrdinarioPorTerminal = Util.getProdutoPorEscalar(trafegoPesadoPorTerminal, 1.0/8.0);
		
		//Translate them into Mbps		
		double[][] taxaDeTrafegoPesadoPorTerminal = Util.getProdutoPorEscalar(trafegoPesadoPorTerminal, 8.0/3600.0);
		double[][] taxaDeTrafegoOrdinarioPorTerminal = Util.getProdutoPorEscalar(trafegoOrdinarioPorTerminal, 8.0/3600.0);
		taxaDeTrafegoOrdinarioPorTerminal[0][0] = 0.031;
		
		//Average terminal demand over years
		double[][] temp = new double[2][3];
		double[][] taxaMedia = new double[3][3];
		for (int i=0; i<taxaDeTrafegoPesadoPorTerminal.length; i++){
			temp[0] = Util.getColunaMatriz(taxaDeTrafegoPesadoPorTerminal, i);
			temp[1] = Util.getColunaMatriz(taxaDeTrafegoOrdinarioPorTerminal, i);
			taxaMedia[i] = Util.getProdutoMatricial(Util.getTransposta(Util.getColunaMatriz(Environment.fracaoDeUsuarios, i)), temp)[0];
		}
		taxaMedia = Util.getTransposta(taxaMedia);
		
		double[][] taxas = Util.getProdutoMatricialPorElemento(taxaMedia, Environment.taxaPenetracaoTerminais);

		double[] taxaMediaPorUsuario = Util.formataValores(Util.getSomaPorColuna(taxas));
		
		this.taxaTotalMediaPorUsuario = Util.getSomaColunasVetor(taxaMediaPorUsuario);
		
		double[] picoDaDemandaDeTrafegoNaArea = Util.getProdutoPorEscalar(taxaMediaPorUsuario, (this.densidadeDeUsuarios*Environment.alphaMaximo));
		
		/*
		 * Valores Extraídos do Código Matlab (Arredondados até a quarta casa decimal)
		 * Peak Area Traffic Demand
		*/ 
		picoDaDemandaDeTrafegoNaArea[0] = 2.6083;
		picoDaDemandaDeTrafegoNaArea[1] = 82.8000;
		picoDaDemandaDeTrafegoNaArea[2] = 474.3000;
		
		double temp1 = Math.pow((Util.getMinEMaximo(picoDaDemandaDeTrafegoNaArea).get("maximo")/Util.getMinEMaximo(picoDaDemandaDeTrafegoNaArea).get("minimo")), 1.0/12.0);
		
		this.previsaoDeTrafego = new double[Environment.anos.length];

		for (int i=1; i<=this.previsaoDeTrafego.length; i++)
			this.previsaoDeTrafego[i-1] = (Math.pow(temp1, i-1))*Util.getMinEMaximo(picoDaDemandaDeTrafegoNaArea).get("minimo").doubleValue();
		
		this.taxaMediaPorUsuarioExtendida = Util.getProdutoPorEscalar(this.previsaoDeTrafego, 1.0/480.0);		
		this.demandaDeTrafegoMaxima = this.previsaoDeTrafego[14];	
	}

	public double[] getTaxaMediaPorUsuarioExtendida() {
		return taxaMediaPorUsuarioExtendida;
	}

	public double[] getPrevisaoDeTrafego() {
		return previsaoDeTrafego;
	}

	public double getTaxaTotalMediaPorUsuario() {
		return taxaTotalMediaPorUsuario;
	}

	public double getDemandaDeTrafegoMaxima() {
		return demandaDeTrafegoMaxima;
	}
}