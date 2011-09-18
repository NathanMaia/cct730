package br.edu.unifei.cct730.trabalho05.model.filtro;

import br.edu.unifei.cct730.trabalho05.model.imagem.Imagem;
import br.edu.unifei.cct730.trabalho05.model.imagem.ImagemDigitalizada;
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
	public static final int PASSABAIXA_MEDIA = 0;
	public static final int PASSABAIXA_MEDIANA = 1;
	
	/**
	 * Construtor
	 * 
	 * @param ImagemFiltrada im
	 */
	public FiltroPassaBaixa(ImagemDigitalizada im) {
		super(im);
	}

	/**
	 * Metodo responsavel por escolher o tipo
	 * da filtragem da imagem digitalizada
	 * 
	 * @param int tipoFiltro
	 * 
	 * @return Imagem
	 */
	@Override
	public ImagemFiltrada filtrar(int tipoFiltro) throws IllegalArgumentException {

		// Escolha do tipo de filtragem
		switch(tipoFiltro) {
			case PASSABAIXA_MEDIA:
				return this.filtroMedia();
				
			case PASSABAIXA_MEDIANA:
				return this.filtroMediana();
				
			default:
				throw new IllegalArgumentException("Tipo de filtro inexistente!");
		}
	}
	
	/**
	 * Metodo responsavel por aplicar o filtro
	 * passa baixa pela media
	 * 
	 * @return Imagem
	 */
	private ImagemFiltrada filtroMedia() {
		
		// Declaracao de variaveis locais
		ImagemFiltrada imFiltrada = null;
		
		return imFiltrada;
	}

	/**
	 * Metodo responsavel por aplicar o filtro
	 * passa baixa pela mediana
	 * 
	 * @return Imagem
	 */
	private ImagemFiltrada filtroMediana() {
		
		// Declaracao de variaveis locais
		ImagemFiltrada imFiltrada = null;
		
		return imFiltrada;
	}
}
