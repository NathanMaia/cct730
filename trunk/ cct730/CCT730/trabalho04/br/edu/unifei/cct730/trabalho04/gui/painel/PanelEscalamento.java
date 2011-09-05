package br.edu.unifei.cct730.trabalho04.gui.painel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import br.edu.unifei.cct730.trabalho04.eventos.BeanPanel;
import br.edu.unifei.cct730.trabalho04.utils.Transformacao;

/**
 * Classe responsavel por inicializar o painel com os parametros
 * do escalamento da imagem
 * 
 * @author fknappe
 *
 */
public class PanelEscalamento extends javax.swing.JPanel implements BeanPanel {

	// Declaracao das variaveis de instancia
	private Transformacao bean = null;

	/**
	 * Construtor
	 */
	public PanelEscalamento() {
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
		
		// Inicializacao dos componentes da GUI
		lblTipo = new javax.swing.JLabel();
		lblFator = new javax.swing.JLabel();
		comboTipo = new JComboBox(new String[]{ "Ampliacao", "Reducao" });
		spnFator = new JSpinner(new SpinnerNumberModel(2, 2, null, 1));
		
		this.setLayout(new java.awt.GridBagLayout());

		lblTipo.setText("Tipo: ");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
		this.add(lblTipo, gridBagConstraints);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
		gridBagConstraints.weightx = 100.0;
		gridBagConstraints.insets = new java.awt.Insets(2, 0, 2, 5);
		this.add(comboTipo, gridBagConstraints);

		lblFator.setText("Fator: ");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 5);
		this.add(lblFator, gridBagConstraints);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.ipadx = 10;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
		gridBagConstraints.insets = new java.awt.Insets(2, 0, 2, 5);
		this.add(spnFator, gridBagConstraints);
	}

	// Declaracao de components da GUI
    private javax.swing.JLabel lblTipo; 
	private javax.swing.JLabel lblFator;
	private javax.swing.JComboBox comboTipo;
	private javax.swing.JSpinner spnFator;

	/**
	 * Metodo responsavel por retorna o objeto
	 * transformacao parametrizada
	 * 
	 * @return Transformacao
	 */
	public Transformacao getBean() {
		// Declaracao de variaveis
		int indiceTipo, fator = 0;

		// Obtem os dados digitados
		indiceTipo = this.getComboTipo().getSelectedIndex();
		fator = (Integer) this.getSpnFator().getValue();

		this.bean = new Transformacao(indiceTipo, fator);
		return this.bean;
	}
	
	/**
	 * Metodo responsavel por definir o objeto
	 * de transformacao referente a esta interface
	 * 
	 * @param Object b
	 * 
	 * @return void
	 */
	public void setBean(Object b) {
		this.bean = (Transformacao)b;
	}

	// MŽtodos getters e setters
	public javax.swing.JComboBox getComboTipo() {
		return comboTipo;
	}

	public javax.swing.JSpinner getSpnFator() {
		return spnFator;
	}
}
