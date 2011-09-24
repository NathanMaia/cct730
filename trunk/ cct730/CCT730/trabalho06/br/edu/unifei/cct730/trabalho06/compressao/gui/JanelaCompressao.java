package br.edu.unifei.cct730.trabalho06.compressao.gui;

import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;

import br.edu.unifei.cct730.trabalho06.compressao.controlador.ControladorJanelaCompressao;
import br.edu.unifei.cct730.trabalho06.gui.componentes.JButtonAbrirPanel;
import br.edu.unifei.cct730.trabalho06.gui.componentes.JButtonComprimirImagem;
import br.edu.unifei.cct730.trabalho06.gui.componentes.JButtonSair;
import br.edu.unifei.cct730.trabalho06.gui.componentes.JButtonSobre;

/**
 * Classe responsavel por criar uma instancia
 * da janela que contem as acoes para compressao
 * da imagem sintetica
 * 
 * @author fknappe
 *
 */
public class JanelaCompressao extends javax.swing.JFrame {

	// Declaracao das variaveis de instancia
	private ControladorJanelaCompressao med;

	/**
	 * Construtor 
	 * 
	 * @param String title
	 */
	public JanelaCompressao(String title) {
		super(title);
		this.med = new ControladorJanelaCompressao(this);
		this.initComponents();
		this.med.registraEventos();
	}

	/**
	 * Metodo responsavel por inicializar e
	 * posicionar os componentes na GUI
	 * 
	 * @return void
	 */
	public void initComponents() {

		java.awt.GridBagConstraints gridBagConstraints;

		desktop = new javax.swing.JDesktopPane();
		panelMenu = new javax.swing.JPanel();
		btnAbrirPanel = new JButtonAbrirPanel("Criar imagem sintetica...", this.med);
		btnSair = new JButtonSair("Sair", this.med);
		btnSobre = new JButtonSobre("Sobre", this.med);

		getContentPane().setLayout(new java.awt.GridBagLayout());

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.CENTER;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 100.0;
		gridBagConstraints.weighty = 100.0;
		gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
		getContentPane().add(desktop, gridBagConstraints);
		
		panelMenu.add(btnAbrirPanel);
		panelMenu.add(btnSobre);
		panelMenu.add(btnSair);
		
		panelMenu.setBorder(BorderFactory.createTitledBorder(null, "Menu", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.CENTER;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 100.0;
		gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
		getContentPane().add(panelMenu, gridBagConstraints);
		
		this.setExtendedState(MAXIMIZED_BOTH);	
		this.setVisible(true);
	}

	// Declaracao dos componentes da GUI
	private javax.swing.JDesktopPane desktop;
	private javax.swing.JPanel panelMenu;
	private JButtonAbrirPanel btnAbrirPanel;
	private JButtonSair btnSair;
	private JButtonSobre btnSobre;
	
	// Metodos getters e setters
	public JButtonAbrirPanel getBtnAbrirPanel() {
		return btnAbrirPanel;
	}

	public JButtonSair getBtnSair() {
		return btnSair;
	}

	public JButtonSobre getBtnSobre() {
		return btnSobre;
	}
}
