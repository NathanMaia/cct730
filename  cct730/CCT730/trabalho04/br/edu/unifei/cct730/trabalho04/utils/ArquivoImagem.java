package br.edu.unifei.cct730.trabalho04.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

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
	 * 
	 * @return
	 * @throws IOException
	 */
	public BufferedReader abrirArquivoImagem() throws IOException {
		this.stream = new BufferedReader(new FileReader(this.getName()));
		return this.stream;
	}

	/**
	 * 
	 * @return
	 */
	public void fecharArquivo() throws IOException, NullPointerException {
		this.stream.close();
	}

	/**
	 * 
	 * @param nl
	 * @param nc
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
