package br.edu.unifei.cct730.trabalho05.model.filtro;

import br.edu.unifei.cct730.trabalho05.model.imagem.Imagem;
import br.edu.unifei.cct730.trabalho05.model.imagem.ImagemDigitalizada;
import br.edu.unifei.cct730.trabalho05.model.imagem.ImagemFiltrada;

/**
 * Classe responsavel por implementar
 * os comportamentos comuns as classes
 * que aplicam os filtros sobre as imagens
 * 
 * @author fknappe
 *
 */
public abstract class Filtro {

	// Constantes
	public static final int FILTRO_RUIDO = 1;
	public static final int FILTRO_PASSABAIXA = 2;
	
	// Declaracao das variaveis de instancia
	protected ImagemDigitalizada imagem;
	
	/**
	 * Construtor 
	 * 
	 * @param Imagem im
	 */
	public Filtro(ImagemDigitalizada im) {
		this.imagem = im;
	}
	
	/**
	 * Metodo responsavel por aplicar
	 * o filtro sobre uma imagem
	 * 
	 * @param String tipoFiltro
	 * 
	 * @return Imagem
	 */
	public abstract ImagemFiltrada filtrar(int tipoFiltro);

	// Metodos getters e setters
	public Imagem getImagem() {
		return imagem;
	}

	public void setImagem(ImagemDigitalizada imagem) {
		this.imagem = imagem;
	}
}
