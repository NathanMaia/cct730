package br.edu.unifei.cct730.trabalho05.gui.principal;

import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;

import br.edu.unifei.cct730.trabalho05.controlador.principal.ControladorPrincipal;
import br.edu.unifei.cct730.trabalho05.gui.componentes.JButtonFiltrar;
import br.edu.unifei.cct730.trabalho05.gui.componentes.JButtonRecarregarArquivo;
import br.edu.unifei.cct730.trabalho05.gui.componentes.JButtonSair;
import br.edu.unifei.cct730.trabalho05.gui.componentes.JButtonAbrirArquivo;
import br.edu.unifei.cct730.trabalho05.gui.componentes.JButtonSobre;
import br.edu.unifei.cct730.trabalho05.utils.constantes.Constantes;

/**
 * Classe responsavel por instanciar a interface do aplicativo
 * 
 * @author fknappe
 *
 */
public class JanelaPrincipal extends javax.swing.JFrame {
	
	// Declaracao das variaveis de instancia
	private ControladorPrincipal med = null;
	
	/**
	 * Construtor 
	 * 
	 * @param String title
	 */
	public JanelaPrincipal(String title) {
		super(title);
		med = new ControladorPrincipal(this);
		this.initComponents();
		med.registraEventos();
	}
	
	/**
	 * Metodo responsavel pela inciializacao e disposicao
	 * dos componentes da GUI
	 * 
	 * @return void
	 */
	public void initComponents() {
		
		java.awt.GridBagConstraints gridBagConstraints;
		
		// Inicializacao dos componentes da GUI
		panelMenuSuperior = new javax.swing.JPanel();
		panelMenuInferior = new javax.swing.JPanel();
		desktop = new javax.swing.JDesktopPane();
		cmbFiltros = new javax.swing.JComboBox(Constantes.FILTROS_PASSABAIXA);
		btnFiltrar = new JButtonFiltrar("Filtrar", this.med);
		btnAbrirArquivo = new JButtonAbrirArquivo("Abrir imagem...", this.med);
		btnRecarregarArquivo = new JButtonRecarregarArquivo("Recarregar imagem", this.med);
		btnSobre = new JButtonSobre("Sobre...", this.med);
		btnSair = new JButtonSair("Sair", this.med);
		
		this.setLayout(new java.awt.GridBagLayout());
		
		panelMenuSuperior.add(cmbFiltros);
		panelMenuSuperior.add(btnFiltrar);
		
		panelMenuSuperior.setBorder(BorderFactory.createTitledBorder(null, "Filtros", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));
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
		
		// Opcoes menu
		panelMenuInferior.add(btnAbrirArquivo);
		
		btnRecarregarArquivo.setEnabled(false);
		panelMenuInferior.add(btnRecarregarArquivo);
		
		panelMenuInferior.add(btnSobre);
		panelMenuInferior.add(btnSair);
		
		panelMenuInferior.setBorder(BorderFactory.createTitledBorder(null, "Menu", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.CENTER;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 100.0;
		gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
		getContentPane().add(panelMenuInferior, gridBagConstraints);
		
		this.desabilitarMenu();
		
		this.setExtendedState(MAXIMIZED_BOTH);	
		this.setVisible(true);
	}
	
	// Declaracao dos componentes da GUI
	private javax.swing.JDesktopPane desktop;
	private javax.swing.JPanel panelMenuSuperior;
	private javax.swing.JPanel panelMenuInferior;
	private JButtonFiltrar btnFiltrar;
	private javax.swing.JComboBox cmbFiltros;
	private JButtonAbrirArquivo btnAbrirArquivo;
	private JButtonRecarregarArquivo btnRecarregarArquivo;
	private JButtonSobre btnSobre;
	private JButtonSair btnSair;
	
	/**
	 * Metodo responsavel por habilitar as acoes do
	 * menu de funcoes
	 * 
	 * @return void
	 */
	public void habilitarMenu() {
		this.getBtnRecarregarArquivo().setEnabled(true);
		this.getBtnFiltrar().setEnabled(true);
		this.getCmbFiltros().setEnabled(true);
	}
	
	/**
	 * Metodo responsavel por desabilitar as acoes do menu
	 * de funcoes
	 * 
	 * @return void
	 */
	public void desabilitarMenu() {
		this.getBtnRecarregarArquivo().setEnabled(false);
		this.getCmbFiltros().setSelectedIndex(-1);
		this.getBtnFiltrar().setEnabled(false);
		this.getCmbFiltros().setEnabled(false);
	}

	// MŽtodo getters e setters dos componentes da GUI
	public javax.swing.JDesktopPane getDesktop() {
		return desktop;
	}
	
	public JButtonFiltrar getBtnFiltrar() {
		return btnFiltrar;
	}

	public javax.swing.JComboBox getCmbFiltros() {
		return cmbFiltros;
	}

	public JButtonAbrirArquivo getBtnAbrirArquivo() {
		return btnAbrirArquivo;
	}

	public JButtonRecarregarArquivo getBtnRecarregarArquivo() {
		return btnRecarregarArquivo;
	}

	public JButtonSobre getBtnSobre() {
		return btnSobre;
	}

	public JButtonSair getBtnSair() {
		return btnSair;
	}
}
