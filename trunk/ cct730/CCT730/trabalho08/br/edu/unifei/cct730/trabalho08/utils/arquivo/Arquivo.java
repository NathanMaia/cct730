package br.edu.unifei.cct730.trabalho08.utils.arquivo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
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
	protected BufferedReader streamReader = null;
	protected BufferedWriter streamWriter = null;
	
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
	public BufferedReader abrirArquivoLeitura() throws IOException { 
		this.streamReader = new BufferedReader(
			new FileReader(
					new File(this.getAbsolutePath())
			)
		);
		return this.streamReader;
	}
	
	/**
	 * Metodo responsavel pela abertura do arquivo para escrita
	 * 
	 * @return BufferedWriter
	 * @throws IOException
	 */
	public BufferedWriter abrirArquivoEscrita() throws IOException {
		this.streamWriter = new BufferedWriter(
			new FileWriter(
					new File(this.getAbsolutePath())
			)
		);
		return this.streamWriter;
	}
	
	/**
	 * Metodo responsavel por fechar o 
	 * stream de leitura do arquivo
	 * 
	 * @return void
	 * @throws IOException, NullPointerException
	 */
	public void fecharArquivoLeitura() throws IOException, NullPointerException {
		this.streamReader.close();
		this.streamReader = null;
	}
	
	/**
	 * Metodo responsavel por fechar o 
	 * stream de escrita do arquivo
	 * 
	 * @return void
	 * @throws IOException, NullPointerException
	 */
	public void fecharArquivoEscrita() throws IOException, NullPointerException {
		this.streamWriter.close();
		this.streamWriter = null;
	}
	
	/**
	 * Metodo utilizado para verificacao
	 * status do stream do arquivo
	 * 
	 * @return boolean
	 */
	public boolean isFechadoLeitura() {
		return (this.streamReader == null) ? true : false;
	}
	
	public boolean isFechadoEscrita() {
		return (this.streamWriter == null) ? true : false;
	}
	
	/**
	 * Metodo responsavel por verificar se o arquivo
	 * esta pronto para leitura
	 * 
	 * @return boolean
	 * @throws IOException
	 */
	public boolean isReadable() throws IOException {
		if(this.streamReader == null) {
			this.abrirArquivoLeitura();
		} 
		return true;
	}
	
	/**
	 * Metodo responsavel por verificar se o arquivo
	 * esta pronto para escrita
	 * 
	 * @return boolean
	 * @throws IOException
	 */
	protected boolean isWritable() throws IOException {
		if(this.streamWriter == null) {
			this.abrirArquivoEscrita();
		}
		return true;
	}
}
