package br.edu.unifei.cct730.trabalho04.padroes;

import java.beans.PropertyVetoException;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;

/**
 * Classe abstrata responsavel por intermediar as ações
 * da GUI com o restante da aplicacao
 * 
 * @author fknappe
 *
 */
public abstract class Mediator {
	
	//Declaracao das variaveis de instancia
	private javax.swing.JFrame frame = null;
	
	/**
	 * Construtor
	 * @param JFrame j
	 */
	public Mediator(JFrame j) {
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
	public void abrirArquivo(){}
	public void binarizar(){}
	public void histograma(){}
	public void zoom(){}
	public void sair(){}
}


