package br.edu.unifei.cct730.trabalho05.padroes;

import java.beans.PropertyVetoException;

import javax.swing.JInternalFrame;

import br.edu.unifei.cct730.trabalho05.gui.principal.JanelaPrincipal;

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
	 * 
	 * @param JInternalFrame j
	 * 
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
	
	/**
	 * Metodo responsavel por posicionar a janela imagem dentro do desktop
	 * 
	 * @param JInternalFrame j
	 * 
	 * @return void
	 */
	public void lancarJanelaImagem(JInternalFrame j) {
		if(this.frame != null) {
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
	public void recarregarArquivo(){}
	public void filtrar(){}
	public void sobre(){}
	public void sair(){}
}


