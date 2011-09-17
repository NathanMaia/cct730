package br.edu.unifei.cct730.trabalho05.gui.painel;

import br.edu.unifei.cct730.trabalho05.model.imagem.Imagem;
import br.edu.unifei.cct730.trabalho05.model.imagem.ImagemDigitalizada;
import br.edu.unifei.cct730.trabalho05.model.imagem.ImagemFiltrada;

public class PainelImagemFactory {

	public PainelImagem create(int tipoPainel, Imagem im) {

		switch(tipoPainel) {
			case PainelImagem.IMAGEM_DIGITALIZADA: 
				return new PainelImagemDigitalizada((ImagemDigitalizada)im);

			case PainelImagem.IMAGEM_FILTRADA:
				return new PainelImagemFiltrada((ImagemFiltrada)im);

			default:
				throw new NullPointerException("Tipo de painel invalido!");
		}
	}
}
