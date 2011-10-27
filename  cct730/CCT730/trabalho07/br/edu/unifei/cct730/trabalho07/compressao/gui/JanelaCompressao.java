package br.edu.unifei.cct730.trabalho07.compressao.gui;

import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;

import br.edu.unifei.cct730.trabalho07.gui.componentes.JButtonAbrirArquivo;
import br.edu.unifei.cct730.trabalho07.gui.componentes.JButtonComprimirHuffman;
import br.edu.unifei.cct730.trabalho07.gui.componentes.JButtonSair;
import br.edu.unifei.cct730.trabalho07.gui.componentes.JButtonSobre;
import br.edu.unifei.cct730.trabalho07.compressao.controlador.ControladorJanelaCompressao;

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
	 * Metodo responsavel por inicializar
	 * e posicionar os componentes na GUI
	 * 
	 * @return void
	 */
	public void initComponents() {
		
		java.awt.GridBagConstraints gridBagConstraints;

		desktop = new javax.swing.JDesktopPane();
		panelMenu = new javax.swing.JPanel();
		btnAbrirArquivo = new JButtonAbrirArquivo("Abrir imagem...", this.med);
		btnComprimirHuffman = new JButtonComprimirHuffman("Compressao...", this.med);
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
		
		panelMenu.add(btnAbrirArquivo);
		panelMenu.add(btnComprimirHuffman);
		panelMenu.add(btnSobre);
		panelMenu.add(btnSair);
		
		btnComprimirHuffman.setEnabled(false);
		
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
	private JButtonAbrirArquivo btnAbrirArquivo;
	private JButtonComprimirHuffman btnComprimirHuffman;
	private JButtonSair btnSair;
	private JButtonSobre btnSobre;
	
	public void habilitarMenu() {
		btnComprimirHuffman.setEnabled(true);
	}
	
	public void desabilitarMenu() {
		btnComprimirHuffman.setEnabled(false);
	}
	
	// Metodos getters e setters
	public JButtonAbrirArquivo getBtnAbrirArquivo() {
		return btnAbrirArquivo;
	}	
	
	public JButtonComprimirHuffman getBtnComprimirHuffman() {
		return btnComprimirHuffman;
	}
	
	public JButtonSair getBtnSair() {
		return btnSair;
	}

	public JButtonSobre getBtnSobre() {
		return btnSobre;
	}

	public javax.swing.JDesktopPane getDesktop() {
		return desktop;
	}
}
