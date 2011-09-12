package br.edu.unifei.cct730.trabalho04.gui.janelas;

import java.awt.Dimension;

import br.edu.unifei.cct730.trabalho04.gui.painel.PainelImagemBinaria;
import br.edu.unifei.cct730.trabalho04.utils.histograma.Descritor;
import br.edu.unifei.cct730.trabalho04.utils.histograma.Histograma;

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
	private static final int xOffset = 15, yOffset = 30;
	
	/**
	 * Construtor 
	 * 
	 * @param PainelImagemBinaria p
	 */
	public JanelaImagemBinaria(PainelImagemBinaria p) {
		super("[Imagem#" + (++openFrameCount) + "]", 
				true, 
				true, 
				true,
				false
		);
		this.panelImagemBinaria = p;
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
		if (panelImagemBinaria == null)
			panelImagemBinaria = new PainelImagemBinaria();
		getContentPane().add(panelImagemBinaria);
		
		// Define o dimensionamento do painel
		this.setVisible(true);
		setResizable(false);
		setMaximizable(false);
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
