
package br.edu.unifesspa.malves.transportnetwork;

import br.edu.unifesspa.malves.backhaul.Microwave;
import br.edu.unifesspa.malves.photovoltaics.Inverter;
import br.edu.unifesspa.malves.photovoltaics.Meter;
import br.edu.unifesspa.malves.photovoltaics.Panel;
import br.edu.unifesspa.malves.radionetwork.DRABasedDeployment;
import br.edu.unifesspa.malves.tco.CAPEX;
import br.edu.unifesspa.malves.trafficforecast.Environment;
import br.edu.unifesspa.malves.util.Util;
import br.edu.unifesspa.malves.wireless.Macro;

public abstract class DRAPVBased extends DRABasedDeployment{

	/**
	 * Total Power Consumption of DRA-CF Deployment, including the power consumption of the outdoor macro BS network
	 */
	public double[][] potenciaTotal;

	/**
	 * Total Power Consumption of Macro+DRA-CF Architecture
	 */
	public double[] consumoTotal;
	
	/**
	 * 
	 */
	public double[] consumoASerGerado;

	/**
	 * Power Consumption of only DRA-CF Deployment
	 */
	public double[][] potenciaDRAOnly;

	/**
	 * Power Consumption of MacroBS Only
	 */
	public double[][] potenciaMacroOnly;

	/**
	 * Total number of String Inverters
	 */
	public double[] numeroInversores;
	
	/**
	 * Total number of PV Panels
	 */
	public double[] numeroPaineis;

	/**
	 * Generated power
	 */
	public double[] energiaGerada;

	/**
	 * TCO
	 */
	public double tco;

	/**
	 * Estatisticas
	 */
	public double[] estatisticas;

	/**
	 * Radiacao
	 */
	public double radiacao;

	/**
	 * 
	 */
	public double numeroPaineisPorInversor;

	/**
	 * 
	 */
	private double[][] matrizConsumoMinimo;
	
	/**
	 * 
	 */
	private double[] energiaExtra;

	/**
	 * Super constructor call and initializing values
	 */
	public DRAPVBased(double radiacao, double densidadeDeUsuarios){
		super(densidadeDeUsuarios);
		this.nome = "DRA-PV Based Architecture";
		int dimensao = super.numeroDeAntenasDRA.length;
		this.potenciaDRAOnly = new double [dimensao][dimensao];
		this.potenciaMacroOnly = new double [dimensao][dimensao];
		this.potenciaTotal = new double [dimensao][dimensao];
		this.numeroInversores = new double[dimensao];
		this.numeroPaineis = new double[dimensao];
		this.consumoTotal = new double[dimensao];
		this.consumoASerGerado = new double[dimensao];
		this.energiaGerada = new double[dimensao];
		this.energiaExtra = new double[dimensao];
		this.tco = 0;
		this.estatisticas = new double[6];
		this.radiacao = radiacao;
	}

	/**
	 * Calculating the Total Power Consumption of Macro+DRA-CF Architecture (KWH)
	 */
	public void getConsumoMacro(){				
		this.potenciaMacroOnly = Util.getProdutoPorEscalar(super.numeroDeMacros,Macro.potencia+(2.0*Microwave.potenciaBaixa));		
		Util.converterEmKWH(potenciaMacroOnly);
	}

