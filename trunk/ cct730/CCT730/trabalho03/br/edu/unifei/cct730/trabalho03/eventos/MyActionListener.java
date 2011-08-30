package br.edu.unifei.cct730.trabalho03.eventos;

import java.awt.event.ActionEvent;

import br.edu.unifei.cct730.trabalho04.padroes.Command;

/**
 * Classe respons�vel por definir a forma de resposta das a��es
 * de intera��o da GUI com o restante do sistema
 * 
 * @author fknappe
 *
 */
public class MyActionListener implements java.awt.event.ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		Command com = (Command)e.getSource();
		com.executar();
	}
}
