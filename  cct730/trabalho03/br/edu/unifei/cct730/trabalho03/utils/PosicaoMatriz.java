package br.edu.unifei.cct730.trabalho03.utils;


/**
 * Classe PosicaoMatriz
 * Armazena a posicao dos elementos de uma matriz
 * 
 * @author fknappe
 */
public class PosicaoMatriz {
	
	// Declaracao das variaveis globais
	private int linha = 0; 
	private int coluna = 0;

	/**
	 * Construtor
	 * 
	 * @param int l indice da linha de um elemento da matriz
	 * @param int c indice da coluna de um elemento da matriz
	 */
	public PosicaoMatriz(int l, int c) {
		this.linha = l;
		this.coluna = c;
	}
	
	// Metodos gettes e setters
	public int getColuna() {
		return coluna;
	}

	public int getLinha() {
		return linha;
	}
}
