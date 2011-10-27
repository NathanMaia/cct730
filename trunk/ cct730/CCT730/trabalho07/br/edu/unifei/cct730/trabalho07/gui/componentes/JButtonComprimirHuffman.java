package br.edu.unifei.cct730.trabalho07.gui.componentes;

import br.edu.unifei.cct730.trabalho07.padroes.Command;
import br.edu.unifei.cct730.trabalho07.padroes.Controlador;

public class JButtonComprimirHuffman extends javax.swing.JButton implements Command {

	// Declaracao das variaveis de instancia
	private Controlador med;
	
	/**
	 * Construtor
	 * 
	 * @param String title
	 * @param Controlador m
	 */
	public JButtonComprimirHuffman(String title, Controlador m) {
		super(title);
		this.med = m;
	}
	
	@Override
	public void executar() {
		this.med.comprimirHuffman();
	}
}
