package br.edu.unifei.cct730.trabalho05.gui.painel;

import java.util.List;
import java.util.Map;

import br.edu.unifei.cct730.trabalho05.model.imagem.ImagemDigitalizada;
import br.edu.unifei.cct730.trabalho05.model.ponto.Ponto;

/**
 * Classe responsavel por implementar um painel
 * que contem uma imagem digitalizada
 * 
 * @author fknappe
 *
 */
public class PainelImagemDigitalizada extends PainelImagem {

	/**
	 * Construtor 
	 * 
	 * @param ImagemDigitalizada im
	 */
	public PainelImagemDigitalizada(ImagemDigitalizada im) {
		super(im);
	}
	
	/**
	 * Metodo responsavel por construir a imagem digitalizada
	 * 
	 * @param Map<Short, List<Ponto>> tabelaPontos
	 * 
	 * @return void
	 */
	public void constroiImagem(Map<Short, List<Ponto>> tabelaPontos) {
		for (Map.Entry<Short, List<Ponto>> entrada: tabelaPontos.entrySet()) {
			for (Ponto ponto: entrada.getValue()) {
				this.setPosicao(ponto.getX(), ponto.getY(), ponto.getCor());
			}
		}
	}
}
