package br.edu.unifei.cct730.trabalho08.gui.componentes;

import br.edu.unifei.cct730.trabalho08.padroes.Command;
import br.edu.unifei.cct730.trabalho08.padroes.Controlador;

public class JButtonAplicarMorfologico extends javax.swing.JButton implements Command {

	// Declaracao das variaveis de instancia
	private Controlador med;
	
	/**
	 * Controlador
	 * 
	 * @param String title
	 * @param Controlador m
	 */
	public JButtonAplicarMorfologico(String title, Controlador m) {
		super(title);
		this.med = m;
	}
	
	@Override
	public void executar() {
		this.med.aplicarMorfologico();
	}
}
