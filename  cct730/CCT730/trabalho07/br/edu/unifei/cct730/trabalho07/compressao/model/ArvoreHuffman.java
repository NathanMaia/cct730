package br.edu.unifei.cct730.trabalho07.compressao.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Classe responsavel por criar uma arvore de huffman
 * a partir da contabilizacao das frequencia dos 
 * simbolos
 * 
 * @author fknappe
 *
 */
public class ArvoreHuffman {

	// Declaracao de variaveis de instancia
	private NoHEAP raiz;
	private List<NoHEAP> listaNos;
	private List<FrequenciaSimbolo> frequenciaSimbolo;
	private FrequenciaSimbolo[] vetorFrequenciaSimbolo;

	/**
	 * Construtor
	 * 
	 */
	public ArvoreHuffman() {

	}

	/**
	 * Construtor
	 * 
	 * @param NoHEAP r
	 */
	public ArvoreHuffman(NoHEAP r) {
		this.raiz = r;
	}

	/**
	 * Construtor
	 * 
	 * @param List<FrequenciaSimbolo> fsim
	 */
	public ArvoreHuffman(List<FrequenciaSimbolo> fsim) {
		this.listaNos = new ArrayList<NoHEAP>();
		this.frequenciaSimbolo = fsim;
		for(FrequenciaSimbolo fs : fsim) {
			listaNos.add(new NoHEAP(fs.getFrequencia(), fs.getSimbolo()));
		}

		//Cria a arvore
		montarHEAP();

		//Percorre a arvore para descobrir a representação dos objetos
		percorrerHEAP();

		//Coloca as representações em um vetor
		frequenciaSimboloToVector();
	}

	/**
	 * Metodo responsavel por converter a lista de frequencia
	 * em um vetor
	 * 
	 * @return void
	 */
	private void frequenciaSimboloToVector() {
		vetorFrequenciaSimbolo = new FrequenciaSimbolo[256];
		for (FrequenciaSimbolo fr : frequenciaSimbolo) {
			vetorFrequenciaSimbolo[Integer.parseInt(fr.getSimbolo())] = fr;
		}
	}

	/**
	 * Metodo responsavel por montar a arvore
	 * HEAP
	 * 
	 * @return void
	 */
	private void montarHEAP() {
		// Declaracao de variaveis locais
		NoHEAP novo = null;

		// Enquanto existirem nos na lista
		while(!listaNos.isEmpty()) {
			// Caso so exista o no raiz na lista
			if(listaNos.size() == 1) {
				raiz = listaNos.remove(0);
			} else if(listaNos.size() > 1) {
				novo = new NoHEAP();
				novo.setNoEsquerdo(listaNos.remove(0));
				novo.setNoDireito(listaNos.remove(0));
				novo.setFrequencia(novo.getNoEsquerdo().getFrequencia() + novo.getNoDireito().getFrequencia());
				listaNos.add(novo);
				this.ordenarHEAP();
			}
		}
	}

	/**
	 * Metodo responsavel pela ordenacao
	 * dos elementos da arvore
	 * 
	 * @return void
	 */
	private void ordenarHEAP() {
		Collections.sort(listaNos);
	}

	/**
	 * Metodo responsavel por realizar a chamada
	 * recursiva para percorrer a arvore HEAP
	 * a partir da raiz
	 * 
	 * @return void
	 */
	private void percorrerHEAP() {
		this.percorrerHEAP(this.raiz, this.frequenciaSimbolo, "");
	}

	/**
	 * Metodo recusrsivo para percorrer os elementos
	 * da arvore do elemento especificado ate algum
	 * no folha
	 * 
	 * @param NoHEAP no
	 * @param List<FrequenciaSimbolo> fsimb
	 * @param String binario
	 * 
	 * @return void
	 */
	private void percorrerHEAP(NoHEAP no, List<FrequenciaSimbolo> fsimb, String binario) {
		if(no.getNoEsquerdo() != null) {
			percorrerHEAP(no.getNoEsquerdo(), fsimb, binario + "0");
		}

		if(no.getNoDireito() != null) {
			percorrerHEAP(no.getNoDireito(), fsimb, binario + "1");
		}

		if(no.isFolha()) {
			for(FrequenciaSimbolo fs : fsimb) {
				if(fs.getSimbolo().equals(no.getSimbolo())) {
					fs.setCodigoHuffman(binario);
				}
			}
		}

		no = null;
	}

