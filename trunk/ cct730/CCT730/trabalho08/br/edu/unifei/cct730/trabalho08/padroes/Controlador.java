package br.edu.unifei.cct730.trabalho08.padroes;

import java.beans.PropertyVetoException;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import br.edu.unifei.cct730.trabalho08.principal.gui.JanelaPrincipal;
import br.edu.unifei.cct730.trabalho08.utils.Mensagem;

/**
 * Classe abstrata que implementa o comportamento
 * do pattern Mediator
 * 
 * @author fknappe
 *
 */
public abstract class Controlador {
	
	//Declaracao das variaveis de instancia
	protected javax.swing.JFrame frame = null;
	
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
	
	/**
	 * Metodo responsavel por posicionar a janela imagem dentro do desktop
	 * 
	 * @param JInternalFrame j
	 * 
	 * @return void
	 */
	public void lancarJanelaImagem(JInternalFrame j) {
		if(this.frame != null) {
		  ((JanelaPrincipal)frame).getDesktop().add(j);
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
	public void abrirPanel(){}
	public void comprimirHuffman(){}
	public void descomprimirHuffman(){}
	
	/**
	 * Metodo responsavel por tratar as acoes
	 * do botao sobre
	 * 
	 * @return void
	 */
	public void sobre() {
		Mensagem.mostraMensagemSobre(
				this.frame,
				"Universidade Federal de Itajuba\n" + 
				"PDI - Processamento de Imagens \n" +
				"Professor: Dr. Edison Oliveira de Jesus\n" +
				"Aluno: Felipe Agostini Knappe - 12623"
		);
	}
	
	/**
	 * Metodo responsavel por tratar as acoes
	 * do botao sair
	 * 
	 * @return void
	 */
	public void sair() {
		if(Mensagem.confirmaMensagem(
				this.frame, 
				"Deseja realmente sair do programa?")
			) {
			this.frame.dispose();
			System.exit(0);
		}
	}
}
