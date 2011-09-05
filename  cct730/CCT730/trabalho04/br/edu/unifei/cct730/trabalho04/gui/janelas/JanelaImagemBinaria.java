package br.edu.unifei.cct730.trabalho04.gui.janelas;

import java.awt.Dimension;

import br.edu.unifei.cct730.trabalho04.gui.painel.PainelImagemBinaria;
import br.edu.unifei.cct730.trabalho04.utils.Descritor;
import br.edu.unifei.cct730.trabalho04.utils.Histograma;

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
	 * @param int numeroLinhas
	 * @param int numeroColunas
	 * @param short l
	 * 
	 */
	public JanelaImagemBinaria(
			Descritor d, 
			short l
		) {
		super("[Imagem#" + (++openFrameCount) + "]", 
				true, 
				true, 
				true,
				false
		);

		initComponents(d, l);
	}
	
	/**
	 * Construtor 
	 * 
	 * @param PainelImagemBinaria p
	 */
	public JanelaImagemBinaria(PainelImagemBinaria p) {
		this.panelImagem = p;
		initComponents(null, new Integer(0).shortValue());
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
	public void initComponents(Descritor d, short l) {
		if (panelImagem == null)
			panelImagem = new PainelImagemBinaria(d, l);
		getContentPane().add(panelImagem);
		
		// Define o dimensionamento do painel
		this.setVisible(true);
		setResizable(false);
		setMaximizable(false);
		setLocation(xOffset * openFrameCount, yOffset * openFrameCount);
		
		pack();
	}
	
	// Declaracao dos componentes da GUI
	private PainelImagemBinaria panelImagem;

	// Metodos getters e setters
	public PainelImagemBinaria getPainelImagem() {
		return panelImagem;
	}
}
