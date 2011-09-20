package br.edu.unifei.cct730.trabalho05.gui.janelas;

import javax.swing.JInternalFrame;

import br.edu.unifei.cct730.trabalho05.gui.painel.PainelImagem;

/**
 * Classe responsavel por instanciar uma janela
 * que contem a imagem filtrada
 * 
 * @author fknappe
 *
 */
public class JanelaImagemFiltrada extends JanelaImagem {
	
	// Declaracao das variaveis de instacia
	private static final int xOffset = 175, yOffset = 0;
	private static int openFrameCount = 0;
	private int dinamicOffsetCount = 0;
	
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
	}

	// Metodos getters e setters
	public void setLocation(int dinamicOffsetCount) {
		setLocation(xOffset * dinamicOffsetCount, yOffset * dinamicOffsetCount);
	}
}
