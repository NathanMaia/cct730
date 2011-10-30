package br.edu.unifei.cct730.trabalho08.gui.janelas;

import br.edu.unifei.cct730.trabalho06.gui.componentes.JButtonComprimirImagem;
import br.edu.unifei.cct730.trabalho06.gui.componentes.JButtonSair;
import br.edu.unifei.cct730.trabalho06.gui.painel.Celula;
import br.edu.unifei.cct730.trabalho06.gui.painel.PainelDesenho;

/**
 * Classe responsavel por instanciar a janela que
 * representa a imagem sintetica
 * 
 * @author fknappe
 *
 */
public class JanelaImagemSintetica extends javax.swing.JInternalFrame {

	/**
	 * Construtor
	 */
	public JanelaImagemSintetica(String title) {
		super(title);
		this.initComponents();
	}
	
	/**
	 * MŽtodo responsavel pela inicializacao e posicionamento dos 
	 * componentes da GUI
	 * 
	 * @return void
	 */
	private void initComponents() {
		
		java.awt.GridBagConstraints gridBagConstraints;
		
		// Instanciando os componentes da GUI
		panelBotao = new javax.swing.JPanel();
		btnSair = new JButtonSair("Finalizar", this);
		btnComprimir = new javax.swing.JButton("Comprimir Imagem...");
		
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
		
		panelBotao.add(btnComprimir);
		panelBotao.add(btnSair);
		
		// Par‰metros da interface
		setMaximizable(true);
		setResizable(true);
		setSize(800, 600);
	}
	
	// Declaracao dos componentes da GUI
	private JButtonSair btnSair;
	private javax.swing.JButton btnComprimir;
	private PainelDesenho panelDesenho;
	private javax.swing.JPanel panelBotao;
	
	//Metodos getters e setters
	public JButtonSair getBtnSair() {
		return btnSair;
	}

	public PainelDesenho getPanelDesenho() {
		return panelDesenho;
	}
	
	public void setPanelDesenho(PainelDesenho panel, boolean compressao) {
		
		this.panelDesenho = panel;
		
		if(compressao) btnComprimir.setVisible(true);
		else btnComprimir.setVisible(false);
		
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

	public javax.swing.JButton getBtnComprimir() {
		return btnComprimir;
	}
}
