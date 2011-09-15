package br.edu.unifei.cct730.trabalho05.gui.janelas;

import javax.swing.JInternalFrame;

import br.edu.unifei.cct730.trabalho05.gui.painel.PainelImagem;

public class JanelaImagemFiltrada extends javax.swing.JInternalFrame {
	
	// Declaracao das variaveis de instacia
	private static int openFrameCount = 0;
	private static final int xOffset = 175, yOffset = 5;
	
	/**
	 * Construtor 
	 * 
	 * @param PainelImagem p
	 */
	public JanelaImagemFiltrada(PainelImagem p) {
		super("[Ruido#" + (++openFrameCount) + "]", 
				true, 
				true, 
				true,
				false
		);
		
		this.painelImagemDigitalizada = p;
		this.initComponents();
	}
	
	/**
	 * Metodo responsavel pela instanciacao e inicializacao
	 * dos componentes da GUI
	 * 
	 * @return void
	 */
	public void initComponents() {
		getContentPane().add(painelImagemDigitalizada);
		
		// Define o dimensionamento do painel
		this.setVisible(true);
		setResizable(false);
		setMaximizable(false);
		setDefaultCloseOperation(JInternalFrame.DO_NOTHING_ON_CLOSE);
		setLocation(xOffset * openFrameCount, yOffset * openFrameCount);
		
		pack();
	}
	
	// Declaracao das componentes da GUI
	private PainelImagem painelImagemDigitalizada;

	// Metodos getters e setters
	public PainelImagem getPainelImagemDigitalizada() {
		return painelImagemDigitalizada;
	}

}
