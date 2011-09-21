package br.edu.unifei.cct730.trabalho06.descompressao.gui;

import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;

import br.edu.unifei.cct730.trabalho06.descompressao.controlador.ControladorJanelaDescompressao;
import br.edu.unifei.cct730.trabalho06.gui.componentes.JButtonAbrirArquivo;
import br.edu.unifei.cct730.trabalho06.gui.componentes.JButtonDescomprimirImagem;
import br.edu.unifei.cct730.trabalho06.gui.componentes.JButtonSair;
import br.edu.unifei.cct730.trabalho06.gui.componentes.JButtonSobre;

/**
 * 
 * @author fknappe
 *
 */
public class JanelaDescompressao extends javax.swing.JFrame {
	
	// Declaracao das variaveis de instancia
	private ControladorJanelaDescompressao med;
	
	/**
	 * Construtor 
	 * 
	 * @param Stirng title
	 */
	public JanelaDescompressao(String title) {
		super(title);
		this.med = new ControladorJanelaDescompressao(this);
		this.initComponents();
		med.registraEventos();
	}
	
	/**
	 * Metodo responsavel pela inicializacao
	 * e disposicao dos components na GUI
	 * 
	 * @return void
	 */
	public void initComponents() {
		
		java.awt.GridBagConstraints gridBagConstraints;
		
		desktop = new javax.swing.JDesktopPane();
		panelMenu = new javax.swing.JPanel();
		btnAbrirArquivo = new JButtonAbrirArquivo("Abrir arquivo...", this.med);
		btnDescomprimir = new JButtonDescomprimirImagem("Descomprimir imagem...", this.med);
		btnSobre = new JButtonSobre("Sobre", this.med);
		btnSair = new JButtonSair("Sair...", this.med);
		
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
		btnDescomprimir.setEnabled(false);
		panelMenu.add(btnDescomprimir);
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
	private JButtonAbrirArquivo btnAbrirArquivo;
	private JButtonDescomprimirImagem btnDescomprimir;
	private JButtonSair btnSair;
	private JButtonSobre btnSobre;

	// Metodos getters e setters
	public JButtonAbrirArquivo getBtnAbrirArquivo() {
		return btnAbrirArquivo;
	}
	
	public JButtonDescomprimirImagem getBtnDescomprimir() {
		return btnDescomprimir;
	}

	public JButtonSair getBtnSair() {
		return btnSair;
	}

	public JButtonSobre getBtnSobre() {
		return btnSobre;
	}
}
