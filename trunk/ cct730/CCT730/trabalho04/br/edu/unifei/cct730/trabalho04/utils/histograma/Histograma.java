package br.edu.unifei.cct730.trabalho04.utils.histograma;


/**
 * Classe representativa dos dados do histograma
 * da imagem
 * 
 * @author fknappe
 *
 */
public class Histograma {
	
	// Declaração das variaveis de instância
	private int[] histograma = null;
	private double[] porcentagens = null;
	
	/**
	 * Construtor
	 * 
	 */
	public Histograma() {
		this.histograma = new int[255];
		this.porcentagens = new double[255];
	}

	/**
	 * Metodo que adiciona uma nova entrada ao histograma
	 * da imagem
	 * 
	 * @param int linha
	 * 
	 * @return void
	 */
	public void acrescentar(int linha) {
		histograma[linha]++;
	}
	
	/**
	 * Metodo que retorna o nivel de cinza armazendo no indice
	 * especificado
	 * 
	 * @param int indice
	 * 
	 * @return int
	 */
	public int getNivelCinza(int indice) {
		return histograma[indice];
	}
	
	/**
	 * Metodo que retorna o tom de cinza que mais
	 * aparece no histograma
	 * 
	 * @return int
	 */
	public int getMaiorValor() {
		// Declaracao das variaveis locais
		int maiorValor = histograma[0];
		
		for (int i = 1; i < histograma.length; i++) {
			if (histograma[i] > maiorValor) {
				maiorValor = histograma[i];
			}
		}
		return maiorValor;
	}

	// Métodos getters e setters
	public int[] getHistograma() {
		return histograma;
	}
	
	public double[] getPorcentagens() {
		return this.porcentagens;		
	}
}