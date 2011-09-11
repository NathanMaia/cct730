package br.edu.unifei.cct730.trabalho04.gui.componentes;

import br.edu.unifei.cct730.trabalho04.padroes.Command;
import br.edu.unifei.cct730.trabalho04.padroes.Controlador;

/**
 * Classe responsavel por implementar o botao responsavel pela binarizacao
 * da imagem
 * 
 * @author fknappe
 *
 */
public class JButtonBinarizarImagem extends javax.swing.JButton implements Command {

	// Declaracao das variaveis de instancia
	Controlador med = null;
	
	/**
	 * Construtor
	 * 
	 * @param String title
	 * @param Controlador m
	 */
	public JButtonBinarizarImagem(String title, Controlador m) {
		this.med = m;
		this.setText(title);
	}
	
	/**
	 * Metodo responsavel por executar a acao definida pelo listener
	 * 
	 * @return void
	 */
	@Override
	public void executar() {
		this.med.binarizar();
	}
}
