package br.edu.unifesspa.malves.util;

import java.text.DecimalFormat;
import java.util.HashMap;

public class Util {

	/**
	 * Retorna o produto entre Matriz e um numero inteiro
	 * @param matriz
	 * @param escalar
	 * @return 
	 */
	public static double[][] getProdutoPorEscalar(double[][] matriz, double escalar){
		double[][] results = new double[matriz.length][matriz[0].length];
		for (int i=0; i<matriz.length; i++)
			for (int j=0; j<matriz[0].length; j++)
				results[i][j] = matriz[i][j] * escalar;
		return results;
	}

	public static double[][] formataValores(double[][] matriz){
		double[][] results = new double[matriz.length][matriz[0].length];
		DecimalFormat formato = new DecimalFormat("#.####");   
		for (int i=0; i<matriz.length; i++)
			for (int j=0; j<matriz[0].length; j++){
				results[i][j] = Double.valueOf(formato.format(matriz[i][j]).replace(',', '.'));
			}
		return results;
	}

	public static double[] formataValores(double[] vetor){
		double[] results = new double[vetor.length];
		DecimalFormat formato = new DecimalFormat("#.####");   
		for (int i=0; i<vetor.length; i++)
			results[i] = Double.valueOf(formato.format(vetor[i]).replace(',', '.'));			
		return results;
	}

	/**
	 * Retorna o produto entre vetor e um numero inteiro
	 * @param matriz
	 * @param escalar
	 * @return  
	 */
	public static double[] getProdutoPorEscalar(double[] vetor, double escalar){
		double[] results = new double[vetor.length];
		for (int i=0; i<vetor.length; i++)
			results[i] = vetor[i] * escalar;
		return results;
	}

	/**
	 * Imprime uma matriz e seus dados
	 * @param vetor
	 */
	public static void imprime(double[][] matriz){
		System.out.println("Dados da Matriz: ");
		System.out.println("Numero de Linhas: "+matriz.length);
		System.out.println("Numero de Colunas: "+matriz[0].length);
		for (int i=0; i<matriz.length; i++)
			for (int j=0; j<matriz[0].length; j++)
				System.out.println("Elemento "+i+"-"+j+": "+matriz[i][j]);
		System.out.println();
	}

	/**
	 * Imprime um vetor e seus dados
	 * @param vetor
	 */
	public static void imprime(double[] vetor){
		System.out.println("Vector Data: ");
		System.out.println("Number of Elements: "+vetor.length);
		for (int i=0; i<vetor.length; i++)
			System.out.println("Element "+i+": "+vetor[i]);
		System.out.println();
	}
	
	/**
	 * Imprime um vetor e seus dados multiplicando por X
	 * @param vetor
	 */
	public static void imprime(double[] vetor, double fatorX){
		System.out.println("Vector Data: ");
		System.out.println("Number of Elements: "+vetor.length);
		for (int i=0; i<vetor.length; i++)
			System.out.println("Element "+i+": "+vetor[i]*fatorX);
		System.out.println();
	}

	/**
	 * Retorna a transposta de uma matriz
	 * @param matriz
	 * @return
	 */
	public static double[][] getTransposta(double[][] matriz){
		double[][] results = new double[matriz[0].length][matriz.length];
		for(int linha=0;linha<matriz.length;linha++)
			for(int coluna=0;coluna<matriz[linha].length;coluna++)
				if(coluna>linha)
					results[linha][coluna]=matriz[coluna][linha];
				else if(coluna==linha)
					results[linha][coluna]=matriz[linha][coluna];
				else
					results[linha][coluna]=matriz[coluna][linha];
		return results;	
	}

	/**
	 * Retorna a transposta de um vetor linha
	 * @param vetorLinha
	 * @return
	 */
	public static double[][] getTransposta(double[] vetorLinha){
		double[][] results = new double[1][vetorLinha.length];
		for (int i=0; i<vetorLinha.length; i++)
			results[0][i] = vetorLinha[i];	
		return results;
	}


	/**
	 * Retorna a i-ésima coluna de uma matriz 
	 * @param matriz
	 * @param coluna
	 * @return
	 */
	public static double[] getColunaMatriz(double[][] matriz, int coluna){
		double[] results = new double[matriz.length];
		for (int i=0; i<matriz.length; i++)
			results[i] = matriz[i][coluna];
		return results;
	}

	/**
	 * Retorna o produto matricial em a e b (a*b)
	 * @param a
	 * @param b
	 * @return
	 */
	public static double[][] getProdutoMatricial(double[][] a, double[][] b){
		if (a[0].length != b.length) throw new RuntimeException("Dimensões inconsistentes. Impossível multiplicar as matrizes");
		double[][] results = new double[ a.length ][ b[0].length ];
		for (int i = 0; i < a.length; i++)
			for (int j = 0; j < b[0].length; j++) 
				for (int k = 0; k < a[0].length; k++) 
					results[i][j] += (a[i][k] * b[k][j]);
		return results;
	}

	/**
	 * Retorna produto matricial elemento por elemento
	 * @return
	 */
	public static double[][] getProdutoMatricialPorElemento(double[][] a, double[][] b){
		if ((a.length != b.length) || (a[0].length != b[0].length)) throw new RuntimeException("Dimensões inconsistentes. Impossível multiplicar as matrizes");
		double[][] results = new double[a.length][a[0].length];
		for (int i=0; i<a.length; i++)
			for (int j=0; j<a[0].length; j++)
				results[i][j] = a[i][j] * b[i][j];
		return results;
	}

