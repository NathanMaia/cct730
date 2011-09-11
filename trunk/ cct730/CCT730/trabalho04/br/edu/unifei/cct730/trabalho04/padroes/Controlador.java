package br.edu.unifei.cct730.trabalho04.padroes;

import java.beans.PropertyVetoException;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import br.unifei.edu.cct730.trabalho04.principal.gui.JanelaPrincipal;

/**
 * Classe abstrata que implementa o pattern Mediator
 * 
 * @author fknappe
 *
 */
public abstract class Controlador {
	
	//Declaracao das variaveis de instancia
	protected JanelaPrincipal frame = null;
	
	/**
	 * Construtor
	 * @param JFrame j
	 */
	public Controlador(JanelaPrincipal j) {
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
			frame.getDesktop().add(j);
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
	public void abrirArquivo(){}
	public void binarizar(){}
	public void histograma(){}
	public void equalizacao(){}
	public void zoom(){}
	public void sobre(){}
	public void sair(){}
}


