package br.edu.unifei.cct730.trabalho03.eventos;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import br.edu.unifei.cct730.trabalho03.gui.painel.Celula;

/**
 * Classe que define a forma de interacao do usuario
 * com o painel de desenho.
 * 
 * @author fknappe
 *
 */
public class PainelDesenhoListener extends MouseAdapter {
	
	public void mousePressed(MouseEvent e) {
		Celula painel = (Celula) e.getSource();
		painel.trocaCor();
	}
}
