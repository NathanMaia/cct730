package br.edu.unifei.cct730.trabalho04.gui.janelas;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;

import br.edu.unifei.cct730.trabalho03.gui.componentes.JButtonSair;
import br.edu.unifei.cct730.trabalho04.eventos.BeanPanel;
import br.edu.unifei.cct730.trabalho04.gui.painel.PainelHistograma;
import br.edu.unifei.cct730.trabalho04.utils.histograma.Histograma;

/**
 * Classe responsavel por instaciar a interface que apresenta os dados
 * do histograma da imagem
 * 
 * @author fknappe
 *
 */
public class JanelaHistograma extends javax.swing.JInternalFrame implements BeanPanel {
	
	//Declaracao das variaveis de instacia
	private Histograma bean = null;
	private boolean equalizacao = false;
	
	/**
	 * Construtor 
	 * 
	 * @param Histograma h
	 */
	public JanelaHistograma(
			String title, 
			Histograma h,
			boolean equalizacao) {
		super(title);
		this.setBean(h);
		this.equalizacao = equalizacao;
		initComponents();
	}
	
	/**
	 * Metodo responsavel pela inicializacao e disposicao
	 * dos componentes da GUI
	 * 
	 * @return void
	 */
	public void initComponents() {
		
		java.awt.GridBagConstraints gridBagConstraints;
		
		panelHistograma = new PainelHistograma(this.bean);
		panelLimiar = new javax.swing.JPanel();
		panelBotao = new javax.swing.JPanel();
		btnReiniciar = new javax.swing.JButton();
		btnOk = new javax.swing.JButton();
		btnEqualizar = new javax.swing.JButton();
		sliderLimiar = new javax.swing.JSlider();
		lblValorLimiar = new javax.swing.JLabel();
		
		getContentPane().setLayout(new java.awt.GridBagLayout());
		
		panelHistograma.setBackground(Color.WHITE);
		panelHistograma.setBorder(BorderFactory.createTitledBorder(null, "Histograma", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.CENTER;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 100.0;
		gridBagConstraints.weighty = 100.0;
		gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
		getContentPane().add(panelHistograma, gridBagConstraints);
		
		panelLimiar.setBorder(BorderFactory.createTitledBorder(null, "Limiar", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));
		panelLimiar.setLayout(new java.awt.GridBagLayout());
		
		sliderLimiar.setMinimum(0);
		sliderLimiar.setMaximum(255);
		sliderLimiar.setValue(128);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.CENTER;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 100.0;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		panelLimiar.add(sliderLimiar, gridBagConstraints);
		
		lblValorLimiar.setText("128");
		lblValorLimiar.setHorizontalAlignment(JLabel.CENTER);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.CENTER;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 100.0;
		gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
		panelLimiar.add(lblValorLimiar, gridBagConstraints);
		
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.CENTER;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 100.0;
		gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
		getContentPane().add(panelLimiar, gridBagConstraints);
		
		if(this.equalizacao) {
			btnEqualizar.setText("Equalizar");
			panelBotao.add(btnEqualizar);
			getContentPane().remove(panelLimiar);
		}
		
		btnReiniciar.setText("Reiniciar");
		panelBotao.add(btnReiniciar);
		
		btnOk.setText("Finalizar");
		panelBotao.add(btnOk);
		
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.CENTER;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 100.0;
		gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
		getContentPane().add(panelBotao, gridBagConstraints);
		
		setVisible(true);
		setClosable(true);
		setResizable(false);
		setMaximizable(false);
		setDefaultCloseOperation(JInternalFrame.DO_NOTHING_ON_CLOSE);
		setSize(new Dimension(315, 400));
		this.setLocation(325, 10);
	}
	
	// Declaracao dos componentes da GUI
	private PainelHistograma panelHistograma;
	private javax.swing.JPanel panelLimiar;
	private javax.swing.JLabel lblValorLimiar;
	private javax.swing.JPanel panelBotao;
	private javax.swing.JButton btnOk;
	private javax.swing.JButton btnReiniciar;
	private javax.swing.JButton btnEqualizar;
	private javax.swing.JSlider sliderLimiar;
	
	// Metodos responsaveis pela manipulacao do Histograma
	@Override
	public Object getBean() {
		return this.bean;
	}

	@Override
	public void setBean(Object b) {
		this.bean = (Histograma)b;
	}

	// Metodos getters e setters
	public PainelHistograma getPanelHistograma() {
		return panelHistograma;
	}

	public javax.swing.JButton getBtnOk() {
		return btnOk;
	}

	public javax.swing.JButton getBtnReiniciar() {
		return btnReiniciar;
	}

	public javax.swing.JButton getBtnEqualizar() {
		return btnEqualizar;
	}

	public javax.swing.JSlider getSliderLimiar() {
		return sliderLimiar;
	}

	public javax.swing.JLabel getLblValorLimiar() {
		return lblValorLimiar;
	}
}
