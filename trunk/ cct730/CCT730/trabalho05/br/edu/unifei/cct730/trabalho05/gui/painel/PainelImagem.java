package br.edu.unifei.cct730.trabalho05.gui.painel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;
import java.util.Map;

import br.edu.unifei.cct730.trabalho05.model.imagem.Imagem;
import br.edu.unifei.cct730.trabalho05.model.imagem.ImagemDigitalizada;
import br.edu.unifei.cct730.trabalho05.model.ponto.Ponto;
import br.edu.unifei.cct730.trabalho05.eventos.PainelImagemListener;

/**
 * Classe responsavel por implementar um painel
 * que contem uma imagem
 * 
 * @author fknappe
 *
 */
public abstract class PainelImagem extends javax.swing.JPanel {

	// Constantes
	public static final int IMAGEM_DIGITALIZADA = 1;
	public static final int IMAGEM_FILTRADA = 2;
	
	// Declaracao das variaveis de instancia
	protected Ponto[][] pontos = null;
	protected Imagem imagem = null;
	protected int numeroLinhas, numeroColunas = 0;
	
	/**
	 * Construtor
	 * 
	 * @param Imagem im
	 */
	public PainelImagem(Imagem im) {
		this(im.getNumeroLinhas(), im.getNumeroColunas());
		this.imagem = im;
		this.constroiImagem(imagem.getTabelaPontos());	
	}
	
	/**
	 * Construtor 
	 * 
	 * @param int numLinhas
	 * @param int numColunas
	 */
	public PainelImagem(int numLinhas, int numColunas) {
		this.numeroLinhas = numLinhas;
		this.numeroColunas = numColunas;
		
		// Inicializando o vetor de pontos
		this.constroiVetorPontos();
		
		// Definindo as dimensoes do painel
		this.setPreferredSize(
				new Dimension(
						this.getNumeroLinhas(), 
						this.getNumeroColunas()
				)
		);
		
		/*
		 * Adicionando o listener para capturar o posicioanamento do painel
		 */
		this.addMouseListener(new PainelImagemListener(this));
	}
	
	/**
	 * Metodo responsavel por construir a imagem
	 * 
	 * @param Map<Short, List<Ponto>> tabelaPontos
	 * 
	 * @return void
	 */
	public abstract void constroiImagem(Map<Short, List<Ponto>> tabelaPontos);
	
	/**
	 * Metodo responsavel por inicializar o vetor de pontos do painel
	 * 
	 * @return void
	 */
	protected void constroiVetorPontos() {
		this.setPontos(new Ponto[this.getNumeroLinhas()][this.getNumeroColunas()]);
		for (int i = 0; i < this.getNumeroLinhas(); i++) {
			for (int j = 0; j < this.getNumeroColunas(); j++) {
				this.getPontos()[i][j] = new Ponto(i, j);
			}
		}
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
		for (int i = 0; i < this.getNumeroLinhas(); i++) {
			for (int j = 0; j < this.getNumeroColunas(); j++) {
				this.getPontos()[i][j].plota(g);
			}
		}
	}
	
	/**
	 * Metodo responsavel por alterar o estado do ponto
	 * 
	 * @param int posLinha
	 * @param int posColuna
	 * @param Color c
	 * 
	 * @return void
	 */
	public void setPosicao(int posLinha, int posColuna, Color c) {
		this.getPontos()[posLinha][posColuna].setCor(c);
	}

	// Metodos getters e setters
	public Ponto[][] getPontos() {
		return pontos;
	}

	public void setPontos(Ponto[][] pontos) {
		this.pontos = pontos;
	}
	
	public Imagem getImagem() {
		return imagem;
	}

	public int getNumeroLinhas() {
		return numeroLinhas;
	}

	public int getNumeroColunas() {
		return numeroColunas;
	}
}
