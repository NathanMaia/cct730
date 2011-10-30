package br.edu.unifei.cct730.trabalho08.principal.gui;

import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;

import br.edu.unifei.cct730.trabalho08.gui.componentes.JButtonAbrirPanel;
import br.edu.unifei.cct730.trabalho08.gui.componentes.JButtonAplicarMorfologico;
import br.edu.unifei.cct730.trabalho08.gui.componentes.JButtonDefinirEstruturante;
import br.edu.unifei.cct730.trabalho08.gui.componentes.JButtonSair;
import br.edu.unifei.cct730.trabalho08.gui.componentes.JButtonSobre;
import br.edu.unifei.cct730.trabalho08.principal.controlador.ControladorPrincipal;
import br.edu.unifei.cct730.trabalho08.utils.Constantes;

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

		panelMenuSuperior = new javax.swing.JPanel();
		panelMenuInferior = new javax.swing.JPanel();
		desktop = new javax.swing.JDesktopPane();
		comboBoxMorfologico = new javax.swing.JComboBox(Constantes.OPERACOES_MORFOLOGICAS);
		btnAbrirPanel = new JButtonAbrirPanel("Abrir imagem sintetica...", this.med);
		btnAplicar = new JButtonAplicarMorfologico("Aplicar...", this.med);
		btnEstruturante = new JButtonDefinirEstruturante("Definir elem. estruturante", this.med);
		btnSair = new JButtonSair("Sair", this.med);
		btnSobre = new JButtonSobre("Sobre", this.med);

		panelMenuSuperior.add(comboBoxMorfologico);
		panelMenuSuperior.add(btnAplicar);

		panelMenuSuperior.setBorder(BorderFactory.createTitledBorder(null, "Operacoes", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.CENTER;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 100.0;
		gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
		getContentPane().add(panelMenuSuperior, gridBagConstraints);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.CENTER;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 100.0;
		gridBagConstraints.weighty = 100.0;
		gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
		getContentPane().add(desktop, gridBagConstraints);

		panelMenuInferior.add(btnAbrirPanel);
		panelMenuInferior.add(btnEstruturante);
		panelMenuInferior.add(btnSobre);
		panelMenuInferior.add(btnSair);

		panelMenuInferior.setBorder(BorderFactory.createTitledBorder(null, "Menu de Opcoes", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.CENTER;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 100.0;
		gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
		getContentPane().add(panelMenuInferior, gridBagConstraints);

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
	private javax.swing.JPanel panelMenuSuperior;
	private javax.swing.JPanel panelMenuInferior;
	private javax.swing.JDesktopPane desktop;
	private javax.swing.JComboBox comboBoxMorfologico;
	private JButtonAbrirPanel btnAbrirPanel;
	private JButtonAplicarMorfologico btnAplicar;
	private JButtonDefinirEstruturante btnEstruturante;
	private JButtonSair btnSair;
	private JButtonSobre btnSobre;

	//Metodos getters e setters	

	public javax.swing.JDesktopPane getDesktop() {
		return desktop;
	}

	public javax.swing.JComboBox getComboBoxMorfologico() {
		return comboBoxMorfologico;
	}

	public JButtonSair getBtnSair() {
		return btnSair;
	}

	public JButtonAbrirPanel getBtnAbrirPanel() {
		return btnAbrirPanel;
	}

	public JButtonAplicarMorfologico getBtnAplicar() {
		return btnAplicar;
	}

	public JButtonDefinirEstruturante getBtnEstruturante() {
		return btnEstruturante;
	}

	public JButtonSobre getBtnSobre() {
		return btnSobre;
	}
}
