package br.edu.unifei.cct730.trabalho06.utils.arquivo;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ArquivoCompressao extends Arquivo {

	//Declaracao de variaveis de instancia
	protected String[] linha = null;

	/**
	 * Construtor 
	 * 
	 * @param String nomeArquivo
	 */
	public ArquivoCompressao(String nomeArquivo) {
		super(nomeArquivo);
	}

	/**
	 * Metodo responsavel por salvar as informacoes
	 * da imagem comprimida
	 * 
	 * @param String[] string
	 * 
	 * @return boolean
	 * @throws IOException
	 */
	public boolean salvarArquivoCompressao(int altura, int largura, String[] string) throws IOException {

		// Verifica primeiramente o status do arquivo
		if(this.isWritable()) {
			// Salva a altura e a largura
			this.streamWriter.write("" + altura);
			this.streamWriter.write(" ");
			this.streamWriter.write("" + largura);
			this.streamWriter.newLine();

			// Escreve os valores da imagem
			for (int i = 0; i < string.length; i++) {
				this.streamWriter.write(string[i]);
				this.streamWriter.newLine();
			}
			return true;
		}
		return false;
	}

	public String[] lerArquivoCompressao() throws IOException {

		// Declaracao das variaveis locais
		String[] arquivoDescomprimido = null;
		int numLinhas = 0, numColunas = 0;
		String[] linha = null;
		
		// Verifica primeiramente o status do arquivo
		if(this.isReadable()) {

			linha = this.streamReader.readLine().split(" ");
			
			// Le a altura e a largura
			numLinhas = Integer.parseInt(linha[0]);
			numColunas = Integer.parseInt(linha[1]);

			// Inicializando o vetor
			arquivoDescomprimido = new String[numLinhas];

			for(int i = 0; i < numLinhas; i++) {
				arquivoDescomprimido[i] = this.streamReader.readLine();
			}
			
			this.fecharArquivoLeitura();
		}

		return arquivoDescomprimido;
	}

	/**
	 * Metodo que retorna o numero de linhas da imagem
	 * 
	 * @return Integer
	 * @throws IOException
	 */
	public Integer getNumeroLinhas() throws IOException {

		if(this.isReadable() && linha == null) {
			linha = this.streamReader.readLine().split(" ");
			this.fecharArquivoLeitura();
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

		if(this.isReadable() && linha == null) {
			linha = this.streamReader.readLine().split(" ");
			this.fecharArquivoLeitura();
		}
		return Integer.parseInt(linha[1]);
	}
}
