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
	private int[] histograma;
	private int faixas;

	/**
	 * Construtor
	 * 
	 * @param int faixas
	 */
	public Histograma(int faixas) {
		this.faixas = faixas;
		this.histograma = new int[255];
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
	 * Metodo que retorna as porcentagens dos
	 * tons de cinza do imagem
	 * 
	 * @return double[]
	 */
	public double[] getPorcentagens() {
		int i;
		double[] porcentagens;
		double amplitude;
		
		porcentagens = new double[faixas];
		amplitude = 255.0/faixas;
		
		for (i = 0; i < 255; i++) {
			porcentagens[(int)(i/amplitude)] += histograma[i];
		}
		
		for (i = 0; i < faixas; i++) {
			porcentagens[i] /= 255;
		}
		return porcentagens;		
	}

	/**
	 * Metodo que retorna o valor das porcentagens acumuladas
	 * do histograma
	 * 
	 * @return double[]
	 */
	public double[] getPorcentagensAcumuladas() {
		double[] porcentagensAcumuladas = new double[histograma.length];
		double acumulador = 0;
		
		for (int i = 0; i < histograma.length; i++) {
			acumulador =+ histograma[i];
			porcentagensAcumuladas[i] = acumulador;
		}
		return porcentagensAcumuladas;
	}
	
	// Métodos getters e setters
	public int getFaixas() {
		return faixas;
	}

	public void setFaixas(int faixas) {
		this.faixas = faixas;
	}

	public int[] getHistograma() {
		return histograma;
	}
}