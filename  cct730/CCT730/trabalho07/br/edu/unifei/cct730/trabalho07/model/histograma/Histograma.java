package br.edu.unifei.cct730.trabalho07.model.histograma;


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

	/**
	 * Metodo que calcula a porcentagem das faixas
	 * de nivel de faixa do histograma
	 * 
	 * @return void
	 */
	public void calcularPorcentagens() {
		int acumulado = getPorcentagensAcumulado();
		for (int i = 0; i < histograma.length; i++) {
			porcentagens[i] =   100.0 * (double)histograma[i]/(double)(acumulado);
		}
	}

	/**
	 * Metodo que retorna o valor total acumulado de 
	 * niveis de cinza presentes no histograma da
	 * imagem
	 * 
	 * @return int
	 */
	private int getPorcentagensAcumulado() {
		// Declaracao das variaveis locais
		int acumulado = 0;

		for(int i = 0; i < histograma.length; i++) {
			acumulado += histograma[i];
		}

		return acumulado;
	}

	// Métodos getters e setters
	public int[] getHistograma() {
		return histograma;
	}

	public double[] getPorcentagens() {
		return this.porcentagens;		
	}
}