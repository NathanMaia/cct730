package br.edu.unifei.cct730.trabalho07.utils.arquivo;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import br.edu.unifei.cct730.trabalho07.compressao.model.ArvoreHuffman;
import br.edu.unifei.cct730.trabalho07.compressao.model.FrequenciaSimbolo;

/**
 * Classe responsavel por
 * 
 * @author fknappe
 *
 */
public class ArquivoHHeader extends Arquivo {

	//Declaracao de variaveis de instancia
	private long huffmanTamanhoHeader = 0;
	
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

	// Metodos getters e setters
	
	public long getHuffmanTamanhoHeader() {
		return huffmanTamanhoHeader;
	}
}
