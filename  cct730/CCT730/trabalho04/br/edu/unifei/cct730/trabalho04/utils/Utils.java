package br.edu.unifei.cct730.trabalho04.utils;

import java.awt.Component;
import java.awt.DefaultKeyboardFocusManager;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class Utils {

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
