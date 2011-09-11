package br.edu.unifei.cct730.trabalho04.gui.componentes;

public class MyFileChooser extends javax.swing.JFileChooser {

	private String caminhoArquivo = "";
	
	public MyFileChooser(String title) {
		this.setDialogTitle("Abrir arquivo");
	}
}
