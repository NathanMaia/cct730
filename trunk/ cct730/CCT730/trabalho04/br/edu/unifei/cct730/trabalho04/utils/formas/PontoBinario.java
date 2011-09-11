package br.edu.unifei.cct730.trabalho04.utils.formas;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Classe responsavel por criar o ponto representativo
 * dos pixels da imagem
 * 
 * @author fknappe
 *
 */
public class PontoBinario {
	
	// Declaração das variáveis de instância
	private int x, y = 0;
	private boolean estado = false;
	private Color cor = null;
	
	/**
	 * Construtor 
	 * @param int x
	 * @param int y
	 */
	public PontoBinario(int x, int y) {
		this.x = x;
		this.y = y;
		this.estado = false;
		this.cor = Color.WHITE;
	}
	
	/**
	 * Método responsável por plotar um pixel
	 * na tela
	 * 
	 * @param Graphics g
	 * @return void
	 */
	public void plotar(Graphics g) {
		int novoY = 0;
		novoY = g.getClipBounds().height-y;
		g.setColor(cor);
		g.drawLine(x, novoY, x, novoY);
	}
	
	/**
	 * Metodo responsavel por alterar o estado 
	 * do ponto (0 ou 1)
	 * 
	 * @return void
	 */
	public void trocaEstado() {
		if (estado == true) setEstado(false);
		else setEstado(true);
	}

	// Metodos getters e setters
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public boolean getEstado() {
		return estado;
	}
	
	public void setEstado(boolean estado) {
		this.estado = estado;
        if (estado == true) cor = Color.BLACK;
		else cor = Color.WHITE;
	}
	
	public void setCor(Color cor) {
		if (estado == true) this.cor = cor;
	}
}

