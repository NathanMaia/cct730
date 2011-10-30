package br.edu.unifei.cct730.trabalho08.eventos;

import java.awt.event.ActionEvent;

import br.edu.unifei.cct730.trabalho08.padroes.Command;

/**
 * Classe responsavel por definir a forma de resposta das acoes
 * de interacao da GUI com o restante do sistema
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
