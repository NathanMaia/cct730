package br.edu.unifei.cct730.trabalho05.model.imagem;

/**
 * Classe que implementa o pattern Factory para inicializacao
 * das imagens
 * 
 * @author fknappe
 *
 */
public class ImagemFactory {

	/**
	 * Metodo responsavel pela inicializacao de uma imagem
	 * 
	 * @param int tipoImagem
	 * @param int numLinhas
	 * @param numColunas
	 * 
	 * @return Imagem
	 */
	public static Imagem getImagem(
			int tipoImagem, 
			int numLinhas, 
			int numColunas
	) {

		switch(tipoImagem) {
			case Imagem.IMAGEM_DIGITALIZADA:
				return new ImagemDigitalizada(numLinhas, numColunas);

			case Imagem.IMAGEM_FILTRADA:
				return new ImagemFiltrada(numLinhas, numColunas);
				
			default:
				throw new NullPointerException("Tipo de imagem invalido!");
		}
	}
}
