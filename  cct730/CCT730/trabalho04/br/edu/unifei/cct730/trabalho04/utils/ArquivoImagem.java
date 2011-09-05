package br.edu.unifei.cct730.trabalho04.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Classe responsavel por interagir com o arquivo
 * de dados da imagem
 * 
 * @author fknappe
 *
 */
public class ArquivoImagem extends File {

	//Declaracao das variaveis de instancia
	private BufferedReader stream = null;

	/**
	 * Construtor
	 * @param a
	 */
	public ArquivoImagem(String a) throws IllegalArgumentException {
		super(a);
	}

	/**
	 * Metodo responsavel por abrir o arquivo para leitura
	 * 
	 * @return BufferedReader
	 * @throws IOException
	 */
	public BufferedReader abrirArquivoImagem() throws IOException {
		this.stream = new BufferedReader(new FileReader(this.getName()));
		return this.stream;
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
	 * Metodo responsavel por pegar todos os tons de cinza
	 * presentes na imagem
	 * 
	 * @param int nl
	 * @param int nc
	 * 
	 * @return Short[][]
	 * @throws IOException
	 */
	public Short[][] getTonsCinza(int nl, int nc) throws IOException {
		Short[][] tonsDeCinza = new Short[nl][nc];
		String linha = "";
		String linhaRepartida[] = null;
		if(this.stream == null || !this.stream.ready())
			this.abrirArquivoImagem();
		
		for (int i = 0; ((linha = this.stream.readLine()) != null) && (i < nl); i++) {
			linhaRepartida = linha.split(" ");
			for (int j = 0; j < (linhaRepartida.length) && (j < nc); j++) {
				tonsDeCinza[i][j] = Short.parseShort(linhaRepartida[j]);	
			}
		}
		
		return tonsDeCinza;
	}
}
