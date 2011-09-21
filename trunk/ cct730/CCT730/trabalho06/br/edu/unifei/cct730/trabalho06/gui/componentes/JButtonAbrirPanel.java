package br.edu.unifei.cct730.trabalho06.gui.componentes;

import br.edu.unifei.cct730.trabalho06.padroes.Command;
import br.edu.unifei.cct730.trabalho06.padroes.Controlador;

/**
 * Classe responsavel por instanciar um botao encarregado pelas acoes do editor
 * de imagens sinteticas
 * 
 * @author fknappe
 *
 */
public class JButtonAbrirPanel extends javax.swing.JButton implements Command {

	//Declaracao das variaveis de instancia
	private Controlador med = null;
	
	/**
	 * Construtor
	 * 
	 * @param String title
	 * @param Meditor m
	 */
	public JButtonAbrirPanel(String title, Controlador m) {
		this.med = m;
		this.setText(title);
	}

	@Override
	public void executar() {
		this.med.abrirPanel();
	}
}
