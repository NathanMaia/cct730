package br.edu.unifei.cct730.trabalho04.gui.janelas;

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
		panelBotao = new javax.swing.JPanel();
		btnFinalizar = new JButtonSair("Finalizar", this);
		
		getContentPane().setLayout(new java.awt.GridBagLayout());
		
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
		
		panelBotao.add(btnFinalizar);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.CENTER;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 100.0;
		gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
		getContentPane().add(panelBotao, gridBagConstraints);	
	}
	
	// Declaracao dos componentes da GUI
	private javax.swing.JTextArea txtDadosHistograma;
	private javax.swing.JPanel panelBotao;
	private JButtonSair btnFinalizar;

	// Metodos getters e setters
	public JButtonSair getBtnFinalizar() {
		return btnFinalizar;
	}

	// Metodos responsaveis pela manipulacao do Histograma
	@Override
	public Object getBean() {
		return this.bean;
	}

	@Override
	public void setBean(Object b) {
		this.bean = (Histograma)b;
	}
}
