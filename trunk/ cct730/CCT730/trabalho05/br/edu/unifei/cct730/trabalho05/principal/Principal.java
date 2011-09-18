package br.edu.unifei.cct730.trabalho05.principal;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.UIManager;

import br.edu.unifei.cct730.trabalho05.gui.principal.JanelaPrincipal;

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
		/* 
		 * Definindo todas as mensagens da aplicacao localizadas para pt-BR 
		 */
		UIManager.put("OptionPane.yesButtonText", "Sim");  
        UIManager.put("OptionPane.cancelButtonText", "Cancelar");  
        UIManager.put("OptionPane.noButtonText", "Não");  
        UIManager.put("OptionPane.okButtonText", "OK");
        
        UIManager.put("FileChooser.cancelButtonText", "Cancelar");
        UIManager.put("FileChooser.openButtonText", "Abrir");
        UIManager.put("FileChooser.openButtonToolTipText", "Abrir arquivo selecionado");
        UIManager.put("FileChooser.saveButtonText", "Salvar");
        UIManager.put("FileChooser.saveButtonToolTipText", "Salvar arquivo selecionado");
		UIManager.put("FileChooser.newFolderAccessibleNam", "Arquivo novo");
		UIManager.put("FileChooser.fileNameLabelText", "Arquivo:");
		UIManager.put("FileChooser.upFolderAccessibleName", "Acima");
		UIManager.put("FileChooser.upFolderToolTipText", "Acima um nível"); 
		UIManager.put("FileChooser.homeFolderAccessibleName", "Inicio");
		UIManager.put("FileChooser.filesOfTypeLabelText", "Formato de arquivo:");
		UIManager.put("FileChooser.acceptAllFileFilterText", "*.*");
		UIManager.put("FileChooser.lookInLabelText", "Visualizar");
		
		final JanelaPrincipal janela = new JanelaPrincipal("Processamento de Imagens - Filtros");
		//Tratamento da ação de fechar a janela do aplicativo
		janela.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				janela.dispose();
				System.exit(0);
			}
		});
	}
}
