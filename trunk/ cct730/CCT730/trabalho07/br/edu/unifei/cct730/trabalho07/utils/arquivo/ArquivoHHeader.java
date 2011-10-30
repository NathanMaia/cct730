package br.edu.unifei.cct730.trabalho07.utils.arquivo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.edu.unifei.cct730.trabalho07.compressao.model.ArvoreHuffman;
import br.edu.unifei.cct730.trabalho07.compressao.model.FrequenciaSimbolo;

/**
 * Classe responsavel por manipular
 * os dados contidos dentro cabecalho do arquivo
 * de compressao
 * 
 * @author fknappe
 *
 */
public class ArquivoHHeader extends Arquivo {

	//Declaracao de variaveis de instancia
	private long huffmanTamanhoHeader = 0;
	private int numeroLinhas = 0;
	private int numeroColunas = 0;
	private long quantidadeBits = 0;

	/**
	 * Construtor 
	 * 
	 * @param String nomeArquivo
	 */
	public ArquivoHHeader(String nomeArquivo) {
		super(nomeArquivo);
	}

	/**
	 * Metodo responsavel por salvar os dados 
	 * da imagem comprimidos no formato de
	 * huffman
	 * 
	 * @return boolean
	 */
	public boolean salvarHuffmanHeader(ArvoreHuffman arvore, int nl, int nc) throws IOException {

		// Declaracao de variaveis locais
		long soma = 0;

		if(this.isWritable()) {

			// Salva a algura e largura
			this.streamWriter.write("" + nl);
			this.streamWriter.write(" ");
			this.streamWriter.write("" + nc);
			this.streamWriter.newLine();

			// O tamanho da tabela de huffman
			this.streamWriter.write("" + arvore.getFrequenciaSimbolo().size());
			this.streamWriter.newLine();

			// Salva a tabela
			for (FrequenciaSimbolo fs : arvore.getFrequenciaSimbolo()) {
				this.streamWriter.write(fs.getSimbolo() + " " + fs.getCodigoHuffman());
				this.streamWriter.newLine();
			}

			// Conta os bits
			for (FrequenciaSimbolo fs : arvore.getFrequenciaSimbolo()) {
				soma += fs.getCodigoHuffman().length() * fs.getFrequencia();
			}

			// Salva os bits
			this.streamWriter.write(Long.toString(soma));
			this.streamWriter.newLine();
			this.streamWriter.flush();

			huffmanTamanhoHeader = this.length();

			return true;
		}
		return false;
	}

	/**
	 * Metodo responsavel
	 * 
	 * @return ArvoreHuffman
	 * @throws IOException
	 */
	public ArvoreHuffman lerHuffmanHeader() throws IOException { 

		// Declaracao de variaveis locais
		String str[] = null;
		String string = "";
		ArvoreHuffman arvHuffman = null;
		int quantidadeNumeros = 0;
		List<FrequenciaSimbolo> frequenciaSimbolo;

		if(this.isReadable()) {

			/*
			 *  Obtendo os dados da dimensao da imagem
			 */
			str = this.streamReader.readLine().split(" ");
			this.numeroLinhas = Integer.parseInt(str[0]);
			this.numeroColunas = Integer.parseInt(str[1]);

			/*
			 *  Obtendo a quantidades de entradas na tabela de huffman
			 */
			string = this.streamReader.readLine();
			quantidadeNumeros = Integer.parseInt(string);

			/*
			 *  Criando a tabela de huffman
			 */
			frequenciaSimbolo = new ArrayList<FrequenciaSimbolo>();
			for (int i = 0; i < quantidadeNumeros; i++) {
				str = this.streamReader.readLine().split(" ");
				frequenciaSimbolo.add(new FrequenciaSimbolo(str[1], str[0]));
			}

			/*
			 * Obtendo a quantidade de bits gravados no arquivo de dados da
			 * imagem
			 */
			string = this.streamReader.readLine();
			this.quantidadeBits = Long.parseLong(string);
			
			/*
			 *  Cria a arvore de huffman
			 */
			arvHuffman = new ArvoreHuffman();
			for (FrequenciaSimbolo fs : frequenciaSimbolo) {
				arvHuffman.add(fs.getSimbolo(), fs.getCodigoHuffman());
			}
		}

		return arvHuffman;
	}

	// Metodos getters e setters

	public long getHuffmanTamanhoHeader() {
		return huffmanTamanhoHeader;
	}

	public long getQuantidadeBits() {
		return quantidadeBits;
	}

	public int getNumeroLinhas() {
		return numeroLinhas;
	}

	public int getNumeroColunas() {
		return numeroColunas;
	}
}
