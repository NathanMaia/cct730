package br.edu.unifei.cct730.trabalho07.utils.arquivo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import br.edu.unifei.cct730.trabalho07.compressao.model.ArvoreHuffman;

/**
 * Classe responsavel por manipular os dados
 * da arvore de huffman e salva-los no arquivo
 * 
 * @author fknappe
 *
 */
public class ArquivoHDados extends File {

	// Declaracao das variaveis de instancia
	private BitOutputStream outputStream = null;
	private long huffmanTamanho;
	private long huffmanTamanhoData;
	private long huffmanTamanhoHeader;

	/**
	 * Construtor
	 * 
	 * @param String nomeArquivo
	 */
	public ArquivoHDados(String nomeArquivo) {
		super(nomeArquivo);
	}

	/**
	 * Metodo responsavel
	 * 
	 * @param Short[][] tonsCinza
	 * @param ArvoreHuffman arvore
	 * @param int nl
	 * @param int nc
	 * 
	 * @return boolean
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	public boolean salvarHuffmanDados(Short tonsCinza[][], ArvoreHuffman arvore, int nl, int nc) throws NumberFormatException, IOException {

		// Declaracao de variaveis locais
		String representacao = "";

		if(this.isWritable()) {
			// Salva os bits
			for (int i = 0; i < nl; i++) {
				for (int j = 0; j < nc; j++) {
					representacao = arvore.getVetorFrequenciaSimbolo()[tonsCinza[i][j]].getCodigoHuffman();
					for (int k = 0; k < representacao.length(); k++) {
						outputStream.writeBit(Integer.parseInt("" + representacao.charAt(k)));
					}
				}
			}

			huffmanTamanhoData = this.length();
			huffmanTamanho = huffmanTamanhoData + huffmanTamanhoHeader;
			return true;
		}
		return false;
	}

	/**
	 * Metodo responsavel pela verificar se o stream
	 * esta preparado para escrita
	 * 
	 * @return boolean
	 * @throws IOException
	 */
	protected boolean isWritable() throws IOException {
		if(this.outputStream == null) {
			this.abrirArquivoEscrita();
		}
		return true;
	}

	/**
	 * Metodo responsavel pela abertura do arquivo para escrita
	 * 
	 * @return BitOutputStream
	 * @throws IOException
	 */
	protected BitOutputStream abrirArquivoEscrita() throws IOException {
		this.outputStream = 
			new BitOutputStream(
					new FileOutputStream(new File(this.getAbsolutePath()))
			);
		return this.outputStream;
	}
	
	/**
	 * Metodo responsavel por fechar o 
	 * stream de escrita do arquivo
	 * 
	 * @return void
	 * @throws IOException, NullPointerException
	 */
	public void fecharArquivoEscrita() throws IOException, NullPointerException {
		this.outputStream.close();
		this.outputStream = null;
	}

	// Metodos getters e setters
	
	public long getHuffmanTamanho() {
		return huffmanTamanho;
	}

	public long getHuffmanTamanhoData() {
		return huffmanTamanhoData;
	}

	public long getHuffmanTamanhoHeader() {
		return huffmanTamanhoHeader;
	}

	public void setHuffmanTamanhoHeader(long huffmanTamanhoHeader) {
		this.huffmanTamanhoHeader = huffmanTamanhoHeader;
	} 
}
