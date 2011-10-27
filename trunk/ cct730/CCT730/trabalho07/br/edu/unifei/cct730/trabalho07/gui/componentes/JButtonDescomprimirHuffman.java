package br.edu.unifei.cct730.trabalho07.gui.componentes;

import br.edu.unifei.cct730.trabalho07.padroes.Command;
import br.edu.unifei.cct730.trabalho07.padroes.Controlador;

/**
 * Classe responsavel por instanciar um botao encarregado pelas acoes do descomprimir
 * as imagens sinteticas
 * 
 * @author fknappe
 *
 */
public class JButtonDescomprimirHuffman extends javax.swing.JButton implements Command {

	// Declaracao de variaveis de instancia
	private Controlador med;
	
	/**
	 * Construtor 
	 * 
	 * @param String title
	 * @param Controlador m
	 */
	public JButtonDescomprimirHuffman(String title, Controlador m) {
		super(title);
		this.med = m;
	}
	
	@Override
	public void executar() {
		this.med.descomprimirHuffman();
	}

}
