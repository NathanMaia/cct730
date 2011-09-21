package br.edu.unifei.cct730.trabalho06.gui.componentes;

import br.edu.unifei.cct730.trabalho06.padroes.Command;
import br.edu.unifei.cct730.trabalho06.padroes.Controlador;

/**
 * Classe responsavel por definir a acao do botao de mostrar
 * os responsaveis pelo projeto
 * 
 * @author fknappe
 *
 */
public class JButtonSobre extends javax.swing.JButton implements Command {

	// Declaracao das variaveis de instacia
	private Controlador med;
	
	/**
	 * Construtor 
	 * 
	 * @param String title
	 * @param Controlador m
	 */
	public JButtonSobre(String title, Controlador m) {
		this.med = m;
		this.setText(title);
	}

	/**
	 * Metodo responsavel pela execucao da acao
	 * definida pelo listener
	 */
	@Override
	public void executar() {
		this.med.sobre();
	}
}
