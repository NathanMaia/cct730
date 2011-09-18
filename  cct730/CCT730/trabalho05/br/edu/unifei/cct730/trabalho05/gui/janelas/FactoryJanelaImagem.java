package br.edu.unifei.cct730.trabalho05.gui.janelas;

import br.edu.unifei.cct730.trabalho05.gui.painel.PainelImagem;

/**
 * Classe que implementa o pattern Factory
 * para inicializacao dos objetos do tipo
 * JanelaImagem
 * 
 * @author fknappe
 *
 */
public class FactoryJanelaImagem {
	
	/**
	 * Metodo resposavel pela inicializacao
	 * de uma JanelaImagem
	 * 
	 * @param int tipoJanela
	 * @param PainelImagem p
	 * 
	 * @return JanelaImagem
	 */
	public static JanelaImagem create(int tipoJanela, PainelImagem p) throws IllegalArgumentException {
		
		switch(tipoJanela) {
			case JanelaImagem.IMAGEM_DIGITALIZADA:
				return new JanelaImagemDigitalizada(p);
				
			case JanelaImagem.IMAGEM_FILTRADA:
				return new JanelaImagemFiltrada(p);
				
			default:
				throw new IllegalArgumentException("Tipo da janela invalido!");
		}
	}
}
