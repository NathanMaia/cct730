package br.edu.unifei.cct730.trabalho06.gui.componentes;

import br.edu.unifei.cct730.trabalho06.padroes.Command;
import br.edu.unifei.cct730.trabalho06.padroes.Controlador;

/**
 * Classe responsavel por instanciar um botao encarregado pelas acoes de comprimir
 * as imagens sinteticas
 * 
 * @author fknappe
 *
 */
public class JButtonComprimirImagem extends javax.swing.JButton implements Command {

	// Declaracao das variaveis de instancia
	private Controlador med;
	
	/**
	 * Construtor 
	 * 
	 * @param String title
	 * @param Controlador m
	 */
	public JButtonComprimirImagem(String title, Controlador m) {
		super(title);
		this.med = m;
	}
	
	@Override
	public void executar() {
		this.med.comprimirImagem();
	}
}
