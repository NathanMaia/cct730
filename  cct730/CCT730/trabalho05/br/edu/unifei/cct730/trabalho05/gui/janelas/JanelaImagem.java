package br.edu.unifei.cct730.trabalho05.gui.janelas;

import javax.swing.JInternalFrame;

import br.edu.unifei.cct730.trabalho05.gui.painel.PainelImagem;

/**
 * Classe abstrata responsavel por instanciar
 * a janela que ira conter uma imagem
 * 
 * @author fknappe
 *
 */
public abstract class JanelaImagem extends javax.swing.JInternalFrame {

	// Constantes
	public static final int IMAGEM_DIGITALIZADA = 0;
	public static final int IMAGEM_FILTRADA = 1;
	
	// Declaracao das componentes da GUI
	protected PainelImagem painelImagem;
	
	/**
	 * Construtor 
	 * 
	 * @param PainelImagem p
	 */
	public JanelaImagem(
			String title,
			boolean resizable,
			boolean closable,
			boolean maximizable,
			boolean iconifiable,
			PainelImagem p) {
		super(title, 
			  resizable, 
			  closable, 
			  maximizable,
			  iconifiable
		);
		
		this.painelImagem = p;
		this.initComponents();
	}
	
	/**
	 * Metodo responsavel pela instanciacao e inicializacao
	 * dos componentes da GUI
	 * 
	 * @return void
	 */
	public void initComponents() {
		getContentPane().add(painelImagem);
		
		// Define o dimensionamento do painel
		this.setVisible(true);
		setResizable(false);
		setMaximizable(false);
		setDefaultCloseOperation(JInternalFrame.DO_NOTHING_ON_CLOSE);
		
		pack();
	}
	
	// Metodos getters e setters
	public PainelImagem getPainelImagem() {
		return painelImagem;
	}
}
