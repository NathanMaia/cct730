package br.edu.unifei.cct730.trabalho05.model.filtro;

import br.edu.unifei.cct730.trabalho05.model.imagem.Imagem;
import br.edu.unifei.cct730.trabalho05.model.imagem.ImagemDigitalizada;
import br.edu.unifei.cct730.trabalho05.model.imagem.ImagemFiltrada;

/**
 * Classe que implementa o pattern Factory
 * para instanciacao de objetos do tipo
 * Filtro
 * 
 * @author fknappe
 *
 */
public class FactoryFiltro {
	
	/**
	 * Metodo responsavel por instanciar
	 * um objeto do tipo Filtro
	 * 
	 * @param int tipoFiltro
	 * @param ImagemFiltrada im
	 * 
	 * @return Filtro
	 */
	public static Filtro create(int tipoFiltro, ImagemDigitalizada im) throws IllegalArgumentException {
		
		switch(tipoFiltro) {
			case Filtro.FILTRO_RUIDO:
				return new FiltroRuido(im);
			
			case Filtro.FILTRO_PASSABAIXA:
				return new FiltroPassaBaixa(im);
				
			default:
				throw new IllegalArgumentException("Tipo de Filtro invalido");
		}
	}
}
