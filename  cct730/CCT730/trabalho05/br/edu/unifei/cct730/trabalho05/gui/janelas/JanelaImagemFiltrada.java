package br.edu.unifei.cct730.trabalho05.gui.janelas;

import javax.swing.JInternalFrame;

import br.edu.unifei.cct730.trabalho05.gui.painel.PainelImagem;

public class JanelaImagemFiltrada extends JanelaImagem {
	
	/**
	 * Construtor 
	 * 
	 * @param PainelImagem p
	 */
	public JanelaImagemFiltrada(PainelImagem p) {
		super("[Ruido#" + (++openFrameCount) + "]", 
				true, 
				true, 
				true,
				false,
				p
		);
		
	}
}
