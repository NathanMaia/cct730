package br.edu.unifei.cct730.trabalho04.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Arquivo {
	
	String arquivo = "";
	
	public Arquivo(String a) {
		this.arquivo = a;
	}
	
	public BufferedReader getArquivo() throws IOException {
		return new BufferedReader(new FileReader(this.arquivo));
	}	
}
