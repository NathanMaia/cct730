package br.edu.unifei.cct730.trabalho04.utils.imagem;

import java.util.ArrayList;

import br.edu.unifei.cct730.trabalho04.utils.ponto.Ponto;
import br.edu.unifei.cct730.trabalho04.utils.ponto.PontoBinario;
import br.edu.unifei.cct730.trabalho04.utils.imagem.ImagemDigitalizada;

/**
 * Classe que representa a imagem binarizada
 * 
 * @author fknappe
 *
 */
public class ImagemBinarizada extends ImagemDigitalizada {

	/**
	 * Construtor
	 * 
	 * @param int numLinhas
	 * @param int numColunas
	 */
	public ImagemBinarizada(int numLinhas, int numColunas) {
		super(numLinhas, numColunas);
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
	public void criarImagemDigitalizada(int i, int j, short nivelCinza) {
		
		// Verifica se o tom de cinza ainda n‹o existe
		if (this.getTabelaPontos().containsKey(nivelCinza) == false) {
			this.getTabelaPontos().put(nivelCinza, new ArrayList<Ponto>());
		}
		
		// Insere o ponto na lista
		this.getTabelaPontos().get(nivelCinza).add(new PontoBinario(i, j));
	}
}
