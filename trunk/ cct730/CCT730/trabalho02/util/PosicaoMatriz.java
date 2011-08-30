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
	 * @param int l ind’ce da linha de um elemento da matriz
	 * @param int c ind’ce da coluna de um elemento da matriz
	 */
	public PosicaoMatriz(int l, int c) {
		this.linha = l;
		this.coluna = c;
	}
	
	// MŽtodos gettes e setters

	/**
	 * Metodo que retorna o in’dice da coluna do elemento da matriz
	 * @return int
	 */
	public int getColuna() {
		return coluna;
	}

	/**
	 * Metodo que retorna o ’ndice da coluna do elemento da matriz
	 * @return int
	 */
	public int getLinha() {
		return linha;
	}
}
