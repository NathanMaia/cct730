package br.edu.unifei.cct730.trabalho05.model.arquivo;

public class ArquivoFactory {

	public static Arquivo create(int tipoArquivo, String nomeArquivo) {
		switch(tipoArquivo) {
		case Arquivo.ARQUIVO_CABECALHO:
			return new ArquivoCabecalho(nomeArquivo);
			
		case Arquivo.ARQUIVO_IMAGEM:
			return new ArquivoImagem(nomeArquivo);
			
		default:
			throw new NullPointerException("Tipo de arquivo inexistente");
		}
	}
}