	/**
	 * 
	 */
	public void getPotenciaDeGeracao(){		
		this.consumoTotal = Util.getDiagonalPrincipal(this.potenciaTotal);		
		double[][] matrizDePotencia = Util.getZeros(this.consumoTotal.length, this.consumoTotal.length);
		this.matrizConsumoMinimo = Util.getZeros(this.consumoTotal.length, this.consumoTotal.length);

		double geracaoDiariadoPainel = Panel.area*Panel.eficiencia*this.radiacao;
		this.numeroPaineisPorInversor = Math.floor((Inverter.potenciaNominalEntrada*Panel.hspPadrao)/geracaoDiariadoPainel);		
		double potenciaSaidaInversor = Inverter.eficiencia*this.numeroPaineisPorInversor*geracaoDiariadoPainel;
		
		System.out.println(this.nome);
		System.out.println("Irradiação Solar: "+this.radiacao);
		System.out.println("Geracao Painel: "+geracaoDiariadoPainel);
		System.out.println("HSP: "+Panel.hspPadrao);
		System.out.println("Inversor Eficiencia: "+Inverter.eficiencia);
		System.out.println("Inversor Potencia Entrada: "+Inverter.potenciaNominalEntrada);
		System.out.println("Inversor Potencia Saida: "+potenciaSaidaInversor);
		System.out.println("Numero de Paineis por Inversor: "+numeroPaineisPorInversor);
	
		for (int i=0; i<this.energiaGerada.length; i++){
			System.out.println();
			System.out.println("Consumo: "+this.consumoTotal[i]);
			
			this.consumoASerGerado[i] = this.consumoTotal[i] - Util.getSomaPorColuna(matrizDePotencia, i);
			
			System.out.println("Consumo a Ser Gerado (apenas Produção): "+this.consumoASerGerado[i]);
					
			if (this.consumoASerGerado[i] < potenciaSaidaInversor)
				this.numeroInversores[i] = 1;
			else this.numeroInversores[i] = Math.round(this.consumoASerGerado[i]/potenciaSaidaInversor);
			
			System.out.println("Numero de Inversores: "+this.numeroInversores[i]);
			
			this.numeroPaineis[i] = numeroInversores[i] * this.numeroPaineisPorInversor;
			
			System.out.println("Numero de Paineis: "+this.numeroPaineis[i]);
			
			this.matrizConsumoMinimo[i][i] = numeroInversores[i] * Meter.consumoMinimo;
			
			System.out.println("Consumo Minimo Diagonal: "+this.matrizConsumoMinimo[i][i]);
			
			this.consumoASerGerado[i] -= Util.getSomaPorColuna(this.matrizConsumoMinimo, i);
			
			System.out.println("Consumo a ser gerado: "+this.consumoASerGerado[i]);
			
			matrizDePotencia[i][i] = this.numeroInversores[i] * potenciaSaidaInversor;
			
			System.out.println("Matriz de Potencia: "+matrizDePotencia[i][i]);
			
			energiaExtra[i] = matrizDePotencia[i][i] - this.consumoASerGerado[i];
			
			System.out.println("Energia Extra: "+energiaExtra[i]);
			System.out.println();
			
			Util.getDepreciacao(matrizDePotencia,Panel.taxaDesempenho);
			Util.getDepreciacao(this.matrizConsumoMinimo,1);
		}
		this.energiaGerada = Util.getSomaPorColuna(matrizDePotencia);
		this.estatisticas[4] = Util.getSomaColunasVetor(Util.getSomaPorColuna(this.matrizConsumoMinimo))*365/1000;	
	}

	/**
	 * Calculate CAPEX
	 */	
	public void getTCO(){
		//CAPEX e OPEX
		double capex = 0;
		for (int i=0; i<this.consumoTotal.length; i++){
			double temp = Math.pow(CAPEX.taxaDepreciacaoFinanceira, i);
			if (temp < 0.6)
				temp = 0.6;
				
			capex += this.numeroPaineis[i]*Panel.custoPorPainel*temp;
			capex += this.numeroPaineis[i]*Panel.custoKitInstalacao*temp;
			capex += this.numeroInversores[i]*Inverter.custo*temp;																		
		}
		capex *= (1+CAPEX.taxaDeInstalacao);
		this.tco = capex;
		
		System.out.println("Numero de Paineis: ");
		Util.imprime(this.numeroPaineis);
		System.out.println("Numero de Inversores: ");
		Util.imprime(this.numeroInversores);
	}

	/**
	 * Realiza o calculo das estatisticas
	 */
	@SuppressWarnings("unused")
	public void getEstatisticas(){				
		double somaConsumo = 0, somaGeracao = 0, somaExtra = 0, diferenca = 0;		
		double[] disponibilidadeMinimoDaRede = Util.getSomaPorColuna(this.matrizConsumoMinimo);
		double[] energiaMinimaASerGerada = Util.getDiferenca(this.consumoTotal, disponibilidadeMinimoDaRede);

		for (int i=0; i<consumoTotal.length; i++){
			somaConsumo += (this.consumoTotal[i]*365);			
			somaGeracao += (this.energiaGerada[i]*365);
			somaExtra += (this.energiaExtra[i]*365);
		}
						
		this.estatisticas[0] = somaConsumo;
		this.estatisticas[1] = somaGeracao;		
		this.estatisticas[2] = this.tco;
		
		double temp = ((somaConsumo*Meter.custoKwhCompra)+(diferenca*Meter.custoKwhVenda))-this.tco;
		this.estatisticas[3] = (temp/(this.densidadeDeUsuarios*Environment.area))/Environment.anos.length;		
		this.estatisticas[5] = somaExtra;				
	}

	public abstract void getConsumoDRA();
	
	public abstract void debug();
}