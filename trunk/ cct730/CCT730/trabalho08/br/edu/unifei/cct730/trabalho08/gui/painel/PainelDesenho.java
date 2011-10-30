package br.edu.unifei.cct730.trabalho08.gui.painel;

import java.awt.GridLayout;

import br.edu.unifei.cct730.trabalho08.eventos.PainelDesenhoListener;
import br.edu.unifei.cct730.trabalho08.gui.painel.Celula;
import br.edu.unifei.cct730.trabalho08.utils.MatrizCelula;

/**
* Classe responsavel por criar o painel da imagem sintética
* @author fknappe
*
*/
public class PainelDesenho extends javax.swing.JPanel {
	
	//Declaração das variaveis de instancia
	private MatrizCelula matriz = null;
	private int altura, largura = 0;

	/**
	 * Construtor
	 * 
	 * @param int altura  
	 * @param int largura
	 */
	public PainelDesenho(int altura, int largura) {
		matriz = new MatrizCelula(altura, largura);
		this.altura = altura;
		this.largura = largura;
		setLayout(new GridLayout(this.altura, this.largura, 1, 1));
		for (int i = 0; i < this.altura; i++) {
			for (int j = 0; j < this.largura; j++) {
				matriz.add(i, j);
				matriz.getCelula(i, j).addMouseListener(new PainelDesenhoListener());
				this.add(matriz.getCelula(i, j));
			}
		}
	}

	/**
	 * Construtor 
	 * 
	 * @param int altura
	 * @param int largura
	 * @param int matrizEntrada
	 */
	public PainelDesenho(int altura, int largura, int matrizEntrada[][]) {
		matriz = new MatrizCelula(altura, largura);
		this.altura = altura;
		this.largura = largura;
		setLayout(new GridLayout(altura, largura, 1, 1));
		for (int i = 0; i < altura; i++) {
			for (int j = 0; j < largura; j++) {
				matriz.add(matrizEntrada[i][j], i, j);	
				matriz.getCelula(i, j).addMouseListener(new PainelDesenhoListener());
				this.add(matriz.getCelula(i, j));
			}
		}
	}
	
	/**
	 * Construtor 
	 * 
	 * @param MatrizCelula m
	 */
	public PainelDesenho(MatrizCelula m) {
		this.matriz = m;
		this.altura = matriz.getNumLinhas();
		this.largura = matriz.getNumColunas();
		setLayout(new GridLayout(this.altura, this.largura, 1, 1));
		for (int i = 0; i < altura; i++) {
			for (int j = 0; j < largura; j++) {
				this.add(matriz.getCelula(i, j));
			}
		}
	}
	
	//Metodos getters e setters 
	public Celula[][] getPixels() {
		return matriz.getMatrizCelulas();
	}

	public Celula getCelula(int linha, int coluna) {
		return matriz.getCelula(linha, coluna);
	}

	public int getAltura() {
		return altura;
	}

	public int getLargura() {
		return largura;
	}

	public MatrizCelula getMatriz() {
		return matriz;
	}
}
