package br.edu.unifei.cct730.trabalho03.padroes;

import java.beans.PropertyVetoException;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;

/**
 * Classe abstrata que implementa o comportamento
 * do pattern Mediator
 * 
 * @author fknappe
 *
 */
public abstract class Controlador {
	
	//Declaracao das variaveis de instancia
	private javax.swing.JFrame frame = null;
	
	/**
	 * Construtor
	 * @param JFrame j
	 */
	public Controlador(JFrame j) {
		this.frame = j;
	}
	
	/**
	 * Metodo responsavel por posicionar a janela interna dentro do desktop
	 * @param JInternalFrame j
	 * @return void
	 */
	public void lancarFrame(JInternalFrame j) {
		if(this.frame != null) {
			j.setLocation((frame.getWidth() / 2) - (j.getWidth() / 2), (frame.getHeight() / 2) - (j.getHeight() / 2) - 20);
			frame.getLayeredPane().add(j);
			j.setVisible(true);
			j.toFront();
			
			try {
				j.setSelected(true);
			} catch (PropertyVetoException e) {
				e.printStackTrace();
			}
		}
	}
	
	public abstract void registraEventos();
	public void escalamento(){}
	public void rotacao(){}
	public void translacao(){}
	public void espelhamentoHorizontal(){}
	public void espelhamentoVertical(){}
	public void abrirPanel(){}
	public void sobre(){}
	public void sair(){}
}


