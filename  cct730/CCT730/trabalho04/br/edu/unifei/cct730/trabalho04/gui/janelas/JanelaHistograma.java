package br.edu.unifei.cct730.trabalho04.gui.janelas;

import br.edu.unifei.cct730.trabalho03.gui.componentes.JButtonSair;
import br.edu.unifei.cct730.trabalho04.utils.Histograma;

public class JanelaHistograma extends javax.swing.JInternalFrame {
	
	private Histograma histograma = null;
	
	public JanelaHistograma(Histograma h) {
		
	}
	
	public void initComponents() {
		
		java.awt.GridBagConstraints gridBagConstraints;
		
		panelBotao = new javax.swing.JPanel();
		btnFinalizar = new JButtonSair("Finalizar", this);
		
		this.setLayout(new java.awt.GridBagLayout());
	}
	
	private javax.swing.JPanel panelBotao;
	private JButtonSair btnFinalizar;

	public JButtonSair getBtnFinalizar() {
		return btnFinalizar;
	}
}
