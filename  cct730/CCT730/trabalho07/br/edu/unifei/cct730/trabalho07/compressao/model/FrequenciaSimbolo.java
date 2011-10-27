package br.edu.unifei.cct730.trabalho07.compressao.model;

public class FrequenciaSimbolo {
	
	// Declaracao de variaveis de instancia
	private String codigoHuffman;
	private int frequencia;
	private String simbolo;
	
	/**
	 * Construtor
	 * 
	 * @param int freq
	 * @param String obj
	 */
	public FrequenciaSimbolo(int freq, String s) {
		this.frequencia = freq;
		this.simbolo = s;	
	}
	
	/**
	 * Construtor
	 * 
	 * @param String rep
	 * @param String obj
	 */
	public FrequenciaSimbolo(String ch, String s) {
		this.codigoHuffman = ch;
		this.simbolo = s;
	}

	// Metodos getters e setters

	public String getCodigoHuffman() {
		return codigoHuffman;
	}

	public void setCodigoHuffman(String codigoHuffman) {
		this.codigoHuffman = codigoHuffman;
	}

	public String getSimbolo() {
		return simbolo;
	}

	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}
	
	public int getFrequencia() {
		return frequencia;
	}

	public void setFrequencia(int frequencia) {
		this.frequencia = frequencia;
	}
}
