package br.edu.unifei.cct730.trabalho04.utils;


public class Histograma {
	
	// Declaração das variaveis de instância
	private int[] histograma;
	private int faixas;

	public Histograma(int faixas) {
		this.faixas = faixas;
		this.histograma = new int[255];
	}

	public int getFaixas() {
		return faixas;
	}

	public void setFaixas(int faixas) {
		this.faixas = faixas;
	}

	public void acrescentar(int linha) {
		histograma[linha]++;
	}
	
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

	public double[] getPorcentagensAcumuladas() {
		double[] porcentagensAcumuladas = new double[histograma.length];
		double acumulador = 0;
		int i;
		
		for (i = 0; i < histograma.length; i++) {
			acumulador =+ histograma[i];
			porcentagensAcumuladas[i] = acumulador;
		}
		return porcentagensAcumuladas;
	}
}