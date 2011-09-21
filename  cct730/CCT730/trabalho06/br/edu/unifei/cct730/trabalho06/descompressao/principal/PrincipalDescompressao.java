package br.edu.unifei.cct730.trabalho06.descompressao.principal;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.UIManager;

import br.edu.unifei.cct730.trabalho06.descompressao.gui.JanelaDescompressao;

/**
 * Classe responsavel pelas acoes de execucao da aplicacao
 * @author fknappe
 *
 */
public class PrincipalDescompressao {

	/**
	 * Metodo responsavel pela execucao da aplicacao
	 * @param String args
	 */
	public static void main(String args[]) {
		
		/* Definindo todas as mensagens da aplicacao localizadas para pt-BR */
		UIManager.put("OptionPane.yesButtonText", "Sim");  
        UIManager.put("OptionPane.cancelButtonText", "Cancelar");  
        UIManager.put("OptionPane.noButtonText", "Não");  
        UIManager.put("OptionPane.okButtonText", "OK");
		
		final JanelaDescompressao janela = new JanelaDescompressao("Processamento de Imagens - Descompressao de Imagens Sinteticas");
		//Tratamento da ação de fechar a janela do aplicativo
		janela.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				janela.dispose();
				System.exit(0);
			}
		});
	}
}
