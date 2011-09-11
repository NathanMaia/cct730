package br.edu.unifei.cct730.trabalho04.utils.histograma;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.edu.unifei.cct730.trabalho04.utils.formas.PontoBinario;

/**
 * Classe responsavel por armazenar todas as informacoes
 * referentes a imagem
 * 
 * @author fknappe
 *
 */
public class Descritor {
	
	// Declaração das variáveis de instância
	private Map<Short, List<PontoBinario>> tabelaPontos;
	private int numeroLinhas, numeroColunas;

	/**
	 * Construtor
	 * @param int nl
	 * @param int nc
	 */
	public Descritor(int nl, int nc) {
		this.numeroLinhas = nl;
		this.numeroColunas = nc;
		tabelaPontos = new HashMap<Short, List<PontoBinario>>();
	}

	/**
	 * Metodo responsavel por adicionar um novo tom de cinza da 
	 * imagem
	 * 
	 * @param int i
	 * @param int j
	 * @param short nivelCinza
	 * 
	 * @return void
	 */
	public void adicionar(int i, int j, short tomCinza) {
		List<PontoBinario> lista;
		
		if (tabelaPontos.containsKey(tomCinza) == false) {
			tabelaPontos.put(tomCinza, new ArrayList<PontoBinario>());
		}
		
		lista = tabelaPontos.get(tomCinza);
		lista.add(new PontoBinario(i, j));
	}

	/**
	 * Metodo responsavel pela construcao do histograma da 
	 * imagem
	 * 
	 * @param int numeroFaixas
	 * 
	 * @return Histograma
	 */
	public Histograma controiHistograma(int numeroFaixas) {
		Histograma histograma = null;
		int tomCinza = 0;
		
		histograma = new Histograma(numeroFaixas);
		
		for (Map.Entry<Short, List<PontoBinario>> entrada: tabelaPontos.entrySet()) {
			tomCinza = entrada.getKey();
			for (int i = 0; i < entrada.getValue().size(); i++) 
				histograma.acrescentar(tomCinza);
		}
		return histograma;
	}
	
	public Descritor getDescritorEqualizado(Histograma histograma) {
		Descritor descritorEqualizado;
		Map<Short, List<PontoBinario>> tabelaEqualizada;
		List<PontoBinario> listaPontos;
		short nivelCinza, faixa;
		double[] porcentagensAcumuladas;
		double amplitude;
		int faixas;
		
		descritorEqualizado = new Descritor(this.numeroLinhas, this.numeroColunas);
		tabelaEqualizada = descritorEqualizado.getTabelaPontos();
		
		faixas = histograma.getFaixas();
		amplitude = 255.0/faixas;
		porcentagensAcumuladas = histograma.getPorcentagensAcumuladas();
		
		for (Map.Entry<Short, List<PontoBinario>> entrada: tabelaPontos.entrySet()) {
			faixa = (short)(entrada.getKey()/amplitude);
			nivelCinza = (short) Math.round(faixaProxima(porcentagensAcumuladas[faixa], faixas)*amplitude);
			
			for (PontoBinario ponto: entrada.getValue()) {
				if (tabelaEqualizada.containsKey(nivelCinza) == false)
					tabelaEqualizada.put(nivelCinza, new ArrayList<PontoBinario>());
				
				listaPontos = tabelaEqualizada.get(nivelCinza);
				listaPontos.add(ponto);
			}
		}
		return descritorEqualizado;
	}
	
	/**
	 * Metodo responsavel por retorna uma faixa do histograma
	 * da imagem proxima a faixa especificada
	 * 
	 * @param int porcentagem
	 * @param int faixas
	 * 
	 * @return short
	 */
	private short faixaProxima(double porcentagem, int faixas) {
		return (short) Math.round(porcentagem*faixas);
	}
	
	// Metodos getters e setters
	public Map<Short, List<PontoBinario>> getTabelaPontos() {
		return tabelaPontos;
	}

	public int getNumeroLinhas() {
		return this.numeroLinhas;
	}

	public int getNumeroColunas() {
		return this.numeroColunas;
	}
}
