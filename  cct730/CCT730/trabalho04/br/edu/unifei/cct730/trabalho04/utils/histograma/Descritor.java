package br.edu.unifei.cct730.trabalho04.utils.histograma;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.edu.unifei.cct730.trabalho04.ponto.Ponto;
import br.edu.unifei.cct730.trabalho04.ponto.PontoBinario;
import br.edu.unifei.cct730.trabalho04.utils.imagem.ImagemBinarizada;
import br.edu.unifei.cct730.trabalho04.utils.imagem.ImagemDigitalizada;

/**
 * Classe responsavel por manipular as informacoes
 * referentes as imagens digitalizadas
 * 
 * @author fknappe
 *
 */
public class Descritor {
	
	/**
	 * 
	 * @param numeroLinhas
	 * @param numeroColunas
	 * @return
	 */
	public static ImagemDigitalizada constroiImagemDigitalizada(
			int numeroLinhas,
			int numeroColunas
		) {
		ImagemDigitalizada imagem = new ImagemDigitalizada(
				numeroLinhas, 
				numeroColunas
		);
		return imagem;
	}
	
	/**
	 * 
	 * @param numeroLinhas
	 * @param numeroColunas
	 * @return
	 */
	public static ImagemBinarizada constroiImagemBinarizada(
			int numeroLinhas,
			int numeroColunas
	) {
		ImagemBinarizada imagem = new ImagemBinarizada(
				numeroLinhas, 
				numeroColunas
		);
		return imagem;
	}

	/**
	 * Metodo responsavel pela construcao do histograma da 
	 * imagem
	 * 
	 * @param int numeroFaixas
	 * 
	 * @return Histograma
	 */
	public static Histograma controiHistograma(ImagemDigitalizada imagem) {
		Histograma histograma = null;
		int nivelCinza = 0;
		
		histograma = new Histograma();
		
		for (Map.Entry<Short, List<Ponto>> entrada: imagem.getTabelaPontos().entrySet()) {
			nivelCinza = entrada.getKey();
			for (int i = 0; i < entrada.getValue().size(); i++) 
				histograma.acrescentar(nivelCinza);
		}
		return histograma;
	}
}
