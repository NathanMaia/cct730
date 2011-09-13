package br.edu.unifei.cct730.trabalho04.utils.formas;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import br.edu.unifei.cct730.trabalho04.utils.ponto.Ponto;
import br.edu.unifei.cct730.trabalho04.utils.ponto.Pontos;

public class Reta {

	// Declaracao das variaveis de instancia
	private Ponto pInicio, pFinal = null;
	private Pontos pontos = null;
	private Color cor = Color.BLACK;

	/**
	 * Construtor 
	 * 
	 * @param Ponto p1
	 * @param Ponto p2
	 * @param Color c
	 */
	public Reta(Ponto p1, Ponto p2, Color c) {
		this.pInicio = p1;
		this.pFinal = p2;
		this.cor = c;
		pontos = new Pontos();

		if (pInicio.getX() == pFinal.getX()) {
			retaVertical();
		} else if (pInicio.getY() == pFinal.getY()) {
			retaHorizontal();
		}
	}

	/**
	 * Metodo responsavel por construir
	 * uma reta horizontal
	 * 
	 * @return void
	 */
	public void retaHorizontal() {
		// Declaracao das variaveis locais
		int x1, x2 = 0;
		int y = 0;
		
		y = pInicio.getY();
		if (pInicio.getX() < pFinal.getX()) {
			x1 = pInicio.getX();
			x2 = pFinal.getX();
		} else {
			x2 = pInicio.getX();
			x1 = pFinal.getX();
		}
		
		for (int i = x1; i < x2; i++) {
			pontos.add(new Ponto(i, y, this.cor));
		}
	}

	/**
	 * Metodo responsavel por construir uma reta
	 * vertical
	 * 
	 * @return void
	 */
	public void retaVertical() {
		// Declaracao das variaveis locais
		int y1, y2 = 0;
		int x = 0;
		x = pInicio.getX();
		if (pInicio.getY() < pFinal.getY()) {
			y1 = pInicio.getY();
			y2 = pFinal.getY();
		} else {
			y2 = pInicio.getY();
			y1 = pFinal.getY();
		}
		for (int i = y1; i < y2; i++) {
			pontos.add(new Ponto(x, i, this.cor));
		}
	}

	// Metodos getters e setters
	public Ponto getPinicio() {
		return this.pInicio;
	}

	public Ponto getPFinal() {
		return this.pFinal;
	}

	public Pontos getPontos() {
		return pontos;
	}

	public Color getCor() {
		return cor;
	}
}
