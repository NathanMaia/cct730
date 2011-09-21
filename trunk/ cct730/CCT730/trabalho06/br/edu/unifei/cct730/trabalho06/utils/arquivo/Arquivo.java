package br.edu.unifei.cct730.trabalho06.utils.arquivo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Classe abstrata responsavel por implementar
 * os comportamentos comuns as classes de 
 * interacao com arquivos
 * 
 * @author fknappe 
 */
public abstract class Arquivo extends File {

	// Constantes
	public static final int ARQUIVO_CABECALHO = 1;
	public static final int ARQUIVO_IMAGEM = 2;
	
	//Declaracao de variaveis de instancia
	protected BufferedReader stream = null;
	protected String[] linha = null;
	
	/**
	 * Construtor 
	 * 
	 * @param String pathname
	 */
	public Arquivo(String nomeArquivo) {
		super(nomeArquivo);
	}
	
	/**
	 * Metodo responsavel pela abertura do arquivo para leitura
	 * 
	 * @return BufferedReader
	 * @throws IOException
	 */
	public BufferedReader abrirArquivo() throws IOException { 
		this.stream = new BufferedReader(
				new FileReader(
						new File(this.getAbsolutePath())
				)
		);
		return this.stream;
	}
	
	/**
	 * Metodo responsavel por fechar o 
	 * stream do arquivo
	 * 
	 * @return void
	 * @throws IOException, NullPointerException
	 */
	public void fecharArquivo() throws IOException, NullPointerException {
		this.stream.close();
		this.stream = null;
	}
	
	/**
	 * Metodo utilizado para verificacao
	 * status do stream do arquivo
	 * 
	 * @return boolean
	 */
	public boolean isFechado() {
		return (this.stream == null) ? true : false;
	}
	
	/**
	 * Metodo responsavel por verificar se o arquivo
	 * esta pronto para leitura
	 * 
	 * @return boolean
	 * @throws IOException
	 */
	protected boolean isReady() throws IOException {
		if(this.stream == null) {
			this.abrirArquivo();
		} 
		return true;
	}
}
