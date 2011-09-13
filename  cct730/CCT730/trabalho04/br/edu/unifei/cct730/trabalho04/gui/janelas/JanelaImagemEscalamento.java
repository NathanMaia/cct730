package br.edu.unifei.cct730.trabalho04.gui.janelas;

import javax.swing.JInternalFrame;

import br.edu.unifei.cct730.trabalho04.gui.painel.PainelImagem;

/**
 * 
 * @author fknappe
 *
 */
public class JanelaImagemEscalamento extends javax.swing.JInternalFrame {

	// Declaracao das variaveis de instacia
	private static int openFrameCount = 0;
	private static final int xOffset = 175, yOffset = 5;
	
	/**
	 * Construtor
	 * 
	 * @param PainelImagem p
	 * 
	 */
	public JanelaImagemEscalamento(PainelImagem p) {
		super("[Imagem#" + (++openFrameCount) + "]", 
				true, 
				true, 
				true,
				false
		);
		
		this.painelImagemEscalonada = p;
		this.initComponents();
	}
	
	/**
	 * Metodo responsavel pela instanciacao e inicializacao
	 * dos componentes da GUI
	 * 
	 * @return void
	 */
	public void initComponents() {
		getContentPane().add(painelImagemEscalonada);
		
		// Define o dimensionamento do painel
		this.setVisible(true);
		setResizable(false);
		setMaximizable(false);
		setClosable(true);
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		setLocation(xOffset * openFrameCount, yOffset * openFrameCount);
		
		pack();
	}
	
	// Declaracao das componentes da GUI
	private PainelImagem painelImagemEscalonada;

	// Metodos getters e setters
	public PainelImagem getPainelImagemEscalonada() {
		return painelImagemEscalonada;
	}
}
