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
public class ArquivoCabecalho extends File {
	
	//Declaracao de variaveis de instancia
	private BufferedReader stream = null;
	private String[] linha = null;
	
	/**
	 * Construtor
	 * 
	 * @param String a
	 * @throws IllegalArgumentException
	 */
	public ArquivoCabecalho(String a) throws IllegalArgumentException {
		super(a);
	}
	
	/**
	 * Metodo responsavel pela abertura do arquivo para leitura
	 * 
	 * @return BufferedReader
	 * @throws IOException
	 */
	public BufferedReader abrirArquivoCabecalho() throws IOException { 
		this.stream = new BufferedReader(
			new FileReader(
				new File(this.getAbsolutePath())
			)
		);
		return this.stream;
	}
	
	/**
	 * Metodo que retorna o numero de linhas da imagem
	 * 
	 * @return Integer
	 * @throws IOException
	 */
	public Integer getNumeroLinhas() throws IOException {
		if(this.isReady() && linha == null) {
			linha = this.stream.readLine().split(" ");
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
		if(this.isReady() && linha == null) {
			linha = this.stream.readLine().split(" ");
		}
		return Integer.parseInt(linha[1]);
	}
	
	/**
	 * Metodo responsavel por fechar o arquivo
	 * 
	 * @return void
	 * @throws IOException, NullPointerException
	 */
	public void fecharArquivo() throws IOException, NullPointerException {
		this.stream.close();
	}
	
	/**
	 * Metodo responsavel por verificar se o arquivo
	 * esta pronto para leitura
	 * 
	 * @return boolean
	 * @throws IOException
	 */
	private boolean isReady() throws IOException {
		if(this.stream == null) {
			  this.abrirArquivoCabecalho();
		} 
		return true;
	}
}
