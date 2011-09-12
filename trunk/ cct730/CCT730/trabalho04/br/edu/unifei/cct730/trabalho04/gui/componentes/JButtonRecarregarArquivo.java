package br.edu.unifei.cct730.trabalho04.gui.componentes;

import br.edu.unifei.cct730.trabalho04.padroes.Command;
import br.edu.unifei.cct730.trabalho04.padroes.Controlador;

/**
 * Metodo responsavel pela implementacao do botao responsavel pela
 * recarregar o arquivo de imagem
 * 
 * @author fknappe
 *
 */
public class JButtonRecarregarArquivo extends javax.swing.JButton implements Command {

	// Declaracao das variaveis de instancia
	private Controlador med = null;
	
	/**
	 * Construtor 
	 * 
	 * @param String title
	 * @param Controlador m
	 * 
	 */
	public JButtonRecarregarArquivo(String title, Controlador m) {
		super(title);
		this.med = m;
	}

	@Override
	public void executar() {
		this.med.recarregarArquivo();
	}
}
