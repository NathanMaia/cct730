package br.edu.unifei.cct730.trabalho03.gui.componentes;

import br.edu.unifei.cct730.trabalho03.padroes.Command;
import br.edu.unifei.cct730.trabalho03.padroes.Mediator;

/**
 * Classe responsavel por instanciar um botao encarregado pelas acoes do editor
 * de imagens sinteticas
 * 
 * @author fknappe
 *
 */
public class JButtonAbrirPanel extends javax.swing.JButton implements Command {

	//Declaracao das variaveis de instancia
	private Mediator med = null;
	
	/**
	 * Construtor
	 * 
	 * @param String title
	 * @param Meditor m
	 */
	public JButtonAbrirPanel(String title, Mediator m) {
		this.med = m;
		this.setText(title);
	}

	@Override
	public void executar() {
		this.med.abrirPanel();
	}
}
