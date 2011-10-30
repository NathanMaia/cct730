package br.edu.unifei.cct730.trabalho08.gui.janelas;

import br.edu.unifei.cct730.trabalho08.gui.janelas.JanelaImagem;
import br.edu.unifei.cct730.trabalho08.gui.painel.PainelImagem;

/**
 * Classe responsavel por instanciar a
 * janela que contem a imagem digitalizada
 * 
 * @author fknappe
 *
 */
public class JanelaImagemDigitalizada extends JanelaImagem {

	// Declaracao das variaveis de instacia
	private static int openFrameCount = 0;
	private static final int xOffset = 0, yOffset = 0;
	
	/**
	 * Construtor
	 * 
	 * @param PainelImagem p
	 * 
	 */
	public JanelaImagemDigitalizada(PainelImagem p) {
		super(
			"[Imagem#" + (++openFrameCount) + "]", 
			true, 
			true, 
			true,
			false,
			p
		);
		setLocation(xOffset * openFrameCount, yOffset * openFrameCount);
	}
}
