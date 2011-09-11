package br.edu.unifei.cct730.trabalho03.gui.componentes;

import br.edu.unifei.cct730.trabalho03.padroes.Command;
import br.edu.unifei.cct730.trabalho03.padroes.Controlador;

/**
 * Classe responsavel por instanciar um botao encarregado pelas acoes de 
 * espelhamento horizontal
 * 
 * @author fknappe
 *
 */
public class JButtonEspelhamentoHorizontal extends javax.swing.JButton implements Command {
	
	//Declaracao das variaveis de instancia
	private Controlador med = null;
	
	/**
	 * Construtor
	 * @param String title
	 * @param Controlador m
	 */
	public JButtonEspelhamentoHorizontal(String title, Controlador m) {
		this.med = m;
		this.setText(title);
	}

	@Override
	public void executar() {
		this.med.espelhamentoHorizontal();
	}
}
