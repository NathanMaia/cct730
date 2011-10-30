package br.edu.unifei.cct730.trabalho08.utils.arquivo;

import br.edu.unifei.cct730.trabalho08.utils.arquivo.Arquivo;
import br.edu.unifei.cct730.trabalho08.utils.arquivo.ArquivoCabecalho;
import br.edu.unifei.cct730.trabalho08.utils.arquivo.ArquivoImagem;

/**
 * Classe que implementa o pattern Factory
 * para instanciacao de objetos do tipo
 * Arquivo
 * 
 * @author fknappe
 *
 */
public class FactoryArquivo {

	/**
	 * Metodo responsavel por instanciar um
	 * objeto do tipo Arquivo
	 * 
	 * @param int tipoArquivo
	 * @param String nomeArquivo
	 * 
	 * @return Arquivo
	 */
	public static Arquivo create(int tipoArquivo, String nomeArquivo) throws IllegalArgumentException {
		switch(tipoArquivo) {
			case Arquivo.ARQUIVO_CABECALHO:
				return new ArquivoCabecalho(nomeArquivo);
			
			case Arquivo.ARQUIVO_IMAGEM:
				return new ArquivoImagem(nomeArquivo);
			
			default:
				throw new IllegalArgumentException("Tipo de arquivo invalido!");
		}
	}
}
