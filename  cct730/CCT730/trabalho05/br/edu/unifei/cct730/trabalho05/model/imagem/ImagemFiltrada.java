package br.edu.unifei.cct730.trabalho05.model.imagem;

import java.util.List;
import java.util.Map;

import br.edu.unifei.cct730.trabalho05.model.ponto.Ponto;

/**
 * Classe responsavel por implementar
 * uma imagem filtrada
 * 
 * @author fknappe
 *
 */
public class ImagemFiltrada extends Imagem {
	
	/**
	 * Construtor
	 * 
	 * @param int numLinhas
	 * @param int numColunas
	 */
	public ImagemFiltrada(int numLinhas, int numColunas) {
		super(numLinhas, numColunas);
	}
	
	/**
	 * Metodo responsavel por retornar uma matriz
	 * dos tons de cinza presentes na imagem
	 * 
	 * @return Short[][]
	 */
	public Short[][] getTonsDeCinzaImagem() {
		// Declaracao de variaveis locais
		Short[][] tonsDeCinza = new Short[this.getNumeroLinhas()][this.getNumeroColunas()];
		
		for (Map.Entry<Short, List<Ponto>> entrada: this.getTabelaPontos().entrySet()) {
			for (Ponto ponto: entrada.getValue()) {
				tonsDeCinza[ponto.getX()][ponto.getY()] = entrada.getKey();
			}
		}
		return tonsDeCinza;
	}
}
