package br.edu.unifei.cct730.trabalho05.model.filtro;

import java.io.IOException;

import br.edu.unifei.cct730.trabalho05.model.imagem.Imagem;
import br.edu.unifei.cct730.trabalho05.model.imagem.ImagemFiltrada;
import br.edu.unifei.cct730.trabalho05.utils.constantes.Constantes;

public class FiltroPassaBaixa extends Filtro {

	// Constantes
	private static final int PASSABAIXA_MEDIA = 0;
	private static final int PASSABAIXA_MEDIANA = 1;
	
	public FiltroPassaBaixa(ImagemFiltrada im) {
		super(im);
	}

	/**
	 * Metodo responsavel por escolher o tipo
	 * da filtragem da imagem digitalizada
	 * 
	 * @param String tipo
	 * 
	 * @return ImagemFiltrada
	 */
	@Override
	public ImagemFiltrada filtrar(int tipoFiltro) {

		Short[][] tonsDeCinza = this.imagem.getTonsDeCinzaImagem();

		// Escolha do tipo de filtragem
		switch(tipoFiltro) {
			case PASSABAIXA_MEDIA:
				return this.filtroMedia(tonsDeCinza);
				
			case PASSABAIXA_MEDIANA:
				return this.filtroMediana(tonsDeCinza);
				
			default:
				throw new NullPointerException("Tipo de filtro inexistente!");
		}
	}
	
	/**
	 * Metodo responsavel por aplicar o filtro
	 * passa baixa pela media
	 * 
	 * @param Short[][] tonsDeCinza
	 * 
	 * @return ImagemFiltrada
	 */
	private ImagemFiltrada filtroMedia(Short[][] tonsDeCinza) {
		return imagem;
	}

	/**
	 * Metodo responsavel por aplicar o filtro
	 * passa baixa pela mediana
	 * 
	 * @param Short[][] tonsDeCinza
	 * 
	 * @return ImagemFiltrada
	 */
	private ImagemFiltrada filtroMediana(Short[][] tonsDeCinza) {
		return imagem;
	}
}
