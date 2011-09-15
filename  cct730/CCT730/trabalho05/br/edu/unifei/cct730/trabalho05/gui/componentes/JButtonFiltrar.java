package br.edu.unifei.cct730.trabalho05.gui.componentes;

import br.edu.unifei.cct730.trabalho05.padroes.Command;
import br.edu.unifei.cct730.trabalho05.padroes.Controlador;

/**
 * Classe responsavel por implementar o botao responsavel por 
 * aplicar os filtros sobre a imagem
 * 
 * @author fknappe
 *
 */
public class JButtonFiltrar extends javax.swing.JButton implements Command {
	
	// Declaracao das variaveis de instancia
	private Controlador med;
	
	/**
	 * Construtor
	 * 
	 * @param String title
	 * @param Controlador m
	 */
	public JButtonFiltrar(String title, Controlador m) {
		super(title);
		this.med = m;
	}

	@Override
	public void executar() {
		this.med.filtrar();
	}
}
