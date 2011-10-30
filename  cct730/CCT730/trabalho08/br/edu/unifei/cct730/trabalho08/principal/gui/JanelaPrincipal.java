package br.edu.unifei.cct730.trabalho08.principal.gui;

import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;

import br.edu.unifei.cct730.trabalho08.gui.componentes.JButtonAbrirArquivo;
import br.edu.unifei.cct730.trabalho08.gui.componentes.JButtonSair;
import br.edu.unifei.cct730.trabalho08.gui.componentes.JButtonSobre;
import br.edu.unifei.cct730.trabalho08.principal.controlador.ControladorPrincipal;

/**
 * 
 * @author fknappe
 *
 */
public class JanelaPrincipal extends javax.swing.JFrame {
	
	// Declaracao das variaveis de instancia
	private ControladorPrincipal med;
	
	/**
	 * Construtor
	 * 
	 * @param String title
	 */
	public JanelaPrincipal(String title) {
		super(title);
		this.med = new ControladorPrincipal(this);
		this.initComponents();
		this.med.registraEventos();
	}
	
	/**
	 * Metodo responsavel pela inicializacao e 
	 * disposicao dos componentes da GUI
	 * 
	 * @return void
	 */
	public void initComponents() {
		
		java.awt.GridBagConstraints gridBagConstraints;
		
		getContentPane().setLayout(new java.awt.GridBagLayout());
		
		panelMenu = new javax.swing.JPanel();
		desktop = new javax.swing.JDesktopPane();
		btnAbrirArquivo = new JButtonAbrirArquivo("Abrir arquivo...", this.med);
		btnSair = new JButtonSair("Sair", this.med);
		btnSobre = new JButtonSobre("Sobre", this.med);
		
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
	
	/**
	 * Metodo responsavel por habilitar
	 * as acoes do menu
	 * 
	 * @return void
	 */
	public void habilitarMenu() {
		
	}
	
	/**
	 * Metodo responsavel por desabilitar
	 * as acoes do menu
	 * 
	 * @return void
	 */
	public void desabilitarMenu() {
		
	}
	
	// Declaracao dos componentes da GUI
	private javax.swing.JPanel panelMenu;
	private javax.swing.JDesktopPane desktop;
	private JButtonAbrirArquivo btnAbrirArquivo;
	private JButtonSair btnSair;
	private JButtonSobre btnSobre;

	//Metodos getters e setters	
	public JButtonAbrirArquivo getBtnAbrirArquivo() {
		return btnAbrirArquivo;
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
