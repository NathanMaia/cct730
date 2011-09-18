package br.edu.unifei.cct730.trabalho05.gui.janelas;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;

import br.edu.unifei.cct730.trabalho05.gui.componentes.JButtonSair;

/**
 * Classe responsavel pela escolha do matriz
 * que ira aplicar o filtro do tipo passa baixa 
 * sobre a imagem
 * 
 * @author fknappe
 *
 */
public class JanelaFiltroPassaBaixa extends javax.swing.JInternalFrame {
	
	/**
	 * Construtor
	 * 
	 */
	public JanelaFiltroPassaBaixa(String title) {
		super(title);
		this.initComponents();
	}
	
	/**
	 * Metodo responsavel pela inicializacao
	 * e disposicao dos componentes na GUI
	 * 
	 * @return void
	 */
	public void initComponents() {
		
		java.awt.GridBagConstraints gridBagConstraints;
		
		// Incializacao dos componentes da GUI
		panelTamanhoMatriz = new javax.swing.JPanel();
		lblTamanhoMatriz = new javax.swing.JLabel();
		panelSlider = new javax.swing.JPanel();
		sliderMatriz = new javax.swing.JSlider();
		panelBotao = new javax.swing.JPanel();
		btnOk = new javax.swing.JButton("Ok");
		btnCancelar = new JButtonSair("Cancelar", this);
		
		getContentPane().setLayout(new java.awt.GridBagLayout());
		
		panelTamanhoMatriz.setBorder(BorderFactory.createTitledBorder(null, "Matriz", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));
		panelTamanhoMatriz.setLayout(new java.awt.GridBagLayout());
		
		lblTamanhoMatriz.setText("3x3");
		lblTamanhoMatriz.setHorizontalAlignment(JLabel.CENTER);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.CENTER;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 100.0;
		gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
		panelTamanhoMatriz.add(lblTamanhoMatriz, gridBagConstraints);
		
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.CENTER;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 100.0;
		gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
		getContentPane().add(panelTamanhoMatriz, gridBagConstraints);
		
		sliderMatriz.setValue(0);
		sliderMatriz.setMinimum(0);
		sliderMatriz.setMaximum(10);
		
		panelSlider.setBorder(BorderFactory.createTitledBorder(""));
		panelSlider.setLayout(new java.awt.GridBagLayout());
		
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.CENTER;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 100.0;
		gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
		panelSlider.add(sliderMatriz, gridBagConstraints);
		
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.CENTER;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 100.0;
		gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
		getContentPane().add(panelSlider, gridBagConstraints);
		
		panelBotao.setBorder(BorderFactory.createTitledBorder(""));
		
		panelBotao.add(btnOk);
		panelBotao.add(btnCancelar);
		
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.CENTER;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 100.0;
		gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
		getContentPane().add(panelBotao, gridBagConstraints);
		
		this.setVisible(true);
		this.setMaximizable(false);
		this.setResizable(false);
		this.setSize(new Dimension(350, 215));
	}

	// Declaracao dos componentes da GUI
	private javax.swing.JPanel panelTamanhoMatriz;
	private javax.swing.JLabel lblTamanhoMatriz;
	private javax.swing.JPanel panelSlider;
	private javax.swing.JSlider sliderMatriz;
	private javax.swing.JPanel panelBotao;
	private javax.swing.JButton btnOk;
	private JButtonSair btnCancelar;
	
	// Metodos getters e setters
	public javax.swing.JSlider getSliderMatriz() {
		return sliderMatriz;
	}

	public JButtonSair getBtnCancelar() {
		return btnCancelar;
	}

	public javax.swing.JButton getBtnOk() {
		return btnOk;
	}

	public javax.swing.JLabel getLblTamanhoMatriz() {
		return lblTamanhoMatriz;
	}
}
