package br.edu.unifei.cct730.trabalho04.gui.janelas;

import br.edu.unifei.cct730.trabalho04.gui.painel.PainelImagemBinaria;
import br.edu.unifei.cct730.trabalho04.utils.Descritor;
import br.edu.unifei.cct730.trabalho04.utils.Histograma;

public class JanelaImagemBinaria extends javax.swing.JInternalFrame {
	
	public JanelaImagemBinaria(Descritor d, Histograma h, short l) {
		initComponents(d, h, l);
	}
	
	public void initComponents(Descritor d, Histograma h, short l) {
		panelImagem = new PainelImagemBinaria(d, h, l);
		getContentPane().add(panelImagem);
	}
	
	private PainelImagemBinaria panelImagem;

	public PainelImagemBinaria getPanelImagem() {
		return panelImagem;
	}
}
