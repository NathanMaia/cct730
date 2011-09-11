package br.edu.unifei.cct730.trabalho04.gui.janelas;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BorderFactory;

import br.edu.unifei.cct730.trabalho04.gui.componentes.JButtonSair;
import br.edu.unifei.cct730.trabalho04.gui.painel.PainelEscalamento;

/**
 * Classe responsavel por inicializar a interface que apresenta
 * os parametros para realizar a transformacao de escalamento
 * da imagem binarizada
 * 
 * @author fknappe
 *
 */
public class JanelaParametrosZoom extends javax.swing.JInternalFrame {
	
	/**
	 * Construtor
	 */
	public JanelaParametrosZoom() {
		super("Escalamento");
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
		
		panelEscalamento = new PainelEscalamento();
		panelBotao = new javax.swing.JPanel();
		btnCancelar = new JButtonSair("Cancelar", this);
		btnOk = new javax.swing.JButton("Ok");
		
		panelBotao.add(btnOk);
		panelBotao.add(btnCancelar);

		getContentPane().setLayout(new java.awt.GridBagLayout());
		
		panelEscalamento.setBorder(BorderFactory.createTitledBorder(""));
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.CENTER;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 100.0;
		gridBagConstraints.weighty = 100.0;
		gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
		getContentPane().add(panelEscalamento, gridBagConstraints);
		
		panelBotao.add(btnOk);
		panelBotao.add(btnCancelar);
		
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.CENTER;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 100.0;
		gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
		getContentPane().add(panelBotao, gridBagConstraints);
		
		// Inicializacao do GUI
		setSize(250, 150);
		setResizable(false);

		// Posicionamento
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int posX = (d.width - this.getWidth()) / 2;
		int posY = (d.height - this.getHeight()) / 2;
		setLocation(posX, posY);
		setVisible(true);
	}
	
	// Declaracao dos componentes da GUI
	private PainelEscalamento panelEscalamento;
	private javax.swing.JPanel panelBotao;
	private JButtonSair btnCancelar;
	private javax.swing.JButton btnOk;
	
	// Metodos getters e setters
	public PainelEscalamento getPanelEscalamento() {
		return panelEscalamento;
	}

	public JButtonSair getBtnCancelar() {
		return btnCancelar;
	}

	public javax.swing.JButton getBtnOk() {
		return btnOk;
	}
}
