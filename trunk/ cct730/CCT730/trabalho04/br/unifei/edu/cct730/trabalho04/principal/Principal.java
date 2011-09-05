package br.unifei.edu.cct730.trabalho04.principal;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import br.unifei.edu.cct730.trabalho04.principal.gui.JanelaPrincipal;

/**
 * Classe principal do sistema, responsavel por sua execucao
 * 
 * @author fknappe
 *
 */
public class Principal {
	
	/**
	 * Metodo responsavel pela execucao do aplicativo
	 * 
	 * @param String args
	 */
	public static void main(String args[]) {
		final JanelaPrincipal janela = new JanelaPrincipal("Processamento de Imagens - Histograma");
		//Tratamento da ação de fechar a janela do aplicativo
		janela.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				janela.dispose();
				System.exit(0);
			}
		});
	}

}
