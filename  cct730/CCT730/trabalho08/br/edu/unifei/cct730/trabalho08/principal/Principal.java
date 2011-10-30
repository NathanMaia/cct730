package br.edu.unifei.cct730.trabalho08.principal;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.UIManager;

import br.edu.unifei.cct730.trabalho08.principal.gui.JanelaPrincipal;

public class Principal {

	public static void main(String args[]) {

		/* Definindo todas as mensagens da aplicacao localizadas para pt-BR */
		UIManager.put("OptionPane.yesButtonText", "Sim");  
		UIManager.put("OptionPane.cancelButtonText", "Cancelar");  
		UIManager.put("OptionPane.noButtonText", "Não");  
		UIManager.put("OptionPane.okButtonText", "OK");

		final JanelaPrincipal janela = new JanelaPrincipal("Processamento de Imagens - Morfologia Matematica");
		//Tratamento da ação de fechar a janela do aplicativo
		janela.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				janela.dispose();
				System.exit(0);
			}
		});
	}
}
