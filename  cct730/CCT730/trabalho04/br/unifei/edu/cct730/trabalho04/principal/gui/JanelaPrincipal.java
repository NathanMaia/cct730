package br.unifei.edu.cct730.trabalho04.principal.gui;

import javax.swing.BorderFactory;
import javax.swing.JDesktopPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import br.edu.unifei.cct730.trabalho04.gui.componentes.JButtonSair;
import br.edu.unifei.cct730.trabalho04.gui.componentes.JButtonAbrirArquivo;
import br.edu.unifei.cct730.trabalho04.gui.componentes.JButtonBinarizarImagem;
import br.edu.unifei.cct730.trabalho04.gui.componentes.JButtonHistograma;
import br.edu.unifei.cct730.trabalho04.gui.componentes.JButtonZoom;
import br.edu.unifei.cct730.trabalho04.principal.mediator.MediatorPrincipal;

public class JanelaPrincipal extends javax.swing.JFrame {
	
	private MediatorPrincipal med = null;
	
	public JanelaPrincipal(String title) {
		super(title);
		med = new MediatorPrincipal(this);
		this.initComponents();
		med.registraEventos();
	}
	
	public void initComponents() {
		
		java.awt.GridBagConstraints gridBagConstraints;
		
		panelFuncao = new javax.swing.JPanel();
		desktop = new javax.swing.JDesktopPane();
		btnAbrirArquivo = new JButtonAbrirArquivo("Abrir Imagem...", this.med);
		btnHistograma = new JButtonHistograma("Construir Histograma...", this.med);
		btnBinarizar = new JButtonBinarizarImagem("Binarizar Imagem...", this.med);
		btnZoom = new JButtonZoom("Zoom...", this.med);
		btnSair = new JButtonSair("Sair", this.med);
		
		this.setLayout(new java.awt.GridBagLayout());
		
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.CENTER;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 100.0;
		gridBagConstraints.weighty = 100.0;
		gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
		getContentPane().add(desktop, gridBagConstraints);
		
		panelFuncao.add(btnAbrirArquivo);
		
		btnHistograma.setEnabled(false);
		panelFuncao.add(btnHistograma);
		
		btnBinarizar.setEnabled(false);
		panelFuncao.add(btnBinarizar);
		
		btnZoom.setEnabled(false);
		panelFuncao.add(btnZoom);
		panelFuncao.add(btnSair);
		
		panelFuncao.setBorder(BorderFactory.createTitledBorder(null, "Menu de Funções", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));
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
	
	private javax.swing.JDesktopPane desktop;
	private javax.swing.JPanel panelFuncao;
	private JButtonAbrirArquivo btnAbrirArquivo;
	private JButtonBinarizarImagem btnBinarizar;
	private JButtonHistograma btnHistograma;
	private JButtonZoom btnZoom;
	private JButtonSair btnSair;

	// Método getters e setters dos componentes da GUI
	public JButtonAbrirArquivo getBtnAbrirArquivo() {
		return btnAbrirArquivo;
	}

	public JButtonBinarizarImagem getBtnBinarizar() {
		return btnBinarizar;
	}

	public JButtonHistograma getBtnHistograma() {
		return btnHistograma;
	}

	public JButtonZoom getBtnZoom() {
		return btnZoom;
	}

	public JButtonSair getBtnSair() {
		return btnSair;
	}
}
