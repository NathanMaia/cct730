package br.edu.unifei.cct730.trabalho04.gui.janelas;

import br.edu.unifei.cct730.trabalho04.gui.painel.PainelImagem;

/**
 * Classe responsavel por inicializar a janela que apresenta 
 * a imagem equalizada
 * 
 * @author fknappe
 *
 */
public class JanelaImagemEqualizada extends javax.swing.JInternalFrame {
	
	// Declaracao das variaveis de instacia
	private static int openFrameCount = 0;
	private static final int xOffset = 15, yOffset = 50;
	
	/**
	 * Construtor
	 * 
	 * @param String title
	 * 
	 */
	public JanelaImagemEqualizada(PainelImagem p) {
		super("[Equalizacao#" + (++openFrameCount) + "]", 
				true, 
				true, 
				true,
				false
		);
		
		this.painelImagemEqualizada = p;
		this.initComponents();
	}
	
	/**
	 * Metodo responsavel pela inicializacao e disposicao 
	 * dos componentes da GUI
	 * 
	 * @return void
	 */
	public void initComponents() {
		getContentPane().add(painelImagemEqualizada);
		
		// Define o dimensionamento do painel
		this.setVisible(true);
		setResizable(false);
		setMaximizable(false);
		setLocation(xOffset * openFrameCount, yOffset * openFrameCount);
		
		pack();
	}
	
	// Declaracao das componentes da GUI
	private PainelImagem painelImagemEqualizada;

	// Metodos getters e setters
	public PainelImagem getPainelImagemEqualizada() {
		return painelImagemEqualizada;
	}
}
