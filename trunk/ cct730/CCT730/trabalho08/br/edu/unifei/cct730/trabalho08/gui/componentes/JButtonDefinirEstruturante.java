package br.edu.unifei.cct730.trabalho08.gui.componentes;

import br.edu.unifei.cct730.trabalho08.padroes.Command;
import br.edu.unifei.cct730.trabalho08.padroes.Controlador;

public class JButtonDefinirEstruturante extends javax.swing.JButton implements Command {

	// Declaracao de variaveis de instancia
	private Controlador med;
	
	/**
	 * Construtor
	 * 
	 * @param String title
	 * @param Controlador m
	 */
	public JButtonDefinirEstruturante(String title, Controlador m) {
		super(title);
		this.med = m;
	}
	
	@Override
	public void executar() {
		this.med.definirEstruturante();
	}
}
