package br.edu.unifei.cct730.trabalho05.model.filtro;

import br.edu.unifei.cct730.trabalho05.model.imagem.ImagemFiltrada;

/**
 * Classe responsavel pelas acoes dos filtros
 * do tipo passa baixa
 * 
 * @author fknappe
 *
 */
public class FiltroPassaBaixa extends Filtro {

	// Constantes
	private static final int PASSABAIXA_MEDIA = 0;
	private static final int PASSABAIXA_MEDIANA = 1;
	
	/**
	 * Construtor
	 * 
	 * @param ImagemFiltrada im
	 */
	public FiltroPassaBaixa(ImagemFiltrada im) {
		super(im);
	}

	/**
	 * Metodo responsavel por escolher o tipo
	 * da filtragem da imagem digitalizada
	 * 
	 * @param int tipoFiltro
	 * 
	 * @return ImagemFiltrada
	 */
	@Override
	public ImagemFiltrada filtrar(int tipoFiltro) {

		// Escolha do tipo de filtragem
		switch(tipoFiltro) {
			case PASSABAIXA_MEDIA:
				return this.filtroMedia();
				
			case PASSABAIXA_MEDIANA:
				return this.filtroMediana();
				
			default:
				throw new NullPointerException("Tipo de filtro inexistente!");
		}
	}
	
	/**
	 * Metodo responsavel por aplicar o filtro
	 * passa baixa pela media
	 * 
	 * @return ImagemFiltrada
	 */
	private ImagemFiltrada filtroMedia() {
		return imagem;
	}

	/**
	 * Metodo responsavel por aplicar o filtro
	 * passa baixa pela mediana
	 * 
	 * @return ImagemFiltrada
	 */
	private ImagemFiltrada filtroMediana() {
		return imagem;
	}
}
