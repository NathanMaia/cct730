package br.edu.unifei.cct730.trabalho04.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * 
 * @author fknappe
 *
 */
public class ArquivoCabecalho extends File {
	
	//Declaracao de variaveis de instancia
	private BufferedReader stream = null;
	
	/**
	 * Construtor
	 * @param a
	 */
	public ArquivoCabecalho(String a) throws IllegalArgumentException {
		super(a);
	}
	
	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	public BufferedReader abrirArquivoCabecalho() throws IOException { 
		this.stream = new BufferedReader(
			new FileReader(
				new File(this.getName())
			)
		);
		return this.stream;
	}
	
	/**
	 * 
	 * @return
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
	 * 
	 * @return
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
	 * 
	 * @return
	 */
	public void fecharArquivo() throws IOException, NullPointerException {
		this.stream.close();
	}
}
