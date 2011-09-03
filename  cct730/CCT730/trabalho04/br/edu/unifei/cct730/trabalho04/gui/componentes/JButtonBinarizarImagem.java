package br.edu.unifei.cct730.trabalho04.gui.componentes;

import br.edu.unifei.cct730.trabalho04.padroes.Command;
import br.edu.unifei.cct730.trabalho04.padroes.Mediator;

public class JButtonBinarizarImagem extends javax.swing.JButton implements Command {

	Mediator med = null;
	
	public JButtonBinarizarImagem(String title, Mediator m) {
		this.med = m;
		this.setText(title);
	}
	
	@Override
	public void executar() {
		this.med.binarizar();
	}
}
