package br.edu.unifei.cct730.trabalho08.utils;

import br.edu.unifei.cct730.trabalho08.gui.painel.Celula;

/**
 * Classe responsavel pela matriz de celulas da imagem sintetica
 * @author fknappe
 */
public class MatrizCelula {
	
	//Declaração de variáveis globais
	private Celula matrizCelulas[][] = null;
	private int numLinhas, numColunas = 0;
	
	/**
	 * Construtor
	 * @param int linha  numero de linhas da matriz
	 * @param int coluna numero de colunas da matriz
	 */
	public MatrizCelula(int linha, int coluna) {
		this.matrizCelulas = new Celula[linha][coluna];
		this.numColunas = matrizCelulas[0].length;
		this.numLinhas = matrizCelulas.length;
	}
	
	/**
	 * Metodo responsavel por adicionar uma nova celula a matriz
	 * @param int valor
	 * @param int linha
	 * @param int coluna
	 * 
	 * @return void
	 */
	public void add(int valor, int linha, int coluna){
		matrizCelulas[linha][coluna] = new Celula(linha, coluna);
		matrizCelulas[linha][coluna].setValor(valor);
	}
	
	/**
	 * Metodo responsavel por adicionar uma nova celula a matriz
	 * @param linha  posicao da linha
	 * @param coluna posicao da coluna
	 * 
	 * @return void
	 */
	public void add(int linha, int coluna){
		matrizCelulas[linha][coluna] = new Celula(linha, coluna);
		matrizCelulas[linha][coluna].setValor(0);
	}
	
	/**
	 * Método responsável por retornar uma matriz contendo os valores
	 * de todas as células da matriz
	 * 
	 * @return int[][]
	 */
	public int[][] getMatrizValores(){
		int matrizValores[][] = new int[numLinhas][numColunas];
		
		for (int i = 0; i < numLinhas; i++) {
			for (int j = 0; j < numColunas; j++) {
				matrizValores[i][j] = matrizCelulas[i][j].getValor();
			}
		}		
		return matrizValores;		
	}
	
	/**
	 * Método responsável por retornar uma string contendo todos os valores
	 * das células da matriz
	 * 
	 * @return String
	 */
	public String toString(){
		String string = "";
		for (int i = 0; i < numLinhas; i++) {
			for (int j = 0; j < numColunas; j++) {
				if(matrizCelulas[i][j].getValor() <10){
					string += ("" + matrizCelulas[i][j].getValor() + "  ");
				}else if ( matrizCelulas[i][j].getValor() >=10 || matrizCelulas[i][j].getValor() < 0 ){
					string += ("" + matrizCelulas[i][j].getValor() + " ");
				}			
			}
			string += "\n";
		}		
		return string;
	}
	
	/**
	 * Método responsavel por inicializar uma nova matriz
	 * 
	 * @param void
	 * @return void
	 */
	public void zerarMatriz() {
		for (int i = 0; i < numLinhas; i++) {
			for (int j = 0; j < numColunas; j++) {
				add(i,j);
			}
		}
	}
	
	//Métodos getters e setters
	
	public void setMatrizCelulas(Celula matriz[][]){
		this.matrizCelulas = matriz;
		this.numColunas = matriz[0].length;
		this.numLinhas = matriz.length;	
	}
	
	public Celula[][] getMatrizCelulas() {
		return matrizCelulas;
	}
	
	public int getNumColunas() {
		return numColunas;
	}

	public int getNumLinhas() {
		return numLinhas;
	}
	
	public int getValorCelula(int i, int j){
		return matrizCelulas[i][j].getValor();
	}

	public void setValorCelula(int valor, int i, int j) {
		matrizCelulas[i][j].setValor(valor);		
	}	
	
	public Celula getCelula(int i, int j){
		return matrizCelulas[i][j];		
	}		
}
