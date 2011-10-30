package br.edu.unifei.cct730.trabalho08.gui.janelas;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JInternalFrame;
import javax.swing.border.TitledBorder;

import br.edu.unifei.cct730.trabalho08.gui.componentes.JButtonSair;

/**
 * Classe responsavel pela apresentacao dos dados
 * da compressao pelo metodo da arvore de huffman
 * 
 * @author fknappe
 *
 */
public class JanelaRelatorio extends javax.swing.JInternalFrame {
	
	/**
	 * Construtor
	 */
	public JanelaRelatorio() {
		super("Relatorio - Arvore de Huffman");
		this.initComponents();
	}
	
	/**
	 * Metodo responsavel pela inicializacao e
	 * disposicao dos componentes da GUI
	 * 
	 * @return void
	 */
	public void initComponents() {
		
		java.awt.GridBagConstraints gridBagConstraints;
		
		// Inicializacao dos componentes da GUI
		panelBotao = new javax.swing.JPanel();
		txtAreaRelatorio = new javax.swing.JTextArea();
		btnCompressao = new javax.swing.JButton("Realizar compressao");
		btnFinalizar = new JButtonSair("Fechar", this);
		
		getContentPane().setLayout(new java.awt.GridBagLayout());
		
		txtAreaRelatorio.setBorder(BorderFactory.createTitledBorder(null, "Compressao de Dados", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));	
		txtAreaRelatorio.setEditable(false);
		scrollPane = new javax.swing.JScrollPane(txtAreaRelatorio);
		scrollPane.setVerticalScrollBarPolicy(javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.CENTER;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 100.0;
		gridBagConstraints.weighty = 100.0;
		gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
		getContentPane().add(scrollPane, gridBagConstraints);
		
		panelBotao.add(btnCompressao);
		panelBotao.add(btnFinalizar);
		
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.CENTER;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 100.0;
		gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
		getContentPane().add(panelBotao, gridBagConstraints);
		
		setSize(new Dimension(625, 375));
		setResizable(false);
		setMaximizable(false);
		setDefaultCloseOperation(JInternalFrame.DO_NOTHING_ON_CLOSE);
	}
	
	// Declaracao dos componentes da GUI
	private javax.swing.JPanel panelBotao;
	private javax.swing.JTextArea txtAreaRelatorio;
	private javax.swing.JScrollPane scrollPane;
	private javax.swing.JButton btnCompressao;
	private JButtonSair btnFinalizar;
	
	// Metodos getters e setters
	public javax.swing.JTextArea getTxtAreaRelatorio() {
		return txtAreaRelatorio;
	}

	public javax.swing.JButton getBtnCompressao() {
		return btnCompressao;
	}

	public JButtonSair getBtnFinalizar() {
		return btnFinalizar;
	}
}
