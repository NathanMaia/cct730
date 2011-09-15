package br.edu.unifei.cct730.trabalho05.utils.imagem;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.edu.unifei.cct730.trabalho05.utils.ponto.Ponto;

/**
 * Classe que representa a imagem digitalizada
 * 
 * @author fknappe
 *
 */
public class ImagemDigitalizada {

	// Declaração das variaveis de instancia
	private Map<Short, List<Ponto>> tabelaPontos = null;
	private int numeroLinhas, numeroColunas = 0;

	/**
	 * Construtor
	 * 
	 * @param int numLinhas
	 * @param int numColunas
	 */
	public ImagemDigitalizada(int numLinhas, int numColunas) {
		this.numeroLinhas = numLinhas;
		this.numeroColunas = numColunas;
		tabelaPontos = new HashMap<Short, List<Ponto>>();
	}
	
	/**
	 * Metodo responsavel por adicionar um novo tom de cinza a 
	 * imagem
	 * 
	 * @param int i
	 * @param int j
	 * @param short nivelCinza
	 * 
	 * @return void
	 */
	public void criarImagem(int i, int j, short nivelCinza) {
		
		// Verifica se o tom de cinza ainda não existe
		if (this.tabelaPontos.containsKey(nivelCinza) == false) {
			this.tabelaPontos.put(nivelCinza, new ArrayList<Ponto>());
		}
		// Insere o ponto na lista
		this.tabelaPontos.get(nivelCinza).add(
				new Ponto(
						i, j, 
						new Color(nivelCinza, nivelCinza, nivelCinza)
				)
		);
	}
	
	// Metodos getters e setters
	public Map<Short, List<Ponto>> getTabelaPontos() {
		return tabelaPontos;
	}

	public int getNumeroLinhas() {
		return this.numeroLinhas;
	}

	public int getNumeroColunas() {
		return this.numeroColunas;
	}
}
