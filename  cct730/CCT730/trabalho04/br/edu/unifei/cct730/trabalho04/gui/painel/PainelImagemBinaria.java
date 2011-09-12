package br.edu.unifei.cct730.trabalho04.gui.painel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;

import br.edu.unifei.cct730.trabalho04.ponto.Ponto;
import br.edu.unifei.cct730.trabalho04.ponto.PontoBinario;
import br.edu.unifei.cct730.trabalho04.utils.histograma.Descritor;
import br.edu.unifei.cct730.trabalho04.utils.histograma.Histograma;
import br.edu.unifei.cct730.trabalho04.utils.imagem.ImagemBinarizada;

/**
 * Classe responsavel por criar a imagem binarizada
 * @author fknappe
 *
 */
public class PainelImagemBinaria extends PainelImagem {
	
	// Declaracao das variaveis de instacia
	private short limiar = 128;
	
	public PainelImagemBinaria() {
		super();
	}
	
	/**
	 * Construtor 
	 * 
	 * @param ImagemBinarizada im
	 * 
	 */
	public PainelImagemBinaria(ImagemBinarizada im) {
		super(im);
	}
	
	/**
	 * Metodo responsavel por construir a imagem binaria
	 * 
	 * @param tabelaPontos
	 * 
	 * @return void
	 */
	public void constroiImagem(Map<Short, List<Ponto>> tabelaPontos) {
		// Declaracao das variaveis locais
		short nivelCinza = 0;
		
		for (Map.Entry<Short, List<Ponto>> entrada: tabelaPontos.entrySet()) {
			nivelCinza = entrada.getKey();
			for (Ponto ponto: entrada.getValue()) {
				setPosicao(ponto.getX(), ponto.getY(), binarizar(nivelCinza));
			}
		}
	}
	
	/**
	 * Metodo responsavel por inicializar o vetor de pontos do painel
	 * 
	 * @return void
	 */
	protected void constroiVetorPontos() {
		this.setPontos(new Ponto[this.getNumeroLinhas()][this.getNumeroColunas()]);
		for (int i = 0; i < this.getNumeroLinhas(); i++) {
			for (int j = 0; j < this.getNumeroColunas(); j++) {
				this.getPontos()[i][j] = new PontoBinario(i, j);
			}
		}
	}
	
	/**
	 * Metodo responsavel por retornar o estado do ponto binario (retorna
	 * 0 ou 1)
	 * 
	 * @param int posLinha
	 * @param int posColuna
	 * 
	 * @return boolean
	 */
	public boolean getPosicao(int posLinha, int posColuna) {
		return ((PontoBinario)this.getPontos()[posLinha][posColuna]).getEstado();
	}
	
	/**
	 * Metodo responsavel por alterar o estado do ponto binario (0 ou 1)
	 * 
	 * @param int posLinha
	 * @param int posColuna
	 * @param boolean estado
	 * 
	 * @return void
	 */
	protected void setPosicao(int posLinha, int posColuna, boolean estado) {
		((PontoBinario)this.getPontos()[posLinha][posColuna]).setEstado(estado);
	}
	
	/**
	 * Metodo responsavel por trocar o estado de ponto binario
	 * especificado
	 * 
	 * @param int posLinha
	 * @param int posColuna
	 * 
	 * @return void
	 */
	public void trocaEstadoPosicao(int posLinha, int posColuna) {
		((PontoBinario)this.getPontos()[posLinha][posColuna]).trocaEstado();
	}
	
	/**
	 * Metodo responsavel por determinar quais areas da imagem
	 * serao binarizadas
	 * 
	 * @param short readShort
	 * 
	 * @return boolean
	 */
	private boolean binarizar(short readShort) {
		if (readShort < this.limiar) return true;
		return false;
	}

	// Metodos getters e setters
	public short getLimiar() {
		return limiar;
	}

	public void setLimiar(short limiar) {
		this.limiar = limiar;
	}
}
