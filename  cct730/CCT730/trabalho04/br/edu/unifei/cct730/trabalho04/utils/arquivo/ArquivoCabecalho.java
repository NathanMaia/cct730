package br.edu.unifei.cct730.trabalho04.utils.arquivo;

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
		String[] linhaSplit = null;
		if(this.stream == null || !this.stream.ready())
			this.abrirArquivoCabecalho();
		
		linhaSplit = this.stream.readLine().split(" ");
		return Integer.parseInt(linhaSplit[0]);
	}
	
	/**
	 * Metodo que retorna o numero de colunas da imagem
	 * 
	 * @return Integer
	 * @throws IOException
	 */
	public Integer getNumeroColunas() throws IOException {
		String[] linhaSplit = null;
		if(this.stream == null || !this.stream.ready())
		  this.abrirArquivoCabecalho();
		
		linhaSplit = this.stream.readLine().split(" ");
		return Integer.parseInt(linhaSplit[1]);
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
}
