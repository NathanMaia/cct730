package br.edu.unifei.cct730.trabalho03.gui.componentes;

import br.edu.unifei.cct730.trabalho03.padroes.Command;
import br.edu.unifei.cct730.trabalho03.padroes.Controlador;

/**
 * Classe responsavel por instanciar um botao encarregado das acoes da operacao de 
 * espelhamento vertical
 * 
 * @author fknappe
 *
 */
public class JButtonEspelhamentoVertical extends javax.swing.JButton implements Command {
	
	//Declaracao das variaveis de instacia
	private Controlador med;
	
	/**
	 * Construtor
	 * 
	 * @param String title
	 * @param Controlador m
	 */
	public JButtonEspelhamentoVertical(String title, Controlador m) {
		this.med = m;
		this.setText(title);
	}

	@Override
	public void executar() {
		this.med.espelhamentoVertical();
	}
}
