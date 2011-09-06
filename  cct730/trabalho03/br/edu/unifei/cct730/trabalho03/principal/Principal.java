package br.edu.unifei.cct730.trabalho03.principal;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import br.edu.unifei.cct730.trabalho03.principal.gui.JanelaPrincipal;
import br.edu.unifei.cct730.trabalho03.utils.Mensagem;

/**
 * Classe responsavel pelas acoes de execucao da aplicacao
 * @author fknappe
 *
 */
public class Principal {
	
	/**
	 * Metodo responsavel pela execucao da aplicacao
	 * @param args
	 */
	public static void main(String args[]) {
		final JanelaPrincipal janela = new JanelaPrincipal("Processamento de Imagens - Transformacoes Geometricas");
		//Tratamento da acao de fechar a janela do aplicativo
		janela.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				janela.dispose();
				System.exit(0);
			}
		});
	}
}
