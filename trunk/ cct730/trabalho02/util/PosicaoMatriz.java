package util;


/**
 * Classe PosicaoMatriz
 * Armazena a posicao dos elementos de uma matriz
 * 
 * @author fknappe
 */
public class PosicaoMatriz {
	// Declaracao das variaveis
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
	
	// M�todos gettes e setters

	/**
	 * Metodo que retorna o indice da coluna do elemento da matriz
	 * @return int
	 */
	public int getColuna() {
		return coluna;
	}

	/**
	 * Metodo que retorna o indice da coluna do elemento da matriz
	 * @return int
	 */
	public int getLinha() {
		return linha;
	}
}
