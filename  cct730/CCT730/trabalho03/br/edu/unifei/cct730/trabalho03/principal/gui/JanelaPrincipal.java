package br.edu.unifei.cct730.trabalho03.principal.gui;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import br.edu.unifei.cct730.trabalho03.gui.componentes.JButtonAbrirPanel;
import br.edu.unifei.cct730.trabalho03.gui.componentes.JButtonEspelhamentoHorizontal;
import br.edu.unifei.cct730.trabalho03.gui.componentes.JButtonEspelhamentoVertical;
import br.edu.unifei.cct730.trabalho03.gui.componentes.JButtonRotacao;
import br.edu.unifei.cct730.trabalho03.gui.componentes.JButtonSair;
import br.edu.unifei.cct730.trabalho03.gui.componentes.JButtonSobre;
import br.edu.unifei.cct730.trabalho03.gui.componentes.JButtonTranslacao;
import br.edu.unifei.cct730.trabalho03.principal.controlador.ControladorPrincipal;


/**
 * Classe responsavel por instanciar a GUI principal do aplicativo
 * @author fknappe
 *
 */
public class JanelaPrincipal extends javax.swing.JFrame {
	
	//Declaracao das variaveis de instacia
	private ControladorPrincipal med = null;
	
	/**
	 * Construtor
	 * @param title
	 */
	public JanelaPrincipal(String title) {
		super(title);
		med = new ControladorPrincipal(this);
		this.initComponents();
		med.registraEventos();
	}
	
	/**
	 * Metodo responsavel por inicializar e posicionar os componentes da GUI
	 * 
	 * @return void
	 */
	public void initComponents() {
		java.awt.GridBagConstraints gridBagConstraints;
		
		// Definindo o layout do painel
		getContentPane().setLayout(new java.awt.GridBagLayout());
		
		// Instanciando os componentes da GUI
		desktop = new JDesktopPane();
		panelFuncao = new javax.swing.JPanel();
		btnFinalizar = new JButtonSair("Finalizar...", this.med);
		btnSobre = new JButtonSobre("Sobre...", this.med);
		btnAbrirPanel = new JButtonAbrirPanel("Nova Imagem Sintetica...", this.med);
		btnEspelhamentoH = new JButtonEspelhamentoHorizontal("Espelhamento Horizontal", this.med);
		btnEspelhamentoV = new JButtonEspelhamentoVertical("Espelhamento Vertical", this.med);
		btnRotacao = new JButtonRotacao("Rotacao", this.med);
		btnTranslacao = new JButtonTranslacao("Translacao", this.med);
		
		//Posicionando os componentes da GUI
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.CENTER;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 100.0;
		gridBagConstraints.weighty = 100.0;
		gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
		getContentPane().add(desktop, gridBagConstraints);
		
		panelFuncao.add(btnAbrirPanel);
		btnEspelhamentoH.setEnabled(false);
		panelFuncao.add(btnEspelhamentoH);
		btnEspelhamentoV.setEnabled(false);
		panelFuncao.add(btnEspelhamentoV);
		btnRotacao.setEnabled(false);
		panelFuncao.add(btnRotacao);
		btnTranslacao.setEnabled(false);
		panelFuncao.add(btnTranslacao);
		panelFuncao.add(btnSobre);
		panelFuncao.add(btnFinalizar);
		
		panelFuncao.setBorder(BorderFactory.createTitledBorder(null, "Menu", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.CENTER;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 100.0;
		gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
		getContentPane().add(panelFuncao, gridBagConstraints);
		
		this.setExtendedState(MAXIMIZED_BOTH);	
		this.setVisible(true);
	}
	
	//Declaracao dos componentes da GUI
	private JDesktopPane desktop;
	private javax.swing.JPanel panelFuncao;
	private JButtonSair btnFinalizar;
	private JButtonSobre btnSobre;
	private JButtonAbrirPanel btnAbrirPanel;
	private JButtonEspelhamentoHorizontal btnEspelhamentoH;
	private JButtonEspelhamentoVertical btnEspelhamentoV;
	private JButtonRotacao btnRotacao;
	private JButtonTranslacao btnTranslacao;
	
	/**
	 * Metodo responsavel por habilitar as acoes do menu
	 * 
	 * @return void
	 */
	public void habilitaFuncoesMenu() {
		this.getBtnEspelhamentoH().setEnabled(true);
		this.getBtnEspelhamentoV().setEnabled(true);
		this.getBtnRotacao().setEnabled(true);
		this.getBtnTranslacao().setEnabled(true);
	}
	
	/**
	 * Metodo responsavel por desabilitar as acoes do menu
	 * 
	 * @return void
	 */
	public void desabilitaFuncoesMenu() {
		this.getBtnEspelhamentoH().setEnabled(false);
		this.getBtnEspelhamentoV().setEnabled(false);
		this.getBtnRotacao().setEnabled(false);
		this.getBtnTranslacao().setEnabled(false);
	}

	//Metodos getters e setters dos componentes da GUI
	public JDesktopPane getDesktop() {
		return desktop;
	}

	public JButtonSair getBtnFinalizar() {
		return btnFinalizar;
	}

	public JButtonSobre getBtnSobre() {
		return btnSobre;
	}

	public JButtonAbrirPanel getBtnAbrirPanel() {
		return btnAbrirPanel;
	}

	public JButtonEspelhamentoHorizontal getBtnEspelhamentoH() {
		return btnEspelhamentoH;
	}

	public JButtonEspelhamentoVertical getBtnEspelhamentoV() {
		return btnEspelhamentoV;
	}

	public JButtonRotacao getBtnRotacao() {
		return btnRotacao;
	}

	public JButtonTranslacao getBtnTranslacao() {
		return btnTranslacao;
	}
}
