package br.edu.unifei.cct730.trabalho04.gui.componentes;

import br.edu.unifei.cct730.trabalho04.padroes.Command;
import br.edu.unifei.cct730.trabalho04.padroes.Mediator;

public class JButtonHistograma extends javax.swing.JButton implements Command {

	private Mediator med = null;
	
	public JButtonHistograma(String title, Mediator m) {
		this.med = m;
		this.setText(title);
	}
	
	@Override
	public void executar() {
		this.med.histograma();
	}
}