	/**
	 * Retorn uma vetor linha com a soma das colunas de uma matriz
	 * @param matriz
	 * @return
	 */
	public static double[] getSomaPorColuna(double[][] matriz){
		double[] results = new double[matriz[0].length];
		for (int j=0; j<matriz[0].length; j++)
			for (int i=0; i<matriz.length; i++)
				results[j] += matriz[i][j];
		return results;
	}

	/**
	 * Retorn uma vetor linha com a soma das colunas de uma matriz
	 * @param matriz
	 * @return
	 */
	public static double getSomaPorColuna(double[][] matriz, int coluna){
		double soma = 0;
		for (int i=0; i<matriz.length; i++)
			soma += matriz[i][coluna];
		return soma;
	}

	/**
	 * 
	 * @param numeroLinhas
	 * @param numeroColunas
	 * @return
	 */
	public static double[][] getZeros(int numeroLinhas, int numeroColunas){
		double[][] results = new double[numeroLinhas][numeroColunas];
		for (int i=0; i<numeroLinhas; i++)
			for (int j=0; j<numeroColunas; j++)
				results[i][j] = 0;
		return results;
	}

	/**
	 * 
	 */
	public static void getDepreciacao(double[][] matriz, double fator){
		for (int i=0; i<matriz.length; i++)
			for (int j=i; j<matriz[0].length; j++)
				if (i != j)
					matriz[i][j] = matriz[i][j-1]*fator;
	}

	/**
	 * Retorn um valor (double) com o total das colunas do vetor
	 * @param vetor
	 * @return
	 */
	public static double getSomaColunasVetor(double[] vetor){
		double results = 0;
		for (int i=0; i<vetor.length; i++)
			results += vetor[i];
		return results;
	}

	/**
	 * Retorn uma collection com os valores maximos e minimos de um vetor
	 * @param vetor
	 * @return
	 */
	public static HashMap<String, Double> getMinEMaximo(double[] vetor){
		HashMap<String, Double> results = new HashMap<String, Double>();
		double minimo = 10000;
		double maximo = -1;
		for (int i=0; i<vetor.length; i++){
			if (vetor[i] < minimo)
				minimo = vetor[i];
			if (vetor[i] > maximo)
				maximo = vetor[i];
		}
		results.put("maximo", maximo);
		results.put("minimo", minimo);
		return results;
	}


	/**
	 * Retorna um vetor elevado à uma dada potencia
	 * @param vetor
	 * @param potencia
	 * @return
	 */
	public static double[] getPotencia(double[] vetor, double potencia){
		double[] results = new double[vetor.length];
		for (int i=0; i<vetor.length; i++)
			results[i] = Math.pow(vetor[i], potencia);
		return results;
	}

	/**
	 * 
	 * @param vetor
	 * @param dividendo
	 * @return
	 */
	public static double[] getDivisao(double[] vetor, double dividendo){
		double[] results = new double[vetor.length];
		for (int i=0; i<vetor.length; i++)
			results[i] = Math.ceil(dividendo/vetor[i]);
		return results;
	}

	/**
	 * 
	 * @param vetor
	 * @param dividendo
	 * @return
	 */
	public static double[][] getDivisao(double[][] vetorColuna, double divisor){
		double[][] results = new double[vetorColuna.length][vetorColuna[0].length];
		for (int i=0; i<vetorColuna.length; i++)
			results[i][0] = Math.ceil(vetorColuna[i][0]/divisor);
		return results;
	}

	/**
	 * Soma duas matrizes
	 * @param a
	 * @param b
	 * @return
	 */
	public static double[][] getSoma(double[][] a, double[][] b){
		double[][] results = new double[a.length][a[0].length];
		for (int i=0; i<a.length; i++)
			for (int j=0; j<a[0].length; j++)
				results[i][j] = a[i][j] + b[i][0];
		return results;
	}

	/**
	 * Soma duas matrizes
	 * @param a
	 * @param b
	 * @return
	 */
	public static double[][] getSoma(double[][] a, double[] b){
		double[][] results = new double[a.length][a[0].length];
		for (int i=0; i<a.length; i++)
			for (int j=0; j<a[0].length; j++)
				results[i][j] = a[i][j] + b[i];
		return results;
	}

	/**
	 * Soma duas matrizes
	 * @param a
	 * @param b
	 * @return
	 */
	public static double[] getSoma(double[] a, double[] b){
		double[] results = new double[a.length];
		for (int i=0; i<a.length; i++)
			results[i] = a[i] + b[i];
		return results;
	}

	public static double[][] getDiferenca(double[][] a, double[] b){
		double[][] results = new double[a.length][a[0].length];
		for (int i=0; i<a.length; i++)
			for (int j=0; j<a[0].length; j++)
				results[i][j] = (a[i][j] - b[i]);
		return results;
	}

	public static double[] getDiferenca(double[] a, double[] b){
		double[] results = new double[a.length];
		for (int i=0; i<a.length; i++)
			results[i] = a[i] - b[i];
		return results;
	}

	public static void converterEmKWH(double[][] a){
		for (int i=0; i<a.length; i++)
			for (int j=0; j<a[0].length; j++)
				a[i][j] = ((a[i][j]*24.0)/1000.0);
	}

	public static double[] getDiagonalPrincipal(double[][] matriz){
		double[] diagonal = new double[matriz.length];
		for (int i=0; i<matriz.length; i++)
			for (int j=0; j<matriz.length; j++)
				if (i ==j)
					diagonal[i] = matriz[i][j];
		return diagonal;
	}

	public static String formataValorEmReais(double valor) {  
		DecimalFormat d = new DecimalFormat("R$ #,##0.00");  
		return d.format(valor);  
	}  
}