package br.edu.unifei.cct730.trabalho05.utils.arquivo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Classe responsavel por interagir com o arquivo 
 * de cabecalho da imagem
 * 
 * @author fknappe
 *
 */
public class ArquivoCabecalho extends Arquivo {

	// Declaracao de variaveis locais
	private int numeroLinhas = 0;
	private int numeroColunas = 0;
	protected String[] linha = null;
	
	/**
	 * Construtor
	 * 
	 * @param String a
	 * @throws IllegalArgumentException
	 */
	public ArquivoCabecalho(String pathname) {
		super(pathname);
	}

	/**
	 * Metodo que retorna o numero de linhas da imagem
	 * 
	 * @return Integer
	 * @throws IOException
	 */
	public Integer getNumeroLinhas() throws IOException {
		if(this.numeroLinhas == 0) {
			if(this.isReady() && linha == null) {
				linha = this.stream.readLine().split(" ");
			}
		}
		return Integer.parseInt(linha[0]);
	}

	/**
	 * Metodo que retorna o numero de colunas da imagem
	 * 
	 * @return Integer
	 * @throws IOException
	 */
	public Integer getNumeroColunas() throws IOException {
		if(this.numeroColunas == 0) {
			if(this.isReady() && linha == null) {
				linha = this.stream.readLine().split(" ");
			}
		}
		return Integer.parseInt(linha[1]);
	}
}
