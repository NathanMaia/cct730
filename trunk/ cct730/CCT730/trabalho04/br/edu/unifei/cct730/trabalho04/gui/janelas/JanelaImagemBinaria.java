package br.edu.unifei.cct730.trabalho04.gui.janelas;

import javax.swing.JInternalFrame;

import br.edu.unifei.cct730.trabalho04.gui.painel.PainelImagemBinaria;

/**
 * Classe responsavel por instanciar a interface que apresenta
 * a imagem binarizada
 * 
 * @author fknappe
 *
 */
public class JanelaImagemBinaria extends javax.swing.JInternalFrame {
	
	// Declaracao das variaveis de instacia
	private static int openFrameCount = 0;
	private static final int xOffset = 175, yOffset = 5;
	
	/**
	 * Construtor 
	 * 
	 * @param PainelImagemBinaria p
	 */
	public JanelaImagemBinaria(PainelImagemBinaria p, short limiar) {
		super("[Binarizacao#" + (++openFrameCount) + "]", 
				true, 
				true, 
				true,
				false
		);
		this.panelImagemBinaria = p;
		this.panelImagemBinaria.setLimiar(limiar);
		initComponents();
	}
	
	/**
	 * Metodo responsavel pela inicializacao dos componentes da GUI
	 * 
	 * @param int numeroLinhas
	 * @param int numeroColunas
	 * @param short l
	 * 
	 * @return void
	 */
	public void initComponents() {
		getContentPane().add(panelImagemBinaria);
		
		// Define o dimensionamento do painel
		this.setVisible(true);
		setResizable(false);
		setMaximizable(false);
		setDefaultCloseOperation(JInternalFrame.DO_NOTHING_ON_CLOSE);
		setLocation(xOffset * openFrameCount, yOffset * openFrameCount);
		
		pack();
	}
	
	// Declaracao dos componentes da GUI
	private PainelImagemBinaria panelImagemBinaria;

	// Metodos getters e setters
	public PainelImagemBinaria getPainelImagemBinaria() {
		return panelImagemBinaria;
	}
}
