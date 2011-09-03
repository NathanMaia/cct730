package br.edu.unifei.cct730.trabalho04.gui.painel;

import br.edu.unifei.cct730.trabalho04.utils.Descritor;
import br.edu.unifei.cct730.trabalho04.utils.Histograma;

public class PainelImagemBinaria extends javax.swing.JPanel {
	
	private Descritor descritor = null;
	private Histograma histograma = null;
	private short limiar = 0;
	
	public PainelImagemBinaria(Descritor d, Histograma h, short l) {
		super();
		this.descritor = d;
		this.histograma = h;
		this.limiar = l;
	}
}
