package br.edu.unifei.cct730.trabalho03.utils;

import java.awt.Component;
import java.awt.DefaultKeyboardFocusManager;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 * Classe responsavel por apresentar as mensagens da aplicacao ao usuario
 * @author fknappe
 *
 */
public class Mensagem {

	/**
	 * Metodo responsavel por apresentar uma mensagem de confirmacao ao usuario
	 * 
	 * @param Component componente
	 * @param String mensagem
	 * @return boolean
	 */
	public static boolean confirmaMensagem(Component componente, String mensagem) {
		final JOptionPane j = new JOptionPane(mensagem, JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION, null);
		j.setComponentOrientation(((componente == null) ?
				JOptionPane.getRootFrame() : componente).getComponentOrientation());
		final Component componenteFoco = DefaultKeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner();
		JDialog dialog = j.createDialog(componente, "Confirmar");
		dialog.setVisible(true);
		dialog.addWindowListener(new WindowAdapter() {

			public void windowDeactivated(WindowEvent e) {
				if(componenteFoco != null && (j.getValue().equals(JOptionPane.CLOSED_OPTION) || ((Integer)j.getValue()).equals(JOptionPane.NO_OPTION))) {
					componenteFoco.requestFocus();
				}
			}

		});
		return j.getValue().equals(JOptionPane.YES_OPTION);
	}
	
	/**
	 * Metodo responsavel por apresentar as mensagens de erro ao usuario
	 * 
	 * @param Component componente
	 * @param String erro
	 */
	public static void mostraErro(final Component componente, String erro) {
		JOptionPane j = new JOptionPane(erro, JOptionPane.ERROR_MESSAGE);
		j.setComponentOrientation(((componente == null) ?
				JOptionPane.getRootFrame() : componente).getComponentOrientation());

		final Component componenteFoco = DefaultKeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner();
		JDialog dialog = j.createDialog(componente, "ERRO!");
		dialog.addWindowListener(new WindowAdapter() {

			@Override
			public void windowDeactivated(WindowEvent e) {
				if(componenteFoco != null) {
					componenteFoco.requestFocus();
				}
			}

		});
		dialog.setVisible(true);
	}
	
	/**
	 * Metodo responsavel por apresentar as mensagens para entrada de dados do usuario
	 * 
	 * @param String mensagem
	 * @return int
	 * @throws NumberFormatException
	 */
	public static int entradaDeDados(String mensagem) throws NumberFormatException {
		String valor = JOptionPane.showInputDialog(
				null, 
				mensagem, 
				JOptionPane.INFORMATION_MESSAGE
		);
		int vl = 0;
		if(valor != null) {
			vl = Integer.parseInt(valor);
		}
		return vl;
	}
}
