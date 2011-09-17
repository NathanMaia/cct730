package br.edu.unifei.cct730.trabalho05.model.imagem;

/**
 * Classe que implementa o pattern Factory para inicializacao
 * das imagens
 * 
 * @author fknappe
 *
 */
public class FactoryImagem {

	/**
	 * Metodo responsavel pela inicializacao de um 
	 * objeto do tipo Imagem
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
				throw new IllegalArgumentException("Tipo de imagem invalido!");
		}
	}
}
