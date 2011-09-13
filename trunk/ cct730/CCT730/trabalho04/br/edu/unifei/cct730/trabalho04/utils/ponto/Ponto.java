package br.edu.unifei.cct730.trabalho04.utils.ponto;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Classe representativa de um ponto
 * 
 * @author fknappe
 *
 */
public class Ponto {

	// Declaracao das variaveis de instancia
	private int x, y = 0;
	protected Color cor = Color.BLACK;

	/**
	 * Construtor
	 * 
	 * @param int x
	 * @param int y
	 * @param Color cor
	 */
	public Ponto(int x, int y, Color cor) {
		this.x = x;
		this.y = y;
		this.cor = cor;
	}

	/**
	 * Construtor 
	 * 
	 * @param int x
	 * @param int y
	 */
	public Ponto(double x, double y) {
		this.x = (int) Math.round(x);
		this.y = (int) Math.round(y);
	}
	
	/**
	 * Metodo responsavel por plotar um
	 * ponto na tela
	 * 
	 * @param Graphics g
	 */
	public void plota(Graphics g) {
		int novoY = 0;
		novoY = g.getClipBounds().height - this.getY();
		g.setColor(cor);
		g.drawLine(this.getX(), novoY, this.getX(), novoY);	
	}

	// Metodos getters e setters
	public int getX() {
		return this.x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return this.y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Color getCor() {
		return cor;
	}

	public void setCor(Color cor) {
		this.cor = cor;
	}
}
