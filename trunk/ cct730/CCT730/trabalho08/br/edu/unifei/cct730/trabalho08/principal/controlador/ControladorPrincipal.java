package br.edu.unifei.cct730.trabalho08.principal.controlador;

import javax.swing.JInternalFrame;

import br.edu.unifei.cct730.trabalho08.eventos.MyActionListener;
import br.edu.unifei.cct730.trabalho08.padroes.Controlador;
import br.edu.unifei.cct730.trabalho08.principal.gui.JanelaPrincipal;

/**
 * Classe responsavel por tratar as acoes
 * do usuario com o restante da aplicacao
 * 
 * @author fknappe
 *
 */
public class ControladorPrincipal extends Controlador {

	/**
	 * Construtor
	 * 
	 * @param JanelaPrincipal j
	 */
	public ControladorPrincipal(JanelaPrincipal j) {
		super(j);
	}

	@Override
	public void registraEventos() {

		MyActionListener myListener = new MyActionListener();

		((JanelaPrincipal)this.frame).getBtnAbrirPanel().addActionListener(myListener);
		((JanelaPrincipal)this.frame).getBtnAplicar().addActionListener(myListener);
		((JanelaPrincipal)this.frame).getBtnEstruturante().addActionListener(myListener);
		((JanelaPrincipal)this.frame).getBtnSobre().addActionListener(myListener);
		((JanelaPrincipal)this.frame).getBtnSair().addActionListener(myListener);
	}
	
	/**
	 * Metodo responsavel por tratar as acoes
	 * do botao de abertura das imagens 
	 * sinteticas
	 * 
	 * @return void
	 */
	public void abrirPanel() {
		
	}
	
	/**
	 * Metodo responsavel por tratar as acoes
	 * do botao de aplicacao das operacoes
	 * morfologicas
	 * 
	 * @return void
	 */
	public void aplicarMorfologico() {
		
	}
	
	/**
	 * Metodo responsavel por tratar as 
	 * acoes do botao para criacao do
	 * elemento estruturante
	 * 
	 * @return void
	 */
	public void definirEstruturante() {
		
	}

	/*
	 **********************************************************************************************************************************************************************
	 *                                                            
	 *                                                            Metodos auxiliares (todos privados)
	 *                                                            
	 **********************************************************************************************************************************************************************/                                                       	
	
	/**
	 * Metodo responsavel por desfazer as acoes anteriores
	 * na reabertura do arquivo
	 * 
	 * @return void
	 */
	private void desfazerAcoesAnteriores() {

		/*
		 * Finalizando todas as acoes anteriores
		 */
		for(JInternalFrame j : ((JanelaPrincipal)this.frame).getDesktop().getAllFrames()) {
			j.dispose();
		}

		/*
		 * Restaurando o menu para a acao inicial
		 */
		((JanelaPrincipal)this.frame).desabilitarMenu();
	}
}
