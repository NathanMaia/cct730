package br.edu.unifei.cct730.trabalho07.descompressao.gui;

import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;

import br.edu.unifei.cct730.trabalho07.descompressao.controlador.ControladorJanelaDescompressao;
import br.edu.unifei.cct730.trabalho07.gui.componentes.JButtonAbrirArquivo;
import br.edu.unifei.cct730.trabalho07.gui.componentes.JButtonDescomprimirHuffman;
import br.edu.unifei.cct730.trabalho07.gui.componentes.JButtonSair;
import br.edu.unifei.cct730.trabalho07.gui.componentes.JButtonSobre;

/**
 * Classe responsavel por criar uma instancia
 * da janela que contem as acoes para descompressao
 * da imagem
 * 
 * @author fknappe
 *
 */
public class JanelaDescompressao extends javax.swing.JFrame {

	// Declaracao de variaveis de instancia
	private ControladorJanelaDescompressao med;
	
	/**
	 * Construtor
	 * 
	 * @param String title
	 */
	public JanelaDescompressao(String title) {
		super(title);
		this.med = new ControladorJanelaDescompressao(this);
		this.initComponents();
		this.med.registraEventos();
	}
	
	/**
	 * Metodo responsavel por instanciar e posicionar
	 * os componentes da GUI
	 * 
	 * @return void
	 */
	public void initComponents() {
		
		java.awt.GridBagConstraints gridBagConstraints;
		
		// Inicializacao dos componentes da GUI
		panelMenu = new javax.swing.JPanel();
		desktop = new javax.swing.JDesktopPane();
		btnAbrirArquivo = new JButtonAbrirArquivo("Abrir arquivo...", this.med);
		btnDescomprimirHuffman = new JButtonDescomprimirHuffman("Descomprimir arquivo...", this.med);
		btnSobre = new JButtonSobre("Sobre", this.med);
		btnSair = new JButtonSair("Sair", this.med);
		
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
		panelMenu.add(btnDescomprimirHuffman);
		panelMenu.add(btnSobre);
		panelMenu.add(btnSair);
		
		btnDescomprimirHuffman.setEnabled(false);
		
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
	
	private javax.swing.JPanel panelMenu;
	private javax.swing.JDesktopPane desktop;
	private JButtonAbrirArquivo btnAbrirArquivo;
	private JButtonDescomprimirHuffman btnDescomprimirHuffman;
	private JButtonSobre btnSobre;
	private JButtonSair btnSair;
	
	/**
	 * Metodo responsavel por habilitar as acoes do menu
	 * 
	 * @return void
	 */
	public void habilitarMenu() {
		this.getBtnDescomprimirHuffman().setEnabled(true);
	}
	
	/**
	 * Metodo responsavel por desabilitar as acoes do menu
	 * 
	 * @return void
	 */
	public void desabilitarMenu() {
		this.getBtnDescomprimirHuffman().setEnabled(false);
	}
	
	//Metodos getters e setters
	
	public javax.swing.JDesktopPane getDesktop() {
		return desktop;
	}

	public JButtonAbrirArquivo getBtnAbrirArquivo() {
		return btnAbrirArquivo;
	}

	public JButtonDescomprimirHuffman getBtnDescomprimirHuffman() {
		return btnDescomprimirHuffman;
	}

	public JButtonSobre getBtnSobre() {
		return btnSobre;
	}

	public JButtonSair getBtnSair() {
		return btnSair;
	}
}
