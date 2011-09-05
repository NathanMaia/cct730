package br.edu.unifei.cct730.trabalho04.gui.componentes;

import br.edu.unifei.cct730.trabalho04.padroes.Command;
import br.edu.unifei.cct730.trabalho04.padroes.Mediator;

/**
 * Classe responsavel por implementar o botao responsavel pela abertura
 * do arquivo da imagem
 * 
 * @author fknappe
 *
 */
public class JButtonAbrirArquivo extends javax.swing.JButton implements Command{
	
	//Declaracao das variaveis de instancia
	private Mediator med = null;
	
	/**
	 * Construtor
	 * @param String title
	 * @param Mediator m
	 */
	public JButtonAbrirArquivo(String title, Mediator m) {
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
		this.med.abrirArquivo();
	}
}
