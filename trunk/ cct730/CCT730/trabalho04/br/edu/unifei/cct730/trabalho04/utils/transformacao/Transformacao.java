package br.edu.unifei.cct730.trabalho04.utils.transformacao;

import br.edu.unifei.cct730.trabalho04.gui.painel.PainelImagemBinaria;

public class Transformacao {
	
	// Constantes que determinam o tipo da transformacao
	private static final int AMPLIACAO = 0;
	private static final int REDUCAO = 1;
	private int tipo, fator;

	/**
	 * Construtor 
	 * 
	 * @param int tipo
	 * @param int fator
	 */
	public Transformacao(int tipo, int fator) {
		this.tipo = tipo;
		this.fator = fator;
	}

	/**
	 * Metodo responsavel por realizar a transformacao de escalamento 
	 * sobre a imagem binaria
	 * 
	 * @param PainelImagemBinaria imagemOriginal
	 * @param PainelImagemBinaria imagemNova
	 * 
	 * @return PainelImagemBinaria
	 */
	public PainelImagemBinaria realizarTransformacao(
			PainelImagemBinaria imagemOriginal
	) {
		
		// Declaracao de variaveis
		int numeroLinhas, numeroColunas, escalaI, escalaJ, novoI, novoJ, novoNl, novoNc = 0;
		float fatorCalculo = 0.0f;

		numeroLinhas = imagemOriginal.getNumeroLinhas();
		numeroColunas = imagemOriginal.getNumeroColunas();

		if (tipo == AMPLIACAO) fatorCalculo = fator;
		else if (tipo == REDUCAO) fatorCalculo = (float) (1.0 / fator);
		else fatorCalculo = 1;
		
		novoNl = (int) Math.round(numeroLinhas * fatorCalculo);
		novoNc = (int) Math.round(numeroColunas * fatorCalculo);
		PainelImagemBinaria imagemNova = new PainelImagemBinaria(novoNl, novoNc, imagemOriginal.getLimiar());

		for (int i = 0; i < numeroLinhas; i++) {
			for (int j = 0; j < numeroColunas; j++) {
				escalaI = (int) (i * fatorCalculo);
				escalaJ = (int) (j * fatorCalculo);
				for (novoI = escalaI; novoI < (i + 1) * fatorCalculo; novoI++) {
					for (novoJ = escalaJ; novoJ < (j + 1) * fatorCalculo; novoJ++) {
						if (novoI >= 0 && novoI < novoNl && novoJ >= 0 && novoJ < novoNc) {
							imagemNova.setPosicao(novoI, novoJ, imagemOriginal
									.getPosicao(i, j));
						}
					}
				}
			}
		}
		return imagemNova;
	}
}
