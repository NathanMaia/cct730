package br.edu.unifei.cct730.trabalho07.compressao.model;

/**
 * Classe responsavel por representar a estrutura
 * de dados dos nos da arvore HEAP
 * 
 * @author fknappe
 *
 */
public class NoHEAP implements Comparable<NoHEAP> {
	
	// Declaracao das variaveis de instancia
	private int frequencia;
	private NoHEAP noEsquerdo;
	private NoHEAP noDireito;
	private String simbolo;
	
	/**
	 * Construtor
	 */
	public NoHEAP() {
		this.noEsquerdo = null;
		this.noDireito = null;
	}
	
	/**
	 * Construtor
	 * 
	 * @param int valor
	 * @param int objeto
	 */
	public NoHEAP(int f, String s) {
		this();
		this.frequencia = f;
		this.simbolo = s;
	}
	
	/**
	 * Construtor
	 * 
	 * @param NoHEAP esq
	 * @param NoHEAP dir
	 * @param int f
	 */
	public NoHEAP(NoHEAP esq, NoHEAP dir, int f) {
		this.noEsquerdo = esq;
		this.noDireito = dir;
		this.frequencia = f;
	}
	
	/**
	 * Metodo responsavel por verificar se trata-se
	 * de um no folha da arvore HEAP
	 * 
	 * @return boolean
	 */
	public boolean isFolha() {
		return ((this.noEsquerdo == null) && (this.noDireito == null)) ? true : false;
	}
	
	/**
	 * Metodo responsavel implementar a acao de comparacao
	 * entre os nos da arvore (ordenacao do HEAP)
	 * 
	 * @param NoHEAP obj
	 * 
	 * @return int
	 */
	public int compareTo(NoHEAP obj) {
		if (this.frequencia == obj.frequencia) {
			if (this.noEsquerdo != null && obj.noEsquerdo == null) {
				return -1;
			} else if (this.noEsquerdo == null && obj.noEsquerdo != null) {
				return 1;
			} else if (this.noEsquerdo != null && obj.noEsquerdo != null) {
				if (this.noDireito != null && obj.noDireito == null) {
					return -1;
				} else if (this.noDireito == null && obj.noDireito != null) {
					return 1;
				}
			}
			return 0;
		} else if (this.frequencia < obj.frequencia) {
			return -1;
		} else {
			return 1;
		}
	}

	// Metodos getters e setters
	
	public int getFrequencia() {
		return frequencia;
	}

	public void setFrequencia(int frequencia) {
		this.frequencia = frequencia;
	}

	public NoHEAP getNoEsquerdo() {
		return noEsquerdo;
	}

	public void setNoEsquerdo(NoHEAP noEsquerdo) {
		this.noEsquerdo = noEsquerdo;
	}

	public NoHEAP getNoDireito() {
		return noDireito;
	}

	public void setNoDireito(NoHEAP noDireito) {
		this.noDireito = noDireito;
	}

	public String getSimbolo() {
		return simbolo;
	}

	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}
}
