package br.edu.unifei.cct730.trabalho04.gui.janelas;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JInternalFrame;

import br.edu.unifei.cct730.trabalho03.gui.componentes.JButtonSair;
import br.edu.unifei.cct730.trabalho04.eventos.BeanPanel;
import br.edu.unifei.cct730.trabalho04.utils.Histograma;

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
	
	/**
	 * Construtor 
	 * 
	 * @param Histograma h
	 */
	public JanelaHistograma(Histograma h) {
		super("Histograma");
		initComponents();
		this.setBean(h);
	}
	
	/**
	 * Metodo responsavel pela inicializacao e disposicao
	 * dos componentes da GUI
	 * 
	 * @return void
	 */
	public void initComponents() {
		
		java.awt.GridBagConstraints gridBagConstraints;
		
		txtDadosHistograma = new javax.swing.JTextArea();
		
		getContentPane().setLayout(new java.awt.GridBagLayout());
		
		txtDadosHistograma.setBackground(new Color(237, 237, 237));
		txtDadosHistograma.setBorder(BorderFactory.createEtchedBorder());
		txtDadosHistograma.setEditable(false);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.CENTER;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 100.0;
		gridBagConstraints.weighty = 100.0;
		gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
		getContentPane().add(txtDadosHistograma, gridBagConstraints);
		
		this.setVisible(true);
		setResizable(false);
		setMaximizable(false);
		this.setSize(new Dimension(778, 455));
		this.setDefaultCloseOperation(JInternalFrame.DO_NOTHING_ON_CLOSE);
	}
	
	// Declaracao dos componentes da GUI
	private javax.swing.JTextArea txtDadosHistograma;
	
	public javax.swing.JTextArea getTxtDadosHistograma() {
		return txtDadosHistograma;
	}

	// Metodos responsaveis pela manipulacao do Histograma
	@Override
	public Object getBean() {
		return this.bean;
	}

	@Override
	public void setBean(Object b) {
		this.bean = (Histograma) b;
		
		int j = 0;
		String data = "";
		for(int i = 0; i < this.bean.getHistograma().length; i++) {
			if(this.bean.getHistograma()[i] > 0) {
				if(j % 8 == 0) data += "\n";
				j++;
				data += "" + i + 
					" - " + this.bean.getHistograma()[i] + "\t";
			}
		}
		this.getTxtDadosHistograma().setText(data);
	}
}
