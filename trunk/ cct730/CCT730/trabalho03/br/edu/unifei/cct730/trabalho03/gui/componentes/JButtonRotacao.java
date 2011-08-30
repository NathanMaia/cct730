package br.edu.unifei.cct730.trabalho03.gui.componentes;

import br.edu.unifei.cct730.trabalho03.padroes.Command;
import br.edu.unifei.cct730.trabalho03.padroes.Mediator;

/**
 * Classe responsavel por instanciar um botao que realiza a operacao de rotacao
 * @author fknappe
 *
 */
public class JButtonRotacao extends javax.swing.JButton implements Command {

	//Declaracao das variaveis de inst�ncia
	private Mediator med = null;
	
	/**
	 * Construtor
	 * @param title
	 * @param m
	 */
	public JButtonRotacao(String title, Mediator m) {
		this.med = m;
		this.setText(title);
	}
	
	@Override
	public void executar() {
		this.med.rotacao();
	}
}
