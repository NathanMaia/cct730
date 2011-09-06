package br.edu.unifei.cct730.trabalho03.gui.janelas;

import javax.swing.JInternalFrame;

import br.edu.unifei.cct730.trabalho03.gui.componentes.JButtonSair;
import br.edu.unifei.cct730.trabalho03.gui.painel.PanelDesenho;

/**
 * Classe responsavel pela representacao da imagem sintetica
 * @author fknappe
 *
 */
public class JanelaImagemSintetica extends javax.swing.JInternalFrame {
	
	public JanelaImagemSintetica() {
		super("Editor de Imagem Sintetica");
		this.initComponents();
	}
	
	/**
	 * Metodo responsavel pela inicializacao e posicionamento dos 
	 * componentes da GUI
	 * 
	 * @param int altura
	 * @param int largura
	 * 
	 * @return void
	 */
	private void initComponents() {
		java.awt.GridBagConstraints gridBagConstraints;
		
		// Instanciando os componentes da GUI
		panelBotao = new javax.swing.JPanel();
		btnSair = new JButtonSair("Finalizar", this);
		
		// Definindo o layout dos componentes
		getContentPane().setLayout(new java.awt.GridBagLayout());
		
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.CENTER;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 100.0;
		gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
		getContentPane().add(panelBotao, gridBagConstraints);
		
		panelBotao.add(btnSair);
		
		// Par�metros da interface
		setMaximizable(true);
		setResizable(true);
		setSize(800, 600);
	}
	
	// Declaracao dos componentes da GUI
	private JButtonSair btnSair;
	private PanelDesenho panelDesenho;
	private javax.swing.JPanel panelBotao;
	
	//Metodos getters e setters dos componentes da GUI
	public JButtonSair getBtnSair() {
		return btnSair;
	}

	public PanelDesenho getPanelDesenho() {
		return panelDesenho;
	}
	
	public void setPanelDesenho(PanelDesenho panel) {
		this.panelDesenho = panel;
		
		java.awt.GridBagConstraints gridBagConstraints;
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.CENTER;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 100.0;
		gridBagConstraints.weighty = 100.0;
		gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
		getContentPane().add(panelDesenho, gridBagConstraints);
	}
}
