package br.edu.unifei.cct730.trabalho04.gui.componentes;

import br.edu.unifei.cct730.trabalho04.padroes.Command;
import br.edu.unifei.cct730.trabalho04.padroes.Controlador;

public class JButtonEqualizacao extends javax.swing.JButton implements Command {
	
	private Controlador med;
	
	public JButtonEqualizacao(String title, Controlador m) {
		super(title);
		this.med = m;
	}

	@Override
	public void executar() {
		this.med.equalizacao();
	}
}