	/**
	 * Metodo responsavel por realizar a busca
	 * de um no da arvore HEAP por seu codigo de
	 * huffman
	 * 
	 * @param String binario
	 * 
	 * @return String
	 */
	public String buscaNoHEAP(String binario) {
		return buscaNoHEAP(this.raiz, binario, 0); 
	}

	/**
	 * Metodo recursivo que implementa uma busca
	 * pelo elemento especificado na arvore HEAP
	 * 
	 * @param NoHEAP no
	 * @param String binario
	 * @param int i
	 * 
	 * @return String
	 */
	private String buscaNoHEAP(NoHEAP no, String binario, int i) {

		/* 
		 * Caso seja o no especificado seja folha,
		 * retorno o valor do simbolo
		 */

		if(no.isFolha()) {
			return no.getSimbolo();
		}

		// Percorrendo o HEAP
		if ( i < binario.length()) {
			// Verificando o valor i do codigo huffman
			switch(binario.charAt(i)) {
			case '0':
				i++;
				return buscaNoHEAP(no.getNoEsquerdo(), binario, i);

			case '1':
				i++;
				return buscaNoHEAP(no.getNoDireito(), binario, i);
			}
		}
		return null;
	}

	/**
	 * Metodo recursivo responsavel criar uma arvore
	 * HEAP a partir de um no, seu respectivo simbolo 
	 * e codigo huffman
	 * 
	 * @param NoHEAP no
	 * @param String simbolo
	 * @param String binario
	 * 
	 * @return void
	 */
	private void criarHEAPRecursivo(NoHEAP no, String simbolo, String binario) {

		// Caso seja o ultimo bit
		if(binario.length() == 1) {
			switch(binario.charAt(0)) {
			case '0':
				if (no.getNoEsquerdo() == null) {
					no.setNoEsquerdo(new NoHEAP());
				}
				no.getNoEsquerdo().setSimbolo(simbolo);
				return;
			case '1':
				if(no.getNoDireito() == null) {
					no.setNoDireito(new NoHEAP());
				}
				no.getNoDireito().setSimbolo(simbolo);
				return;
			}		
		}
		// Caso contrario
		switch(binario.charAt(0)) {
		case '0':
			if(no.getNoEsquerdo() == null) {
				no.setNoEsquerdo(new NoHEAP());
			}
			binario = binario.substring(1);
			criarHEAPRecursivo(no.getNoEsquerdo(), simbolo, binario);
			return;
		case '1':
			if(no.getNoDireito() == null) {
				no.setNoDireito(new NoHEAP());
			}
			binario = binario.substring(1);
			criarHEAPRecursivo(no.getNoDireito(), simbolo, binario);
			return;
		}
	}

	/**
	 * Metodo responsavel por adicionar um novo
	 * na arvore
	 * 
	 * @param String simbolo
	 * @param String binario
	 * 
	 * @return void
	 */
	public void add(String simbolo, String binario) {
		if (raiz == null) {
			raiz = new NoHEAP();
		}
		criarHEAPRecursivo(this.raiz, simbolo, binario);
	}

	/**
	 * Metodo responsavel por retornar uma string
	 * que contem a lista de simbolos presentes
	 * nesta arvore
	 * 
	 * @return String
	 */
	public String listaSimbolos() {
		String resposta = "";
		for (FrequenciaSimbolo fs : frequenciaSimbolo) {
			resposta += "\nSimbolo: " + fs.getSimbolo() + " - Frequencia: " + fs.getFrequencia() + " - Codigo Huff: " + fs.getCodigoHuffman();
		}
		return resposta;
	}

	// Metodos getters e setters
	public NoHEAP getRaiz() {
		return raiz;
	}

	public void setRaiz(NoHEAP raiz) {
		this.raiz = raiz;
	}

	public List<NoHEAP> getListaNos() {
		return listaNos;
	}

	public void setListaNos(List<NoHEAP> listaNos) {
		this.listaNos = listaNos;
	}

	public List<FrequenciaSimbolo> getFrequenciaSimbolo() {
		return frequenciaSimbolo;
	}

	public void setFrequenciaRepresentacao(
			List<FrequenciaSimbolo> frequenciaSimbolo) {
		this.frequenciaSimbolo = frequenciaSimbolo;
	}

	public FrequenciaSimbolo[] getVetorFrequenciaSimbolo() {
		return vetorFrequenciaSimbolo;
	}

	public void setVetorFrequenciaSimbolo(
			FrequenciaSimbolo[] vetorFrequenciaSimbolo) {
		this.vetorFrequenciaSimbolo = vetorFrequenciaSimbolo;
	}
}
