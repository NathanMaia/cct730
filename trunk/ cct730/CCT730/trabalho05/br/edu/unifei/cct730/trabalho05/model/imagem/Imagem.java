package br.edu.unifei.cct730.trabalho05.model.imagem;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.edu.unifei.cct730.trabalho05.model.ponto.Ponto;

/**
 * 
 * @author fknappe
 *
 */
public abstract class Imagem {

	// Constantes
	public static final int IMAGEM_DIGITALIZADA = 0;
	public static final int IMAGEM_FILTRADA = 1;
	
	// Declaração das variaveis de instancia
	protected Map<Short, List<Ponto>> tabelaPontos = null;
	protected int numeroLinhas, numeroColunas = 0;

	/**
	 * Construtor 
	 * 
	 * @param int numLinhas
	 * @param int numColunas
	 */
	public Imagem(int numLinhas, int numColunas) {
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
	public Imagem constroiImagem(Short[][] tonsDeCinza) {
		for(int i = 0; i < this.numeroLinhas; i++) {
			for(int j = 0; j < this.numeroColunas; j++) {
				this.criarImagem(i, j, tonsDeCinza[i][j]);
			}
		}
		
		return this;
	}
	
	protected void criarImagem(int i, int j, Short nivelCinza) {
		// Verifica se o tom de cinza ainda não existe
		if (this.tabelaPontos.containsKey(nivelCinza) == false) {
			this.tabelaPontos.put(nivelCinza, new ArrayList<Ponto>());
		}
		// Insere o ponto na lista
		this.tabelaPontos.get(nivelCinza).add(
				new Ponto(
						i, j, 
						new Color(
								nivelCinza, 
								nivelCinza, 
								nivelCinza
						)
				)
		);
	}
	
	/**
	 * Metodo responsavel por retornar uma matriz
	 * dos tons de cinza presentes na imagem
	 * 
	 * @return Short[][]
	 */
	public Short[][] getTonsDeCinzaImagem() {
		// Declaracao de variaveis locais
		Short[][] tonsDeCinza = new Short[this.getNumeroLinhas()][this.getNumeroColunas()];
		
		for (Map.Entry<Short, List<Ponto>> entrada: this.getTabelaPontos().entrySet()) {
			for (Ponto ponto: entrada.getValue()) {
				tonsDeCinza[ponto.getX()][ponto.getY()] = entrada.getKey();
			}
		}
		return tonsDeCinza;
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
