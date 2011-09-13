package br.edu.unifei.cct730.trabalho04.utils.ponto;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Classe responsavel por criar o ponto representativo
 * dos pixels da imagem
 * 
 * @author fknappe
 *
 */
public class PontoBinario extends Ponto {
	
	// Declaração das variáveis de instância
	private boolean estado = false;
	
	/**
	 * Construtor 
	 * @param int x
	 * @param int y
	 */
	public PontoBinario(int x, int y) {
		super(x, y, Color.WHITE);
		this.estado = false;
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
	public boolean getEstado() {
		return estado;
	}
	
	public void setEstado(boolean estado) {
		this.estado = estado;
        if (estado == true) cor = Color.BLACK;
		else cor = Color.WHITE;
	}
}

