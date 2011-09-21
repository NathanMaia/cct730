package br.edu.unifei.cct730.trabalho06.utils.arquivo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ArquivoImagemComprimida extends Arquivo {

	//Declaracao de variaveis de instancia
	protected BufferedReader stream = null;
	protected String[] linha = null;
	
	/**
	 * Construtor 
	 * 
	 * @param String nomeArquivo
	 */
	public ArquivoImagemComprimida(String nomeArquivo) {
		super(nomeArquivo);
	}
	
}
