package br.edu.unifei.cct730.trabalho05.gui.painel;

import java.util.List;
import java.util.Map;

import br.edu.unifei.cct730.trabalho05.model.imagem.ImagemFiltrada;
import br.edu.unifei.cct730.trabalho05.model.ponto.Ponto;

/**
 * Classe responsavel por implementar um painel
 * que contem uma imagem filtrada
 * 
 * @author fknappe
 *
 */
public class PainelImagemFiltrada extends PainelImagem {
	
	/**
	 * Construtor 
	 * 
	 * @param ImagemFiltrada im
	 */
	public PainelImagemFiltrada(ImagemFiltrada im) {
		super(im);
	}

	@Override
	public void constroiImagem(Map<Short, List<Ponto>> tabelaPontos) {
		for (Map.Entry<Short, List<Ponto>> entrada: tabelaPontos.entrySet()) {
			for (Ponto ponto: entrada.getValue()) {
				this.setPosicao(ponto.getX(), ponto.getY(), ponto.getCor());
			}
		}
	}
}
