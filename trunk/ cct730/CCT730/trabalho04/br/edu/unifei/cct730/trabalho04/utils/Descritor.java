package br.edu.unifei.cct730.trabalho04.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Descritor {
	
	// Declaração das variáveis de instância
	private Map<Short, List<PontoBinario>> tabelaPontos;
	private int nl, nc;

	public Descritor(int nl, int nc) {
		this.nl = nl;
		this.nc = nc;
		tabelaPontos = new HashMap<Short, List<PontoBinario>>();
	}

	public void adicionar(int i, int j, short nivelCinza) {
		List<PontoBinario> lista;
		
		if (tabelaPontos.containsKey(nivelCinza) == false) {
			tabelaPontos.put(nivelCinza, new ArrayList<PontoBinario>());
		}
		
		lista = tabelaPontos.get(nivelCinza);
		lista.add(new PontoBinario(i, j));
	}

	public Histograma controiHistograma(int faixas) {
		Histograma histograma;
		int nivelCinza, i;
		
		histograma = new Histograma(faixas);
		
		for (Map.Entry<Short, List<PontoBinario>> entrada: tabelaPontos.entrySet()) {
			nivelCinza = entrada.getKey();
			for (i=0; i<entrada.getValue().size(); i++) 
				histograma.acrescentar(nivelCinza);
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
		
		descritorEqualizado = new Descritor(nl, nc);
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
	
	private short faixaProxima(double porcentagem, int faixas) {
		return (short) Math.round(porcentagem*faixas);
	}
	
	public Map<Short, List<PontoBinario>> getTabelaPontos() {
		return tabelaPontos;
	}

	public int getNl() {
		return nl;
	}

	public int getNc() {
		return nc;
	}
}
