package br.edu.unifei.cct730.trabalho03.gui.componentes;

import br.edu.unifei.cct730.trabalho03.padroes.Command;
import br.edu.unifei.cct730.trabalho03.padroes.Mediator;

/**
 * Classe responsavel por instanciar um botao encarregado pelas acoes de 
 * espelhamento horizontal
 * 
 * @author fknappe
 *
 */
public class JButtonEspelhamentoHorizontal extends javax.swing.JButton implements Command {
	
	//Declaracao das variaveis de instancia
	private Mediator med = null;
	
	/**
	 * Construtor
	 * @param String title
	 * @param Mediator m
	 */
	public JButtonEspelhamentoHorizontal(String title, Mediator m) {
		this.med = m;
		this.setText(title);
	}

	@Override
	public void executar() {
		this.med.espelhamentoHorizontal();
	}
}
