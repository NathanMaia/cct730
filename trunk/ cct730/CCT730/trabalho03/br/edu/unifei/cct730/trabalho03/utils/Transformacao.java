package br.edu.unifei.cct730.trabalho03.utils;

/**
 * Classe responsavel por realizar as transformacoes geometricas
 * sobre a imagem sintetica
 * 
 * @author fknappe
 *
 */
public class Transformacao {
	
	//Declaracao das variaveis de instancia
	private MatrizCelula matriz = null;
	
	/**
	 * Construtor
	 * 
	 * @param MatrizCelula m
	 */
	public Transformacao(MatrizCelula m) {
		this.matriz = m;
	}
	
	/**
	 * Metodo responsavel por calcular o deslocamento dos pixels
	 * da imagem sintetica
	 * 
	 * @param int deslocamentoX
	 * @param int deslocamentoY
	 * @return int[][]
	 */
	public int[][] translada(int deslocamentoX, int deslocamentoY) { 
		MatrizCelula matrizNova = new MatrizCelula(matriz.getNumLinhas(), matriz.getNumColunas());
		for (int i = 0; i < matriz.getNumLinhas(); i++) {
			for (int j = 0; j < matriz.getNumColunas(); j++) {
				if (i - deslocamentoX >= 0 && j - deslocamentoY >= 0 && i - deslocamentoX <= matriz.getNumLinhas() - 1 && j - deslocamentoY <= matriz.getNumColunas() - 1) {
					matrizNova.add(matriz.getValorCelula(i - deslocamentoX, j - deslocamentoY), i, j);
				} else {
					matrizNova.add(0, i, j);
				}
			}
		}
		return matrizNova.getMatrizValores();
	}
	
	/**
	 * Metodo responsavel por calcular o escalamento dos pixels
	 * da imagem sintetica
	 * 
	 * @param double fator
	 * @return int[][]
	 */
	public int[][] escala(int fator) {
		MatrizCelula matrizNova = new MatrizCelula(matriz.getNumLinhas(), matriz.getNumColunas());
		for (int i = 0; i < matriz.getNumLinhas(); i++) {
			for (int j = 0; j < matriz.getNumColunas(); j++) {
				for (int m = 0; m < fator; m++) {
					for (int p = 0; p < fator; p++) {
						if (i*fator + m <= matriz.getNumLinhas() - 1 && j * fator + p <= matriz.getNumColunas() - 1){							
							matrizNova.add(matriz.getValorCelula(i, j), i*fator + m, j * fator + p);
						}
					}
				}			
			}
		}
		return matrizNova.getMatrizValores();
	}
	
	/**
	 * Metodo responsavel por calcular o rotacionamento dos pixels
	 * da imagem sintetica de acordo com um angulo
	 * 
	 * @param int angulo
	 * @return int[][]
	 */
	public int[][] rotaciona(int angulo) {
		MatrizCelula matrizNova = new MatrizCelula(matriz.getNumLinhas(), matriz.getNumColunas());
		matrizNova.zerarMatriz();

		int x2, y2 = 0;
		double anguloRadianos = Math.toRadians(angulo);
		double cosseno = Math.cos(anguloRadianos);
		double seno = Math.sin(anguloRadianos);

		for (int i = 0; i < matriz.getNumLinhas(); i++) {

			for (int j = 0; j < matriz.getNumColunas(); j++) {
				y2 = (int) Math.round(j * cosseno - i * seno);
				x2 = (int) Math.round(i * cosseno + j * seno);

				if (x2 >= 0 && y2 >= 0 && x2 <= matriz.getNumLinhas() - 1 && y2 <= matriz.getNumColunas() - 1) {
					matrizNova.add(matriz.getValorCelula(i, j), x2, y2);
				} else {
					matrizNova.add(0, i, j);
				}
			}
		}
		return matrizNova.getMatrizValores();
	}
	
	public int[][] rotaciona(int angulo, int referenciaX, int referenciaY) {
		MatrizCelula matrizNova = new MatrizCelula(matriz.getNumLinhas(), matriz.getNumColunas());
		matrizNova.zerarMatriz();

		int x2, y2;
		int m, n;

		double anguloRadianos = Math.toRadians(angulo);
		double cosseno = Math.cos(anguloRadianos);
		double seno = Math.sin(anguloRadianos);

		for (int i = 0; i < matriz.getNumLinhas(); i++) {

			for (int j = 0; j < matriz.getNumColunas(); j++) {

				m = i - referenciaX;
				n = j - referenciaY;

				y2 = (int) Math.round(n * cosseno - m * seno);
				x2 = (int) Math.round(m * cosseno + n * seno);

				y2 = y2 + referenciaY;
				x2 = x2 + referenciaX;

				if (x2 >= 0 && y2 >= 0 && x2 <= matriz.getNumLinhas() - 1 && y2 <= matriz.getNumColunas() - 1) {
					matrizNova.add(matriz.getValorCelula(i, j), x2, y2);
				} else {
					matrizNova.add(0, i, j);
				}
			}
		}
		return matrizNova.getMatrizValores();
	}
	
	/**
	 * Metodo responsavel por calcular o espelhamento horizontal dos
	 * pixels da imagem sintetica 
	 * 
	 * @return int[][]
	 */
	public int[][] espelhamentoHorizontal() {
		MatrizCelula matrizNova = new MatrizCelula(matriz.getNumLinhas(), matriz.getNumColunas());
		matrizNova.zerarMatriz();
		for (int i = 0; i < matriz.getNumLinhas(); i++) {
			for (int j = 0; j < matriz.getNumColunas(); j++) {
				matrizNova.add(matriz.getValorCelula(i, j), matriz.getNumColunas() - i - 1, j);
			}
		}
		return matrizNova.getMatrizValores();
	}
	
	/**
	 * Metodo responsavel por calcular o espelhamento vertical dos
	 * pixels da imagem sintetica
	 * 
	 * @return int[][]
	 */
	public int[][] espelhamentoVertical() {
		MatrizCelula matrizNova = new MatrizCelula(matriz.getNumLinhas(), matriz.getNumColunas());
		matrizNova.zerarMatriz();
		for (int i = 0; i < matriz.getNumLinhas(); i++) {
			for (int j = 0; j < matriz.getNumColunas(); j++) {
				matrizNova.add(matriz.getValorCelula(i, j), i, matriz.getNumLinhas() - j - 1);
			}
		}
		return matrizNova.getMatrizValores();
	}
}
