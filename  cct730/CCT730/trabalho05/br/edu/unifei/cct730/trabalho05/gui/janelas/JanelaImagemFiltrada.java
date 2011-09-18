package br.edu.unifei.cct730.trabalho05.gui.janelas;

import javax.swing.JInternalFrame;

import br.edu.unifei.cct730.trabalho05.gui.painel.PainelImagem;

public class JanelaImagemFiltrada extends JanelaImagem {
	
	// Declaracao das variaveis de instacia
	private static int openFrameCount = 0;
	private static final int xOffset = 175, yOffset = 0;
	
	/**
	 * Construtor 
	 * 
	 * @param PainelImagem p
	 */
	public JanelaImagemFiltrada(PainelImagem p) {
		super("[Filtro#" + (++openFrameCount) + "]", 
				true, 
				true, 
				true,
				false,
				p
		);
		setLocation(xOffset * openFrameCount, yOffset * openFrameCount);
	}
}
