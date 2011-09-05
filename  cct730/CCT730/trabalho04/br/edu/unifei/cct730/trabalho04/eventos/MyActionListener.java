package br.edu.unifei.cct730.trabalho04.eventos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.edu.unifei.cct730.trabalho04.padroes.Command;

/**
 * Classe responsavel por registrar todos os eventos
 * da GUI
 * 
 * @author fknappe
 *
 */
public class MyActionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		Command cmd = (Command)e.getSource();
		cmd.executar();
	}
}
