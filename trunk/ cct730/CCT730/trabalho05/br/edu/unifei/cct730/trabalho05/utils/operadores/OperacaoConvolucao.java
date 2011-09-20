package br.edu.unifei.cct730.trabalho05.utils.operadores;

/**
 * Classe responsavel por tratar as acoes referentes
 * a matriz de convolucao das imagens
 * 
 * @author fknappe
 *
 */
public class OperacaoConvolucao {

	/**
	 * Metodo que retorna uma matriz convoluida para
	 * ser utilizada pelo metodo de filtragem
	 * da media
	 * 
	 * @param valor
	 * 
	 * @return Short[][]
	 */
	public static Short[][] definirMatrizConvolucaoFiltroMedia(int valor) {
		// Declaracao das variaveis locais
		Short[][] matrizConvolucao = null;
		
		// Inicializando a matriz de convolucao
		matrizConvolucao = new Short[valor][valor];
		for(int i = 0; i < valor; i++) {
			for(int j = 0; j < valor; j++) {
				matrizConvolucao[i][j] = 1;
			}
		}
		
		return matrizConvolucao;
	}
	
	/**
	 * Metodo responsavel por retornar uma matriz de convolucao
	 * para ser utilizado pelo metodo de filtragem
	 * da mediana
	 * 
	 * @param int valor
	 * 
	 * @return Short[][]
	 */
	public static Short[][] definirMatrizConvolucaoFiltroMediana(int valor) {
		return new Short[valor][valor];
	}
	
	/**
	 * Metodo responsavel pela ordenacao de um elemento de
	 * acordo com seus vizinhos
	 * 
	 * @param int vetorVizinho
	 * 
	 * @return int[]
	 */
	public static int[] ordenadaVizinhos(int vetorVizinho[]) {
		int temp;
		for (int i = 0; i < vetorVizinho.length - 1; i++) {
			for (int j = 0; j < vetorVizinho.length - 1 - i; j++) {
				if (vetorVizinho[j] > vetorVizinho[j + 1]) {
					temp = vetorVizinho[j];
					vetorVizinho[j] = vetorVizinho[j + 1];
					vetorVizinho[j + 1] = temp;
				}
			}
		}
		return vetorVizinho;
	}
}
