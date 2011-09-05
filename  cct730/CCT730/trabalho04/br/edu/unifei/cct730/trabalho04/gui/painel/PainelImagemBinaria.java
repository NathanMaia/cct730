package br.edu.unifei.cct730.trabalho04.gui.painel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;

import br.edu.unifei.cct730.trabalho04.utils.Descritor;
import br.edu.unifei.cct730.trabalho04.utils.Histograma;
import br.edu.unifei.cct730.trabalho04.utils.PontoBinario;

/**
 * Classe responsavel por criar a imagem binarizada
 * @author fknappe
 *
 */
public class PainelImagemBinaria extends javax.swing.JPanel {
	
	// Declaração das variáveis de instância
	private PontoBinario[][] pontos = null;
	private Descritor descritor = null;
	private int numeroLinhas, numeroColunas = 0;
	private short limiar = 0;
	
	/**
	 * Construtor 
	 * 
	 * @param Descritor d
	 * @param Histograma h
	 * @param short limiar
	 */
	public PainelImagemBinaria(
			Descritor d,
			short limiar
	) {
		this(d.getNumeroLinhas(), d.getNumeroColunas(), limiar);
		this.descritor = d;
		
		// Realiza a construção da imagem
		constroiImagem(descritor.getTabelaPontos());
	}
	/**
	 * Construtor 
	 * 
	 * @param int numeroLinhas
	 * @param int numeroColunas
	 */
	public PainelImagemBinaria(
			int numeroLinhas, 
			int numeroColunas, 
			short l
	) {
		super();
		
		this.numeroLinhas = numeroLinhas;
		this.numeroColunas = numeroColunas;
		this.limiar = l;
		
		// Inicializa o vetor de pontos do painel
		this.constroiVetorPontos();
		
		this.setPreferredSize(
				new Dimension(
						this.numeroLinhas, 
						this.numeroColunas
				)
		);
	}
	
	/**
	 * Metodo responsavel por inicializar o vetor de pontos do painel
	 * 
	 * @return void
	 */
	private void constroiVetorPontos() {
		this.pontos = new PontoBinario[numeroLinhas][numeroColunas];
		for (int i = 0; i < this.numeroLinhas; i++) {
			for (int j = 0; j < this.numeroColunas; j++) {
				pontos[i][j] = new PontoBinario(i, j);
			}
		}
	}
	
	/**
	 * Metodo responsavel pela construcao da imagem binaria
	 * 
	 * @param Map tabelaPontos
	 * 
	 * @return void
	 */
	private void constroiImagem(Map<Short, List<PontoBinario>> tabelaPontos) {
		short nivelCinza;
		
		for (Map.Entry<Short, List<PontoBinario>> entrada: tabelaPontos.entrySet()) {
			nivelCinza = entrada.getKey();
			for (PontoBinario ponto: entrada.getValue()) {
				setPosicao(ponto.getX(), ponto.getY(), binarizar(nivelCinza));
			}
		}
	}

	/**
	 * Metodo responsavel por alterar o estado do ponto binario (0 ou 1)
	 * 
	 * @param int posLinha
	 * @param int posColuna
	 * @param boolean estado
	 * 
	 * @return void
	 */
	public void setPosicao(int posLinha, int posColuna, boolean estado) {
		pontos[posLinha][posColuna].setEstado(estado);
	}
	
	/**
	 * Metodo responsavel por trocar o estado de ponto binario
	 * especificado
	 * 
	 * @param int posLinha
	 * @param int posColuna
	 * 
	 * @return void
	 */
	public void trocaEstadoPosicao(int posLinha, int posColuna) {
		pontos[posLinha][posColuna].trocaEstado();
	}

	/**
	 * Metodo responsavel por colorir o ponto especificado
	 * 
	 * @param int posLinha
	 * @param int posColuna
	 * @param Color cor
	 * 
	 * @return void
	 */
	public void pintarPosicao(int posLinha, int posColuna, Color cor) {
		pontos[posLinha][posColuna].setCor(cor);
		repaint();
	}
	
	/**
	 * Metodo responsavel por determinar quais areas da imagem
	 * serao binarizadas
	 * 
	 * @param short readShort
	 * 
	 * @return boolean
	 */
	private boolean binarizar(short readShort) {
		if (readShort < this.limiar) return true;
		return false;
	}
	
	/**
	 * Metodo responsavel por plotar os pontos da imagem
	 * 
	 * @param Graphics g
	 * 
	 * @return void
	 */
	public void paint(Graphics g) {
		super.paint(g);
		for (int i = 0; i < this.numeroLinhas; i++) {
			for (int j = 0; j < this.numeroColunas; j++) {
				this.pontos[i][j].plotar(g);
			}
		}
	}

	// Metodos getters e setters
	public boolean getPosicao(int posLinha, int posColuna) {
		return pontos[posLinha][posColuna].getEstado();
	}
	
	public int getNumeroColunas() {
		return this.numeroColunas;
	}

	public int getNumeroLinhas() {
		return this.numeroLinhas;
	}
	
	public short getLimiar() {
		return limiar;
	}

	PontoBinario[][] getPontos() {
		return this.pontos;
	}
}
