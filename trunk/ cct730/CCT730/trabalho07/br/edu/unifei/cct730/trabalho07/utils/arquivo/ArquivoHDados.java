package br.edu.unifei.cct730.trabalho07.utils.arquivo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

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
	private BitInputStream inputStream = null;
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
	 * Metodo responsavel por salvar os dados
	 * da compressao da arvore de huffman no
	 * arquivo *.huff.dados
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
	 * Metodo responsavel por realizar a leitura dos
	 * dos dados do arquivo de compressao *.huff.dados
	 * 
	 * @param ArvoreHuffman arvore
	 * @param long quantidadeBits
	 * @param int numeroLinhas
	 * @param int numeroColunas
	 * 
	 * @return Short[][]
	 * @throws IOException
	 * @throws NumberFormatException
	 */
	public Short[][] lerHuffmanDados(
			ArvoreHuffman arvore, 
			long quantidadeBits,
			int numeroLinhas,
			int numeroColunas
	) throws IOException, NumberFormatException {

		// Declaracao de variaveis locais
		ArrayList<Short> vetor = null;
		String binario = "";
		String simbolo = "";
		int valor = 0;
		Short[][] tonsCinza = null;

		if(this.isReadable()) {

			/* 
			 * Vetor que contem os dados lidos do arquivo de bits.
			 */
			vetor = new ArrayList<Short>();

			/*
			 *  Obtendo os bits do arquivo todos os bits do arquivo
			 */
			for (int i = 0; i < quantidadeBits; i++) {

				valor = this.inputStream.readBit();

				/*
				 *  Verifica o valor (0 ou 1)
				 */
				switch (valor) {
				case 0:

					// Adiciona a sequencia o valor 0
					binario += "0";

					// Verifica se o simbolo ja existe na arvore
					if ((simbolo = arvore.buscaNoHEAP(binario)) != null) {

						/*
						 * Caso exista, adiciona ao vetor
						 * de valores e reinicio a sequencia
						 */
						vetor.add(Short.parseShort(simbolo));
						binario = "";
					}
					break;

				case 1:

					// Adiciona a sequencia o valor 1
					binario += "1";

					// Verifica se o simbolo ja existe na arvore
					if ((simbolo = arvore.buscaNoHEAP(binario)) != null) {

						/*
						 * Caso exista, adiciona ao vetor
						 * de valores e reinicio a sequencia
						 */
						vetor.add(Short.parseShort(simbolo));
						binario = "";
					}
					break;
				}

			} 
		}

		/*
		 *  Cria a matriz imagem contendo os tons de cinza lidos do
		 *  arquivo
		 */
		tonsCinza = new Short[numeroLinhas][numeroColunas];
		for (int i = 0; i < numeroLinhas; i++) {
			for (int j = 0; j < numeroColunas; j++) {
				tonsCinza[i][j] = vetor.remove(0);
			}
		}

		// retorna a matriz
		return tonsCinza;
	}

	/**
	 * Metodo responsavel por verificar se o stream
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
	 * Metodo responsavel por verificar se o strem
	 * esta preparado para leitura  
	 * 
	 * @return boolean
	 * @throws IOException
	 */
	protected boolean isReadable() throws IOException {
		if(this.inputStream == null) {
			this.abrirArquivoLeitura();
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

	/**
	 * Metodo responsavel pela abertura do arquivo para leitura
	 * 
	 * @return BitInputStream
	 * @throws IOException
	 */
	protected BitInputStream abrirArquivoLeitura() throws IOException {
		this.inputStream = 
			new BitInputStream(
					new FileInputStream(new File(this.getAbsolutePath()))
			);
		return this.inputStream;
	}
	
	/**
	 * Metodo responsavel por fechar o 
	 * stream de leitura do arquivo
	 * 
	 * @return void
	 * @throws IOException, NullPointerException
	 */
	public void fecharArquivoLeitura() throws IOException, NullPointerException {
		this.inputStream.close();
		this.inputStream = null;
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
