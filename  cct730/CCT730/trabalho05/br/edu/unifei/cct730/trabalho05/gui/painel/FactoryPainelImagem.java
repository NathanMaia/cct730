package br.edu.unifei.cct730.trabalho05.gui.painel;

import br.edu.unifei.cct730.trabalho05.model.imagem.Imagem;
import br.edu.unifei.cct730.trabalho05.model.imagem.ImagemDigitalizada;
import br.edu.unifei.cct730.trabalho05.model.imagem.ImagemFiltrada;

/**
 * Classe responsavel por implementar o 
 * pattern Factory para instanciacao
 * de objetos do tipo PainelImagem
 * 
 * @author fknappe
 *
 */
public class FactoryPainelImagem {

	/**
	 * Metodo responsavel por 
	 * instanciar um objeto do tipo
	 * PainelImagem
	 * 
	 * @param int tipoPainel
	 * @param Imagem im
	 * 
	 * @return 
	 */
	public static PainelImagem create(int tipoPainel, Imagem im) throws IllegalArgumentException {

		switch(tipoPainel) {
			case PainelImagem.IMAGEM_DIGITALIZADA: 
				return new PainelImagemDigitalizada((ImagemDigitalizada)im);

			case PainelImagem.IMAGEM_FILTRADA:
				return new PainelImagemFiltrada((ImagemFiltrada)im);

			default:
				throw new IllegalArgumentException("Tipo de painel invalido!");
		}
	}
}
