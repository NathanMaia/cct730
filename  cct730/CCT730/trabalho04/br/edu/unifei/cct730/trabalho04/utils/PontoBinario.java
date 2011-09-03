package br.edu.unifei.cct730.trabalho04.utils;

import java.awt.Color;
import java.awt.Graphics;

public class PontoBinario {
	
	// Declaração das variáveis de instância
	private int x, y = 0;
	private boolean estado = false;
	private Color cor = null;
	
	public PontoBinario(int x, int y) {
		this.x = x;
		this.y = y;
		this.estado = false;
		this.cor = Color.WHITE;
	}
	
	public void desenhar(Graphics desenho) {
		int novoY = 0;
		novoY = desenho.getClipBounds().height-y;
		desenho.setColor(cor);
		desenho.drawLine(x, novoY, x, novoY);
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

	public void trocaEstado() {
		if (estado == true) setEstado(false);
		else setEstado(true);
	}
}

